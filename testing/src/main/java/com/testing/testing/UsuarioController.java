package com.testing.testing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    private final UsuarioServices usuarios;

    public UsuarioController(UsuarioServices usuarios) {
        this.usuarios = usuarios;
    }

    @GetMapping("/usuarios")
    public List<Usuario> findAll() {
        return usuarios.findAll();
    }

    @PostMapping("/usuarios")
    public Usuario save(@RequestBody Usuario usuario) {
        return usuarios.save(usuario);
    }


    @GetMapping("/usuarios/{id}")
    public Usuario findById(@PathVariable Integer id) {
        return usuarios.findById(id);
    }

}
