package com.profiles.springprofiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SpringProfiles {


    @Value("${app.profile.name}")
    private String appName; // tomara el valor de la propiedad cuyo perfil sea el ultimo en activarse

    public String getAppName() {
        return appName;
    }

    final ProfileOptions profileOptions;
    public SpringProfiles(ProfileOptions profileOptions) {
        this.profileOptions = profileOptions; // se inyectara el valor segun el perfil activo
    }

    public String getProfileName() {
        return "Perfiles activos: " + profileOptions.getProfileName();
    }
}
