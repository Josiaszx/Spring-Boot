package com.proyecto.organizacion.repositories;

import com.proyecto.organizacion.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {}
