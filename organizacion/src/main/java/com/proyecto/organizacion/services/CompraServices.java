package com.proyecto.organizacion.services;

import com.proyecto.organizacion.DTO.ComprasDTO;
import com.proyecto.organizacion.DTO.PostCompraDTO;
import com.proyecto.organizacion.models.Compra;
import com.proyecto.organizacion.models.Usuario;
import com.proyecto.organizacion.repositories.CompraRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompraServices {



    CompraRepository compras;
    UsuarioServices usuarioServices;

    public CompraServices(CompraRepository compras, UsuarioServices usuarioServices) {
        this.compras = compras;
        this.usuarioServices = usuarioServices;
    }

    public List<ComprasDTO> findAll() {

        List<Compra> comprasList = compras.findAll();
        List<ComprasDTO>  comprasDTOList = new ArrayList<>();

        comprasList.forEach(compra -> {
            ComprasDTO comprasDTO = new ComprasDTO();
            comprasDTO.setUsuario(compra.getUsuario().getNombre());
            comprasDTO.setPrecioTotal(compra.getPrecioTotal());
            comprasDTOList.add(comprasDTO);
        });

        return comprasDTOList;
    }

    public List<Compra>  findAllCompras() {
        return compras.findAll();
    }

    public Compra save(PostCompraDTO compraDTO) {
        Compra compra = new Compra();
        compra.setPrecioTotal(compraDTO.getPrecio());

        Usuario usuario = usuarioServices.findUsuarioById(compraDTO.getIdUsuario());
        compra.setUsuario(usuario);

        return compras.save(compra);
    }

}
