package com.springjpa.jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class PerfilEstudinte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    List<String> materias;


    @OneToOne
    @JoinColumn(
            name = "estudiante_id"
    )
    @JsonBackReference // instructiva para no deserializar el objeto estudiante (prevenir recursion infinita en el JSON)
    private Estudiante estudiante;



    // constructores y metodos

    public PerfilEstudinte(List<String> materias) {
        this.materias = materias;
    }

    public PerfilEstudinte() {}

    public List<String> getMaterias() {
        return materias;
    }

    public void setMaterias(List<String> materias) {
        this.materias = materias;
    }

    public void addMateria(String materia) {
        this.materias.add(materia);
    }
}
