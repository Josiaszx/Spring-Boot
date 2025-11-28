package com.springjpa.jpa;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clases")
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30,  nullable = false, unique = true)
    private String nombre;

    @ManyToMany
    // definimos el @JoinTable en la clase "principal" de la relacion
    @JoinTable( // crea la tabla secundaria que se utilizara para poder realizar la relacion de muchos a muchos
            name = "clases_estudiantes", // nombre de la tabla secundaria
            joinColumns = @JoinColumn(name = "clase_id"), // definimos la columna de IDs de la entidad principal
            inverseJoinColumns = @JoinColumn(name = "estudiante_id") // definimos la columna de IDs de la entidad secundaria
    )
    List<Estudiante> estudiantes;

}
