package com.springjpa.jpa;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JPAController {

    // definimos un repositorio nuevo
    private final EstudianteRepository estudianteRepository;
    // spring automaticamente se encarga de la implementacion de los metodos

    public JPAController(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository; // inyeccion por constructor
    }

    // agregar un estudiante
    @PostMapping("/estudiantes")
    public Estudiante postEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteRepository.save(estudiante); // save() --> guarda o actualiza una entidad en el repositorio
    }

    // obtener todos los estudiantes
    @GetMapping("/estudiantes")
    public List<Estudiante> getEstudiantes(){
        return estudianteRepository.findAll(); // findAll() --> retorna una lista con todos las entidades del repositorio
    }

    // obtener un estudiante segun su id
    @GetMapping("/estudiantes/{id}")
    public Estudiante getEstudiante(@PathVariable Integer id){
        return estudianteRepository.findById(id).orElse(null); // findById() --> retorna una entidad según su id
        // con .orElse(null) indicamos que en caso de no encontrar el id solicitado retorne null
    }

    // obtener todos los estudiantes segun su nombre
    @GetMapping("/estudiantes/nombre/{nombre}")
    public List<Estudiante> getEstudiantesByName(@PathVariable String nombre){
        return estudianteRepository.findAllByNombre(nombre);
    }

    // obtener todos los estudiantes segun su nombre
    @GetMapping("/estudiantes/apellido/{apellido}")
    public List<Estudiante> getEstudiantesByApellido(@PathVariable String apellido){
        return estudianteRepository.findAllByApellido(apellido);
    }

    // eliminar estudiante segun su id
    @DeleteMapping("/estudiantes/{id}")
//    @ResponseStatus()
    public void del(@PathVariable Integer id){
        estudianteRepository.deleteById(id);
    }

    @GetMapping("/estudiantes/{id}/clases")
    public List<Clase> findClasesById(@PathVariable Integer id){
        var estudiante = estudianteRepository.findById(id).orElse(new Estudiante());
        return estudiante.getClases();
    }

    @PostMapping("/estudiantes/{id}/clases")
    public Estudiante save(@PathVariable Integer id, @RequestBody Clase clase){
        var estudiante = estudianteRepository.findById(id).orElse(new Estudiante());
        estudiante.getClases().add(clase);
        estudianteRepository.save(estudiante);
        return estudiante;
    }
}
