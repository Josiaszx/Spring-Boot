package com.exepciones.manejodeerrores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// con @RestControllerAdvice indicamos que la clase sera un manjedor de exepciones
@RestControllerAdvice
public class GlobalExceptionHandler {

    // con @ExceptionHandler indicamos que la funcion manejara las exepciones que le pasemos como parametro
    @ExceptionHandler(UserNotFoundException.class)
    // esta funcion manejara todas las exepciones de tipo UserNotFoundException
    public ResponseEntity<String> userNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> arithmeticException(ArithmeticException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR ARITMETICO:  " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("HA OCURRIDO UN ERROR");
    }
}
