package com.testing.testing;

import org.springframework.stereotype.Service;

// Clase para practicar prueba unitaria con JUnit y Mockito
@Service
public class UsuarioMapper {

    public UsuarioDTO UsuarioToUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setEmail(usuario.getEmail());
        return usuarioDTO;
    }

    public Usuario UsuarioDTOToUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEmail(usuarioDTO.getEmail());
        return usuario;
    }

}
