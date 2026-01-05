# Implementación de JWT en spring security

[Explicacion de JWT](./JWT.md) 

## Dependencias necesarias
<details >
<summary> Dependencias </summary>
Antes de poder implementar JWT, debemos agregar las siguientes dependencias al pom.xml si usamos maven:

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.13.0</version>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.13.0</version>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.13.0</version>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-root</artifactId>
    <version>0.13.0</version>
    <type>pom</type>
</dependency>

```

o si usamos gradle:
```gradle
dependencies {
    implementation 'io.jsonwebtoken:jjwt-api:0.13.0'
    runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.13.0'
    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.13.0'
}
```
</details>

## Implementación

Para poder implementar JWT en nuestro proyecto con spring security, debemos seguir los siguientes pasos:

### 1. Crear clases necesarias

Para poder implementer JWT primero debemos crear las siguientes clases:

- `JwtUtils`: Clase con métodos para guardar, procesar y validar tokens.
- `AuthTokenFilter`: Clase definida como filtro para interceptar las solicitudes y validar tokens.
- `AuthEntryPointJwt`: Clase para manejar solicitudes no autorizadas.

### 2. Clase JwtUtils

```java
package com.implemetacion.jwt.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JWTUtils {

	// los valores de los campos con @Values() se definen en application.properties o application.yaml
    @Value("${spring.app.jwtSecret}")
    private String secretKey;

    @Value("${spring.app.jwtExpiraionMs}")
    private long expirationMs;

    // extraer el jwt del header de la solicitud http
    public String extractJwt(HttpServletRequest request) {
        var bearerToken = request.getHeader("Authorization"); // token en formato Bearer <token>

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // extraer el token "limpio" sin el prefijo "Bearer "
        }

        return null;
    }

    // generar jwt a partir de un usuario
    // en este caso se generara el token a partir del email
    public String genereteToken(UserDetails usuario) {
        String email = usuario.getUsername();
        return Jwts.builder()
                .subject(email) // sub: email del usuario
                .issuedAt(new Date()) // iat: fecha de creacion del token
                .expiration(new Date(new Date().getTime() + expirationMs)) // exp: fecha de expiracion del token
                .signWith(key()) // firma del token
                .compact();
    }

    // obtener email desde un token
    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // crear key para validar/firmar tokens
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    // validar token
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parseSignedClaims(authToken);
            return true;
        } catch (
                MalformedJwtException |
                ExpiredJwtException |
                UnsupportedJwtException |
                IllegalArgumentException e
        ) {
            return false;
        }
    }
}

```

### 3. Clase JwtFilter

Para poder autenticarnos con JWT, primero debemos crear un filtro para posteriormente agregarlo al `SecurityFilterChain`.

**Crear filtro:**

```java
package com.implemetacion.jwt.security;

import com.implemetacion.jwt.security.implementations.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JWTFilter extends OncePerRequestFilter {

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

```

💬 `OncePerRequestFilter` es una clase que hereda una de una implementación de la interfaz `Filter` (la interfaz `Filter` es lo que spring necesita para saber que una clase es un filtro de seguridad) que nos permite definir un filtro que solo se ejecutara una vez por cada solicitud. Extender de esta clase nos permite indicarle a spring que esta clase representa un filtro para poder agregarlo al `SecurityFilterChain`.

### 4. Clase AuthEntryPoint

La clase `AuthEntryPoint` se encargara de manejar excepciones de autenticación.

**Implementación:**

```java
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

```

💬 La interfaz `AuthenticationEntryPoint` permite que permite definir como Spring Security responderá cuando un usuario no autenticado intenta acceder a un recurso protegido. En este caso hacemos una implementación propia para manejar el comportamiento.

### 5. Configurar el filter chain

Configuramos el `SecurityFilterChain` de la siguiente manera:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(AbstractHttpConfigurer::disable) // desactivamos el filtro csrf

            // definimos las rutas que no requieren autenticacion
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/api/auth/signin").permitAll(); // la ruta /api/auth/signin es publica
                auth.anyRequest().authenticated();
            })

            // configuramos la politica de sesion en STATLESS porque es necesaria para JWT
            .sessionManagement(session -> {
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })

            /* configuramos el handler de excepciones para que maneje excepciones de autenticacion con la clase AuthEntryPointJwt
                que creamos manualmente (es necesario inyectar la instancia de AuthEntryPointJwt en la clase)*/
            .exceptionHandling(exception -> {
                exception.authenticationEntryPoint(unauthorizedRequestsExceptionHandler);
            })

            // agregamos el filtro JwtAuthenticationFilter antes del filtro UsernamePasswordAuthenticationFilter
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

            .httpBasic(Customizer.withDefaults())
            .build();
}
```

### 6. Configuraciones de seguridad extra

En cualquier clase de configuración anotada con `@Configuration`:

```java
// codificador de contraseñas
@Bean
PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

// bean para obtener el authentication manager implementado
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
    return configuration.getAuthenticationManager();
}
```