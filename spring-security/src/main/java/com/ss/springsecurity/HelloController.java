package com.ss.springsecurity;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        return "Hello World - " + "Id de sesion: " +request.getSession().getId();
    }

    @Autowired
    private UserServices services;


    @GetMapping("/usuarios")
    public List<User> getUsuarios() {
        return services.getUsuarios();
    }

    @PostMapping("/usuarios")
    public User save(@RequestBody User usuario) {
        return services.save(usuario);
    }

    // obtener la clave del token CSRF
    @GetMapping("/csrf")
    public CsrfToken getCsrf(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
