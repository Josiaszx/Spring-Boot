# Get Mapping

Anotacion define metodos que manejan peticiones de tipo GET. Para declarar un método handler de peticiones GET, agregamos la anotación `@GetMapping("/end_point")`.

`@GetMapping()` define un end-point con permisos de GET.

```java
import org.springframework.stereotype.RestController;

@RestController
public class MyFirstController { 
		// metodo se ejecutara cuando el endpoint termine en /home
		@GetMapping("/home")
		public String metodo() {
				return "Hola mundo";
		}
}
```

Esto simplemente nos devolverá un Hola mundo impreso en la pagina cuando se ingrese a la dirección `/home`, si queremos devolver un JSON como respuesta, podemos definir un objeto tipo `Map` donde cargaremos la información y luego retornamos dicho map.

```java
import org.springframework.stereotype.RestController;

@RestController
public class MyFirstController { 
		@GetMapping("/home")
		public Map<String,Object> info(){
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("Nombre", "Server-1");
        respuesta.put("IP", "172.0.0.1");
        respuesta.put("SO", "Windows");
        return respuesta;
    }
}
```

Esto nos devolvera un JSON llamado respuesta con la informacion cargada al ingresar a la ruta `/home`.

**respuesta:**

```json
{
"Nombre": "Server-1",
"IP": "172.0.0.1",
"SO": "Windows"
}
```

Aquí podemos ver como funciona el patron de diseño MVC (Modelo Vista Controlador), siendo el map respuesta nuestro modelo, la clase como el controlador y el json retornado en respuesta como la vista.

💬 Tambien podemos retornar un objeto, donde los valores en el JSON serán los parámetros del objeto y los valores serán los valores de los parámetros.