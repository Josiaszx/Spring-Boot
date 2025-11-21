package com.rest.restbasics;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController // con @RestController indicamos que la clase sera un clase que maneja peticiones http (controlador)
public class FirstController {

    // con GetMapping podemos manejar peticiones de tipo GET a un recurso dado
    @GetMapping("/usuarios")
    // al hacer una peticion htttp de tipo GET a la direccion /usuarios se ejecutara la siguiente funcion
    public String hello(){
        return "LISTA DE USUARIOS";
    }

    @GetMapping("/personas")
    // tambien podemos definir el codigo de estado en la respuesta de la peticion
    @ResponseStatus(HttpStatus.ACCEPTED) // el codigo de respuesta sera 202 - accepted
    // por defecto el metodo GET devuelve el codigo 200 - ok
    public String persona(){
        return "LISTA DE PERSONAS";
    }


    // con PostMapping podemos manejar peticiones de tipo POST a un recurso dado
    // al hacer una peticion http de tipo GET a la direccion /usuarios se ejecutara la siguiente funcion
    @PostMapping("/usuarios")
    public String nuevoUsuario(
            @RequestBody String mensaje
    ) {
        // con request @RequestBody indicamos que el endpoint dado espera un String como body de la peticion
        return "Peticion aceptada, el mensaje recibido fue: " + mensaje;
    }

    // para mappear el cuerpo de una solicitud POST dentro de un objeto java, le inidicamos a @RequesBody el formato
    // en el que espera los parametros con la clase del objeto dado y spring se encargara de deserializarlo
    @PostMapping("/clientes")
    public String nuevoCliente(
            @RequestBody Cliente cliente
    ) {
        return cliente.toString();
    }
    // para que el metodo funcione correctamente, los parametros del body de la funcion deberan coincidir con los parametros
    // de la clase Cliente


    // @PathVariable() ... para obtener valores directamente desde el endpoint
    @GetMapping("/usuarios/{nombre}") // definimos la variable esperada con {}
    public String usuario(
            @PathVariable String nombre // @PathVariable tomara el valor que se de dentro de {} al hacer una peticion en /usuarios/{nombre}
    ){
        return "El usuario es: "+nombre;
    }


    // @RequestParam() ... para obtener valores desde el endpoint desde parametros necesarios
    @GetMapping("/usuarios/param")
    public String usuario2(
            @RequestParam String nombre, // tomara nombre=valor
            @RequestParam String apellido // tomara apellido=valor
    ) {
        return "Usuario: " + nombre + " " + apellido;
    }
    // para pasar un RequestParam en la URL: /usuarios/param?nombre=valor&apellido=valor2
}
