package com.primerproyecto.basicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@PropertySource("classpath:custom.properties")
@Service
public class PrimerServicio {

    // dependecia manejada por spring
    final PrimerComponente primerComponente;

    // inyeccion de dependecia mediante constructor (no se necesita @Autowired)
    public PrimerServicio(PrimerComponente primerComponente){
        this.primerComponente = primerComponente;
    }


    // metodo del componente
    public String saludo() {
        return "Dependencia de PrimerServicio: " + primerComponente.saludo();
    }


    // con value accedemos a valores del archivo application.properties
    // para encontrar el valor le pasamos su clave asociada como: ${nombre_clave}
    // en este caso obtendra el valor de la clave spring.application.name y se lo asignara a nombreProyecto
    @Value("${spring.application.name}")
    private String nombreProyecto;

    public String getNombreProyecto() {
        return "Nombre de Proyecto: " + nombreProyecto;
    }

    // tambien podemos leer propiedades de otros archivos
    // para esto le indicamos a spring el nombre del archivo agregando por encima del nombre de la clase:
    // @PropertySource("classpath:NOMBRE_ARCHIVO.properties")
    // ¡EL ARCIVO SIEMPRE DEBE ESTAR EN LA CARPETA DE resources Y SIEMPRE DEBER TERMINAR EN .properties!
    @Value("${custom.propertie}") // le pasamos el nombre de la propiedad entre ${}
    private String propiedad;
    public String getPropiedad() {
        return "Propiedad desde custom.properties: " + propiedad;
    }


    // Environment es una interfaz que maneja datos del entorno de desarrollo en la que la aplicacion esta corriendo
    // tambien podemos obtener las propiedades de la aplicacion definidas en application.properties (igual que con @Value)
    private Environment env;

    // inyeccion por metodo setter (es necesario el @Autowired)
    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    public String getOS() {
        return "Sistema Operativo: " + env.getProperty("os.name"); // retorna el SO del sistema
    }

    public String getJavaVersion() {
        return "Version de Java: " + env.getProperty("java.version"); // retorna la version de java
    }



}
