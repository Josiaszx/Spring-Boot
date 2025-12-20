package com.ss.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
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

        http
                // desabilitar autenticacion con token csrf
                .csrf(customizer -> customizer.disable())

                // requerir autencacion para cualquier request
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())

                // permitir acceso a un endpoint sin la necesidad de autenticacion
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/usuarios"))
                // el metodo requestMatchers() tambien puede recibir una lista de endpoints

                // requerir autenticacion y permitir acceso a endpoints en una sola funcion
//                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/usuarios").permitAll(); // permitir acceso a /usuarios sin autenticacion
//                    auth.anyRequest().authenticated(); // requerir autenticacion para los demas endpoints
//                })

                // habilitar autenticacion mediante la pagina de login
                .formLogin(Customizer.withDefaults())

                // para redireccionar a endpoint especifico una vez autenticado
                .formLogin(Customizer -> {
                    Customizer.successHandler((request, response, authentication) -> {
                        response.sendRedirect("/redirect"); // una vez se autentica un usario, se lo redirige /redirect
                    });
                })

                // configuracion de politicas de sesiones
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS); // crear una politica de sesion
                    // sessionCreatePolicy() recibe como parametro una de las siguientes politicas de sesion:
                    //      - SessionCreationPolicy.ALWAYS: crea una nueva sesion siempre y cuando no exista ninguna, si ya existe una, la reutiliza
                    //      - SessionCreationPolicy.IF_REQUIRED: (por defecto) crea una nueva sesion solo si es necesario
                    //      - SessionCreationPolicy.NEVER: no crea nuevas sesiones, solo usa sesiones existentes
                    //      - SessionCreationPolicy.STATELESS: no crea ni utiliza ningun tipo de sesión

                    // endpoint de redireccion en caso de error en la sesion
                    session.invalidSessionUrl("/login"); // si la sesion no se puede validar, se redirige a /login

                    // cantidad maxima de sesiones, por defecto: 1
                    session.maximumSessions(1); // util en aplicaciones multiplataforma que manejan muchas sesiones de un mismo usuario

                    // migrar sesiones para evirtar ataques de sesion
                    session.sessionFixation().migrateSession(); // implementado por defecto


                })

                // permitir enviar credenciales en el header de la request
                // util para habilirtar autenticacion por clientes como postman
                .httpBasic(Customizer.withDefaults());


        return http.build();
    }

}
