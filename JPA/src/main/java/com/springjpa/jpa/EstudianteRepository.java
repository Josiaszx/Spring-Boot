package com.springjpa.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// repositorio --> tabla
// al extender de JpaRepository no es necesario agregar la anotacion @Repository porque spring ya entiende que se trata de uno
// al extender JpaRepository<>
//      - Primero, le pasamos la entidad que representara una fila de la tabla
//      - Segundo, le pasamsos el tipo de dato que de nuestro ID dentro de la entidad.
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    // todos los metodos heredados seran implementados por spring en tiempo de ejecucion

    // Agregar metodos propios:
    // obtener todos los elementos segun su nombre
    List<Estudiante> findAllByNombre(String nombre);

    // obtener todos los estudiantes segun su apellido
    List<Estudiante> findAllByApellido(String apellido);

    // el metodo es implementado por spring automáticamente
}
