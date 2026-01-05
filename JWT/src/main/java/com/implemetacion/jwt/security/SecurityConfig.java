package com.implemetacion.jwt.security;

import com.implemetacion.jwt.security.jwt.AuthEntryPointJwt;
import com.implemetacion.jwt.security.jwt.JwtAuthenticationFilter;
import com.implemetacion.jwt.security.jwt.JWTUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tools.jackson.databind.json.JsonMapper;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {


    // las dependecias necesarias seran inyectadas autamaticamente por Spring gracias al @AllArgsConstructor
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private AuthEntryPointJwt unauthorizedRequestsExceptionHandler;

    // 1.  configuramos el SecurityFilterChain
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
                    que creamos manualmente */
                .exceptionHandling(exception -> {
                    exception.authenticationEntryPoint(unauthorizedRequestsExceptionHandler);
                })

                // agregamos el filtro JwtAuthenticationFilter antes del filtro UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .httpBasic(Customizer.withDefaults())
                .build();
    }

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

}
