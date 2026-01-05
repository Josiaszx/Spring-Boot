package com.implemetacion.jwt.controller;

import com.implemetacion.jwt.model.LoginRequest;
import com.implemetacion.jwt.security.jwt.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Clase para manejar las solicitudes de autenticacion.
*/

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;

        try {
            // crear token de tipo Authentication (con la clase UsernamePasswordAuthenticationToken)
            var authToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            );
            System.out.println(authToken.getPrincipal());
            System.out.println(authToken.getCredentials());

            // delegar la autenticacion al AuthenticationProvider adecuado
            authentication = authenticationManager
                    .authenticate(authToken);

        } catch (AuthenticationException e) {
            // respuesta en caso de error de autenticacion
            Map<String, Object> map = new HashMap<>();
            map.put("error", "Bad credentials");
            map.put("status", HttpStatus.UNAUTHORIZED.value() + " " + HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return new ResponseEntity<Object>(map, HttpStatus.UNAUTHORIZED);
        }

        // guardar el token de autenticacion en el SecurityContext
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        // cargar datos del usuario en UserDetails
        var userDetails = (UserDetails) authentication.getPrincipal();

        // crear token para el usuario autenticado
        var jwtToken = jwtUtils.genereteToken(userDetails);

        // obtner roles del usuario
        var roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        // cuerpo de la respuesta
        HashMap<String, Object> response = new HashMap<>();
        response.put("email", userDetails.getUsername());
        response.put("roles", roles);
        response.put("token", jwtToken);

        return ResponseEntity.ok(response);
    }

}
