package com.profiles.springprofiles;

import org.springframework.context.annotation.*;


@Configuration
public class ApplicationConfig {

    // configurar inyeccion de beans segun el perfil activo

    @Bean
    @Profile("dev") // se inyectar este bean si el perfil es dev
    public ProfileOptions bean1() {
        return new ProfileOptions("dev");
    }

    @Bean
    @Profile("test") // se inyectara este bean si el perfil es test
    public ProfileOptions bean2() {
        return new ProfileOptions("test");
    }

    @Bean
    @Profile("dev && test") // se inyectara este bean si los perfiles son dev y test
    @Primary
    public ProfileOptions bean3() {
        return new ProfileOptions("dev - test");
    }
}
