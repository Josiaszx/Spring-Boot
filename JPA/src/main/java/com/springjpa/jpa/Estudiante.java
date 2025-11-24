package com.springjpa.jpa;

import jakarta.persistence.*;

// La clase estudiante representará una fila de una tabla en nuestra base de datos
@Entity // definimos a la clase como una entidad
@Table(name = "estudiantes")
public class Estudiante {

    @Id // definimos la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) //@GenenratedValue define como seran generados los valores de id
    // strategy = GenerationType.IDENTITY --> La BD genera el ID automáticamente (columna AUTO_INCREMENT en MySQL)
    private Integer id;

    @Column( // @Column nos permite definir propiedades del campo
            name = "e_nombre", // el nombre de la columna dentro de la tabla es e_nombre
            length = 20 // la longitud del string sera de 20, en la tabla --> varchar(20)
    )
    private String nombre;


    private String apellido;

    @Column(
            unique = true // definimos que la columna debe tener valore unicos
    )
    private String email;

    public Estudiante(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public Estudiante() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Se agrego el siguiente estudiante: \n" +
                "id: " + id +
                "\nnombre: " + nombre +
                "\napellido: " + apellido  +
                "\nemail: " + email;
    }
}
