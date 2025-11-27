package com.proyecto.organizacion.controllers;

import com.proyecto.organizacion.DTO.UsuarioDTO;
import com.proyecto.organizacion.models.Compra;
import com.proyecto.organizacion.models.Usuario;
import com.proyecto.organizacion.services.UsuarioServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    UsuarioServices usuarioServices;

    public UsuarioController(UsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

    @PostMapping("/usuarios")
    public Usuario save(@RequestBody Usuario usuario) {
        usuarioServices.save(usuario);
        return usuario;
    }

    @GetMapping("/usuarios")
    public List<UsuarioDTO> findAll() {
        return usuarioServices.findAll();
    }

    @GetMapping("usuarios/{id}")
    public UsuarioDTO findById(@PathVariable Integer id) {
        return usuarioServices.findById(id);
    }


    @GetMapping("/usuarios/{id}/compras")
    public List<Compra> getAllComprasById(@PathVariable Integer id) {
        return usuarioServices.getAllComprasById(id);
    }




}
