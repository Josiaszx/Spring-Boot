package com.exepciones.manejodeerrores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findbyId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(UserNotFoundException::new); // si no encuentra el usuario en la bd, lanza el error

        return usuario;
    }


}
