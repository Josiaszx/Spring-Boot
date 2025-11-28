package com.testing.testing;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServices {

    final private UsuarioRepository usuarioRepository;

    public UsuarioServices(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findById(Integer id) {
        return usuarioRepository.findById(Long.valueOf(id)).orElse(new Usuario());
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

}
