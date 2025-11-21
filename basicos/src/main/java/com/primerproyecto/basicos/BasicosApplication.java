package com.primerproyecto.basicos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicosApplication {

    public static void main(String[] args) {

        // capturamos el contexto de la aplicacion (Application Context)
        // uso var para no escribir AnnotationConfigServletWebServerApplicationContext (tipo de objeto de var)
        var ctx = SpringApplication.run(BasicosApplication.class, args);

        // capturamos el componente PrimerComponente
        var primerComponente = ctx.getBean(PrimerComponente.class);

        // Capturamos el componente PrimerServicio
        // spring inyectara automaticamente todas las dependencias necesarias, en este caso, una instancia de PrimerServicio
        var primerServicio = ctx.getBean(PrimerServicio.class);

        // llamando metodos de los componentes
        System.out.println(primerComponente.saludo());
        System.out.println(primerServicio.saludo());

        System.out.println(primerServicio.getNombreProyecto()); // valor obtenido de application.properties con @Value()
        System.out.println(primerServicio.getPropiedad()); // valor de custom.properties

        System.out.println(primerServicio.getOS()); // valor obtenido con la interfaz environment
        System.out.println(primerServicio.getJavaVersion()); // valor obtenido con la interfaz environment


    }

}
