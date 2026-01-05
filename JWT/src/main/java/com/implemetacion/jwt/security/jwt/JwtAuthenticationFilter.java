package com.implemetacion.jwt.security.jwt;


import com.implemetacion.jwt.security.implementations.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
    Esta clase representa un filtro que sera agregado al SecurityFilterChain. Este filtro se encarga de
    validar y autenticar los tokens JWT presentes en las solicitudes, permitiendo así el acceso a
    los recursos protegidos solo a los usuarios autenticados.
    El filtro sera agregado antes del filto UsernamePasswordAuthenticationFilter.
*/
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
/*
    La clase OncePerRequestFilter es una implementacion de Filter que nos permite definir un filtro
    que solo se ejecutara una vez por cada solicitud.
    Extender de esta clase nos permite indicarle a spring que esta clase representa un filtro para
    poder agregarlo al SecurityFilterChain.
*/

    private final JWTUtils jwtUtils;
    private final UserDetailServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        var jwt = jwtUtils.extractJwt(request);

        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            // extraer el email del token
            var email = jwtUtils.getEmailFromToken(jwt);

            // cargar datos del usuario en UserDetails
            var userDetails = userDetailsService.loadUserByUsername(email);

            // crear nuevo token autenticado
            var authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null,
                    userDetails.getAuthorities());
            /*
                UsernamePasswordAuthenticationToken es una implementacion de la interfaz Authentication
                que representa un token de autenticacion.
            */

            // agregar detalles de la solicitud al token de autenticacion
            authentication.setDetails(
                    new WebAuthenticationDetailsSource()
                            .buildDetails(request)
            );

            // guardar el token de autenticacion en el SecurityContext
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
        }

        // instruccion para continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
