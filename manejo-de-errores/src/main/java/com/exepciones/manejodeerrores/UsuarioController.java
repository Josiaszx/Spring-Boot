package com.exepciones.manejodeerrores;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    // lanza error UserNotFoundException si no encuentra el usuario
    public Usuario findById(@PathVariable Integer id) {
        return service.findbyId(id);
    }

    @GetMapping("/err/dividir/{num1}/entre/{num2}")
    // lanza error aritmetico en /usuarios/err/dividir/{num1}/entre/0
    public String error(@PathVariable int num1, @PathVariable int num2) {
        return num1 + " / " + num2 + "=" + num1 / num2;
    }
}
