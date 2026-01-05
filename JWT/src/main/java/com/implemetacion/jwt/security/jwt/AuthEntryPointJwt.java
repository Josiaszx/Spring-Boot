package com.implemetacion.jwt.security.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
    Esta clase maneja las respuestas de error de autenticacion para solicitudes no autorizadas.
    Implementa la interfaz AuthenticationEntryPoint de Spring Security, que define el metodo
    commence() que se invoca cuando un usuario no autenticado intenta acceder a un recurso protegido.
    En este metodo, se configura la respuesta HTTP para indicar que la solicitud no esta autorizada,
    incluyendo detalles del error en formato JSON.
    La interfaz AuthenticationEntryPoint permite manejar errores en la autenticacion desde
    los filtros de Spring Security.
*/

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {

        // configuramos la respuesta HTTP con JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // status 401 UNAUTHORIZED
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // cuerpo de la respuesta
        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", authException.getMessage());
        body.put("path", request.getServletPath());

        // mappear respuesta
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}
