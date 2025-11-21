# Request Param

La anotación `@RequestParam` nos permite agregar parámetros necesarios en los métodos de los controladores, estos parámetros son pasados desde la URL. Para definir estos parámetros dentro de la URL, agregamos al final: `?parametro=valor`  donde parámetro es el nombre del parámetro y valor es su respectivo valor.

**Ejemplo:**

```java
@RestController
public class SaludoController {

    @GetMapping("/saludo")
    public String saludar(@RequestParam String nombre) {
        return "Hola " + nombre;
    }
}
```

- Con `@RequestParam` le decimos a spring que el parámetro `nombre` sera pasado por la URL.

Ahora, si ingresamos a la dirección:

```
http://localhost:8080/saludo?nombre=Juan
```

Obtendremos el mensaje: `Hola Juan`

## Varios Parámetros

Para pasar mas de un parámetro, simplemente agregamos mas parámetros como `@RequestParam` y en la URL concatenamos con `&` .

Ejemplo:

```java
@RestController
public class SaludoController {

    @GetMapping("/saludo")
    public String saludar(
						    @RequestParam String nombre,
						    @RequestParam String apellido
		) {
        return "Hola " + nombre + " " + apellido;
    }
}
```

Con esto, si ingresamos a:

```powershell
http://localhost:8080/saludo?nombre=Juan&apellido=Lopez
```

Obtendremos el mensaje: `Hola Juan Lopez`

## Parámetros Opcionales

Para definir que un parámetro sea opcional, definimos un valor por defecto en caso de que no se defina en la URL. Para esto agregamos `(defaultValue = "valor_por_defecto")` luego de la notación de `@RequestParam` .

⚠️ Siempre se debe pasar dicho valor por defecto como string sin importar el tipo de dato que sea.

Ejemplo:

```java
@RestController
public class SaludoController {

    @GetMapping("/saludo")
    public String saludar(
						    @RequestParam String nombre,
						    @RequestParam String apellido,
						    @RequestParam (dafaultValue = "1") int edad
		) {
        return "Hola " + nombre + " " + apellido + ", tienes " + edad + " años";
    }
}
```

Con esto si ingresamos a:

```powershell
http://localhost:8080/saludo?nombre=Juan&apellido=Lopez
```

Sin definir el valor de edad, obtendremos: `Hola Juan Lopez, tienes 1 años.`

## Nombre diferente al parámetro

Si necesitamos que el nombre de la variable en la URL sea diferente al nombre del parámetro en el controlador agregamos: `(name = "nombre_alternativo")` luego de la notación de`@RequestParam` .

**Ejemplo:**

```java
@RestController
public class SaludoController {

    @GetMapping("/saludo")
    public String saludar(@RequestParam (name = "nombre_usuario" String nombre) {
        return "Hola " + nombre;
    }
}
```

Con esto si ingresamos a la URL con `nombre_usuario` en vez de `nombre`:

```powershell
http://localhost:8080/saludo?nombre_usuario=Juan
```

Obtendremos: `Hola Juan`