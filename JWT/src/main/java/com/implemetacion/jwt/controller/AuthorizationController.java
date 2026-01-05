package com.implemetacion.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authorization")
public class AuthorizationController {

    // Los endpoints seran accesibles dependiendo de los roles asignados a cada usuario
    @GetMapping("/admin")
    public String admin() {
        return "Vista de administrador";
    }

    @GetMapping("/user")
    public String user() {
        return "Vista de usuario";
    }

}
