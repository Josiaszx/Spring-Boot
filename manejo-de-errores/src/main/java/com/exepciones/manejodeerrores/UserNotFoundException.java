package com.exepciones.manejodeerrores;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException() {
        super("ERROR: USUARIO NO ENCONTRADO");
    }
}
