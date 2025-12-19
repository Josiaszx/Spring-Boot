package com.ss.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserRepoitory usuarios;

    public List<User> getUsuarios() {
        return usuarios.findAll();
    }

        // codificar contraseñas con bcrypt
        private BCryptPasswordEncoder BCrypt =  new BCryptPasswordEncoder();

        public User save(User usuario) {
            // obtener contraseña y codificarla
            String originalPassword = usuario.getPassword();
            String encodedPassword = BCrypt.encode(originalPassword);

            // cambiar contraseña antes de guardar en bd
            usuario.setPassword(encodedPassword);

            // guardar usuario con contraseña modificada
            var val = usuarios.save(usuario);
            return usuario;
        }
}
