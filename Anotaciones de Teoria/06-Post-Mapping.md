# Post Mapping

La anotación `@PostMapping` nos permite definir métodos para manejar peticiones a endpoints con el método POST, para esto agregamos la notación `@PostMapping(”/recurso”)` y luego definimos el método que maneje dicha solicitud.

**Ejemplo:**

```java
@RestController // la clase deber estar definida como un controlador rest
public class UsuarioController {

		@PostMapping("/usuarioServices")
    public String crearUsuario() {
        return "Usuario creado correctamente.";
    }
}
```

Ahora, si hacemos una petición con el método POST al siguiente endpoint

```yaml
http://localhost:8080/usuarioServices
```

Si todo sale bien, el servidor recibirá la información y retornara 200 - OK con el siguiente mensaje: 

```
Usuario creado correctamente.
```

## Request Body

Con la anotación `@RequestBody`, le decimos a spring que el cuerpo o body de la petición debe tener un formato especifico, dicho formato debe poder convertirse en una instancia de un objeto dado. 

Con esto, lo que hara `@RequestBody` sera deserializar el body de la petición y convertirlo en un objeto de Java.

Para esto agregamos la anotación como parámetro en nuestro método junto con un objeto del tipo de dato cuyos parámetros requiramos deserializar del body de la petición.

Ejemplo:

```java
@RestController
public class UsuarioController {

		@PostMapping("/user")
    public String nuevaPersona(
            // el archivo resibido por el servidor debe de tener las propiedades de la clase Persona
            @RequestBody Persona persona
            // spring tomara los parametros del body de la solictud e interara crear un objeto tipo Persona apartir de dichos parametros
    ) {
        return "Operacion realizada correctamente.";
    }
}
```

- Spring intenta convertir el cuerpo de la petición (en el formato que sea) en un objeto tipo Persona en este caso (a este proceso se le llama deserialización).

Si un objeto de tipo Persona tiene las siguientes propiedades:

- `private String nombre`
- `private String apellido`
- `private int edad`

Debemos enviar una petición  en cuyo cuerpo (sea el formato que sea) deben estar definidos los mismos parámetros que los definidos en el objecto Persona junto con sus respectivos valores. 

⚠️ Los nombres de los parámetros en el cuerpo de la petición deben ser exactamente iguales a los de las propiedades del objeto.

⚠️ Si spring no encuentra una propiedad en el cuerpo de la petición para relacionar con una propiedad del objeto, por defecto le asignara a dicha propiedad el valor de `null` .

**Ejemplo:**

Si tenemos el siguiente JSON:

```json
{
"nombre" : "Juan",
"apellido" : "Lopez",
"edad" : 20
{
```

Y lo enviamos como body de una petición POST al siguiente endpoint:

```
http://localhost:8080/user
```

Usando el controlador definido anteriormente, si no ocurre ningún error, el servidor procesara correctamente la petición, porque los parámetros del cuerpo de la petición coinciden con los parámetros del objeto definido como `@RequestBody`, por lo tanto devolverá 200 - OK e imprimirá: 

```
Operación realizada correctamente.
```

⚠️ Para el proceso de deserialización es importante tener los getters y setters definidos en la clase en cuya instancia queramos deserializar el body de una petición.

## JsonProperty

Tambien podemos cambiar el nombre del parámetro esperado en el cuerpo de la petición agregando la anotación `@JsonProperty` antes de definir la variable en la clase que definiremos como `@RequestBody`.

```java
@JsonProperty("name")
String nombre;
// ahora el parametro en el cuerpo de la peticion debe llamarse name
```

💬 Tambien podemos definir como `@RequestBody` objetos de tipo Record.