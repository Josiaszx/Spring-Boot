# Path Variable

La anotación `@PathVariable` nos sirve para obtener variables directamente desde la URL sin la necesidad de agregar operadores extra. Para esto primero en nuestro `@GetMapping` agregamos el endpoint esperado entre llaves para indicar que sera una variable, luego agregamos la notation `@PathVariable` dentro del parámetro de nuestro método y en la URL ingresamos el valor de la variable como si fuera una dirección normal.

**Ejemplo:**

```java
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

		// definimos el endpoint entre llaves
    @GetMapping("/{id}")
    // agregamos la notacion de @PathVariable seguido del parametro
    public String obtenerUsuario(@PathVariable int id) {
        return "Usuario con ID: " + id;
    }
}
```

Con esto, si ingresamos a la dirección:

```yaml
http://localhost:8080/usuarios/15
```

Obtendremos el mensaje: 

`Usuario con ID: 15`

## Multiples variables

Para agregar multiples variables, agregamos mas variables en el endpoint y agregamos mas parámetros como `@PathVariable` .

**Ejemplo:**

```java
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

		// Agregamos mas variables en el endpoint
    @GetMapping("/{id}/{nombre}")
    // agregamos mas parametros
    public String obtenerUsuario(
					    @PathVariable int id
							@PathaVariable String nombre
		) {
        return "Usuario con ID: " + id + " - " + nombre;
    }
}
```

Con esto, si ingresamos a la dirección:

```yaml
http://localhost:8080/usuarios/15/Juan
```

Esto nos mostrara: 

`Usuario con ID: 15 - Juan`

💬 Tambien podemos identificar variables agregando entre paréntesis a `@PathVariable` el nombre especifico de la variable en el endpoint.

**Ejemplo:**

```java
    @GetMapping("/usuarios/{nombre}")
    public String usuario(
				@PathVariable("nombre") String nombre
	   ){
        return "El usuario es: "+nombre;
    }
```