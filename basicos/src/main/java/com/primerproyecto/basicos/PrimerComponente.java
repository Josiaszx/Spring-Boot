package com.primerproyecto.basicos;

import org.springframework.stereotype.Component;


@Component
public class PrimerComponente {

    public String saludo() {
        return "Hola Mundo desde PrimerComponente";
    }
}
