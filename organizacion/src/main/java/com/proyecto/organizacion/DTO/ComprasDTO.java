package com.proyecto.organizacion.DTO;

public class ComprasDTO {

    private String usuario;

    private int precioTotal;

    public ComprasDTO(String usuario, int precioTotal) {
        this.usuario = usuario;
        this.precioTotal = precioTotal;
    }

    public ComprasDTO() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }
}
