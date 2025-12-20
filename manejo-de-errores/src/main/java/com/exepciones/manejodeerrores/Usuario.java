package com.exepciones.manejodeerrores;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Usuario {

    @Id
    private Integer id;

    private String nombre;
    private String apellido;
    private String email;

}
