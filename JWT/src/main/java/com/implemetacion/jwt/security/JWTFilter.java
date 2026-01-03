package com.implemetacion.jwt.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
    Esta clase representa un filtro que sera agregado al SecurityFilterChain. Este filtro se encarga de
    validar y autenticar los tokens JWT presentes en las solicitudes, permitiendo así el acceso a
    los recursos protegidos solo a los usuarios autenticados.
    El filtro sera agregado antes del filto UsernamePasswordAuthenticationFilter.
*/
@Component
public class JWTFilter extends OncePerRequestFilter {
/*
    La clase OncePerRequestFilter es una implementacion de Filter que nos permite definir un filtro
    que solo se ejecutara una vez por cada solicitud.
    Extender de esta clase nos permite indicarle a spring que esta clase representa un filtro para
    poder agregarlo al SecurityFilterChain.
*/

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
