# Clase Controlador

## Controlador

Un controlador es una clase que maneja solicitudes http y define como deben procesarse para generar una respuesta adecuada, esta respuesta pueden ser un texto plano o un archivo json, xml o html. Los controladores se definen usando anotaciones de java.

Los controladores normalmente los creamos en una carpeta llamada controllers y de nombre de clase tipo: `nombre_controladorController` .

Para indicar que una clase es de tipo controlador, agregamos la anotación `@RestController` arriba de la definición de la clase. Estos controladores deben estar en la misma carpeta o en una subcarpeta de la aplicación principal con el main. `@RestController` nos permite devolver archivos json, xml o txt como información a la vista (vista: presentación de la información).

Con esto definimos lo que se conoce como API Rest.

```java
import org.springframework.stereotype.RestController;

@RestController // Definimos a la clase como un controlador API Rest.
// con esto MyFirstController sera capaz de manejar peticiones HTTP
public class MyFirstController { ... }
```