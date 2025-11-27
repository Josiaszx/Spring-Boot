package com.proyecto.organizacion.controllers;

import com.proyecto.organizacion.DTO.ComprasDTO;
import com.proyecto.organizacion.DTO.PostCompraDTO;
import com.proyecto.organizacion.models.Compra;
import com.proyecto.organizacion.repositories.CompraRepository;
import com.proyecto.organizacion.services.CompraServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompraController {

    CompraServices compras;

    public CompraController(CompraServices compras) {
        this.compras = compras;
    }

    @PostMapping("/compras")
    public Compra save(@RequestBody PostCompraDTO compra) {
        return compras.save(compra);
    }

    @GetMapping("/compras")
    public List<ComprasDTO> findAll() {
        return compras.findAll();
    }


}
