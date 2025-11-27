package com.proyecto.organizacion.repositories;

import com.proyecto.organizacion.models.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Integer> {
    public List<Compra> findByUsuarioId(Integer id);
}
