package com.ss.springsecurity.service;

import com.ss.springsecurity.repository.UserRepoitory;
import com.ss.springsecurity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserRepoitory usuarios;

    public List<User> getUsuarios() {
        return usuarios.findAll();
    }

    // para codificar contraseñas con bcrypt
//    BCryptPasswordEncoder BCrypt = new BCryptPasswordEncoder();

    // inyectamos el encoder (en este caso BCrypt)
    @Autowired
    private PasswordEncoder encoder;

    public User save(User usuario) {
        // obtener contraseña y codificarla
        String originalPassword = usuario.getPassword();
        String encodedPassword = encoder.encode(originalPassword);

        // cambiar contraseña antes de guardar en bd
        usuario.setPassword(encodedPassword);

        // guardar usuario con contraseña modificada
        usuario = usuarios.save(usuario);
        return usuario;
    }
}
