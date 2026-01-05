# Manejo de excepciones
La anotación `@RestControllerAdvice` nos permite definir una clase como clase para manejo de errores, esto para no tener que estar agregando un try-catch controlador o servicio. Para esto agregamos la notation `@RestControllerAdvice` antes de la clase.

Tambien utilizaremos la anotación `@ExceptionHandler` para definir cada método para los tipos de errores que queremos manejar.

**Ejemplo:**

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // Con esto manejamos errores del tipo ArithmeticException
    @ExceptionHandler(ArithmeticException.class)
    public  ResponseEntity<String> handleArithmeticException(ArithmeticException e) {
        return new ResponseEntity<>("ERROR ARITMETICO", HttpStatus.BAD_REQUEST);
    }

    // Maneja NullPointerException en toda la app
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointer(NullPointerException ex) {
       return new ResponseEntity<>("ERROR DE DATO", HttpStatus.BAD_REQUEST);
    }

    // Maneja cualquier excepción genérica
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("ORCURRIO UN ERROR", HttpStatus.BAD_REQUEST);
    }
}
```

- `ResponseEntity` → Clase que representa toda la respuesta HTTP recibida, la utilizamos para devolver y mostrar los errores.

💬 Las clases definidas para manejo de errores con `@RestControllerAdvice` funicionan solo dentro del contexto del DispatcherServlet.