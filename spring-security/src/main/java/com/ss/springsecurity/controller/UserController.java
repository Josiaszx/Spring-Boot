package com.ss.springsecurity.controller;

import com.ss.springsecurity.entity.User;
import com.ss.springsecurity.service.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        return "Hello World - " + "Id de sesion: " +request.getSession().getId();
    }

    @Autowired
    private UserServices services;


    // endpoint accesible sin autenticacion
    @GetMapping("/public/usuarios")
    public List<User> getUsuarios() {
        return services.getUsuarios();
    }

    // endpoint accesible solo con autenticacion
    @GetMapping("/auth/usuarios")
    public List<User> getUsuariosWithAuthentication() {
        return services.getUsuarios();
    }

    // endpoint accesible solo con autorizacion READ
    @GetMapping("/auth/usuarios/read")
    public List<User> getUsuariosWithReadAuthorization() {
        return services.getUsuarios();
    }

    // endpoint accesible solo con autorizacion ADMIN
    @GetMapping("/auth/usuarios/adimn")
    public List<User> getUsuariosWithAdminAuthorization() {
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

    @GetMapping("/redirect")
    public String redirect() {
        return "PAGINA DE REDIRECCION AL AUTENTICARSE";
    }
}
