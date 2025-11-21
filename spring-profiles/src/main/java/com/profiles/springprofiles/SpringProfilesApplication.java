package com.profiles.springprofiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class SpringProfilesApplication {

    public static void main(String[] args) {

        var app = new SpringApplication(SpringProfilesApplication.class);

        // agregar los perfilse dev y test (en ese orden)
        app.setAdditionalProfiles("dev", "test");

        // inyectar el bean de SpringProfiles
        var springProfiles = app.run(args).getBean(SpringProfiles.class);

        System.out.println(springProfiles.getAppName()); // nombre - test env
        System.out.println(app.getAdditionalProfiles()); // [dev, test]
        System.out.println(springProfiles.getProfileName()); // Perfiles activos: dev - test
    }

}
