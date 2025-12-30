package com.ss.springsecurity.config;

import com.ss.springsecurity.repository.UserRepoitory;
import com.ss.springsecurity.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// para modificar el filter chain creamos una clase con la anotacion @Configuration
// y la anotacion @EnableSecurity para indicar que es una configuracion de seguridad

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // para modificar o crear una cadena de filtros (filter chain) utilizamos el metodo securityFilterChain()
    // el cual debe retornar un objeto tipo SecurityFilterChain.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // dentro de este metodo definimos los filtros de la cadena

        http
                // desabilitar autenticacion con token csrf
                .csrf(customizer -> customizer.disable())

                // configurar autorizaciones de requests
                .authorizeHttpRequests(auth -> {

                    // permitir acceso a un endpoint sin la necesidad de autenticacion
                    auth.requestMatchers(HttpMethod.GET, "/public/usuarios").permitAll(); // permite el acceso a GET /usuarios sin autenticacion
                    auth.requestMatchers(HttpMethod.POST, "/usuarios").permitAll();
                    // el metodo requestMatchers() tambien puede recibir una lista de endpoints
                    // si no especificamos el metodo http, se permite el acceso a todos los metodos

                    // permitir acceso a endpoint solo si el usuario tiene un permiso especifico
                    auth.requestMatchers("/auth/usuarios/read").hasAuthority("READ");
                    auth.requestMatchers("/auth/usuarios/admin").hasAuthority("ADMIN");

                    // requerir autenticacion para todos los demas endpoints
                    auth.anyRequest().authenticated();
                })


                // habilitar autenticacion mediante la pagina de login
                .formLogin(Customizer.withDefaults())


                // redireccionar a endpoint especifico una vez autenticado:
                .formLogin(Customizer -> {
                    Customizer.successHandler((request, response, authentication) -> {
                        response.sendRedirect("/redirect"); // una vez se autentica un usario, se lo redirige /redirect
                    });
                })


                // configuracion de politicas de sesiones
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS); // crear una politica de sesion
                    /*
                        sessionCreatePolicy() recibe como parametro una de las siguientes politicas de sesion:
                            - SessionCreationPolicy.ALWAYS: crea una nueva sesion siempre y cuando no exista ninguna, si ya existe una, la reutiliza
                            - SessionCreationPolicy.IF_REQUIRED: (por defecto) crea una nueva sesion solo si es necesario
                            - SessionCreationPolicy.NEVER: no crea nuevas sesiones, solo usa sesiones existentes
                            - SessionCreationPolicy.STATELESS: no crea ni utiliza ningun tipo de sesión
                    */
                })


                // permitir enviar credenciales en el header de la request
                // utilizado solo cuando se autentica con usuario y contraseña
                // util para habilirtar autenticacion por clientes como postman
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


    // definimos el servicio para codificar contraseñas
    @Bean
    PasswordEncoder passwordEncoder() {
        // BCrtypt es el algoritmo que codificara la contraseña antes de guardarla en la base de datos
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager es el encargado de gestionar los proveedores de autenticacion
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) {
        return http.getSharedObject(AuthenticationManager.class); // retorna el AuthenticationManager configurado en el filter chain
    }


    // definir authentication provider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // DaoAuthenticationProvider es la implementacion de AuthenticationProvider que utiliza un repositorio para autenticar usuarios
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder()); // definimos el codificador de contraseñas para autenticar con la base de datos
        // en este caso utilizamos BCryptPasswordEncoder
        // el proveedor de autenticacion utiliza el codificador para comparar la contraseña ingresada con la almacenada en la base de datos

        return provider;
    }


    @Bean
    public UserDetailsService userDetailsService() {
        // el UserDetailService es el servicio que nos permite cargar los detalles de un usuario
        // y compararlos con las credenciales ingresadas por el usuario
        return new UserDetailsServiceImpl();
    }

}
