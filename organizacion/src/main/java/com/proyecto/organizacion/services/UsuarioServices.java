package com.proyecto.organizacion.services;

import com.proyecto.organizacion.DTO.UsuarioDTO;
import com.proyecto.organizacion.models.Compra;
import com.proyecto.organizacion.models.Usuario;
import com.proyecto.organizacion.repositories.CompraRepository;
import com.proyecto.organizacion.repositories.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServices {

    UsuarioRepository usuarios;
    CompraRepository compras;

    public UsuarioServices(UsuarioRepository usuarios,  CompraRepository compras) {
        this.usuarios = usuarios;
        this.compras = compras;
    }

    public Usuario save(Usuario usuario) {
        return usuarios.save(usuario);
    }

    public List<UsuarioDTO> findAll() {

        List<Usuario> usuariosList =  usuarios.findAll();
        List<UsuarioDTO> usuarioDTOList = new ArrayList<>();

        for (Usuario usuario : usuariosList) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setNombre(usuario.getNombre());
            usuarioDTO.setApellido(usuario.getApellido());
            usuarioDTO.setEmail(usuario.getEmail());
            usuarioDTOList.add(usuarioDTO);
        }

        return usuarioDTOList;
    }

    public UsuarioDTO findById(Integer id) {
        Usuario usuario = usuarios.findById(id).orElse(null);
        if (usuario != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setNombre(usuario.getNombre());
            usuarioDTO.setApellido(usuario.getApellido());
            usuarioDTO.setEmail(usuario.getEmail());
            return usuarioDTO;
        }
        return null;
    }

    public Usuario findUsuarioById(Integer id) {
        return usuarios.findById(id).orElse(null);
    }


    public List<Compra> getAllComprasById(Integer id) {
        return compras.findByUsuarioId(id);
    }

}
