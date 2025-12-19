package com.ss.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// para modificar el filter chain creamos una clase con la anotacion @Configuration
// y la anotacion @EnableSecurity para indicar que es una

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // para modificar o crear una cadena de filtros
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // dentro de este metodo definimos los filtros de la cadena

        // desabilitar autenticacion con token csrf
        http
                .csrf(customizer -> customizer.disable())

                // requerir autencacion
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())


                // habilitar autenticacion mediante la pagina de login
                .formLogin(Customizer.withDefaults())

                // habilirtar autenticacion por clientes como postman
                .httpBasic(Customizer.withDefaults());



        return http.build();
    }

}
