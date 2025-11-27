package com.springjpa.jpa;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Colegio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            unique = true,
            length = 50,
            nullable = false
    )
    private String nombre;

    @Column(
            unique = true,
            length = 50,
            nullable = false
    )
    private String email;


    // relacion con Estudiante
    // @OneToMany() ... relacion de Colegio con Estudiantes es 1-n
    @OneToMany(
            mappedBy = "colegio"
    )
    @JsonManagedReference // Instructiva para deserializar la lista de estudiantes
    private List<Estudiante> estudiantes;



    // constructores y metodos

    public Colegio() {}

    public Colegio(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
