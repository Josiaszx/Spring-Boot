# Paginación de respuestas

Si tenemos una base de datos muy grande y queremos consultar una lista de usuarios, no es conveniente pedir todos los usuarios en una misma request por lo que utilizamos la paginación de respuestas.

Para paginar las respuestas del endpoint, en vez de retornar una clase de tipo `List<>`, retornamos una clase de tipo `Page<>`.

```java
// retorna todos los usuarios
public List<Usuario> findAll(){ ... }

// retorna los usuarios por paginas
public Page<Usuario> findAll(){ ... }
```

Para definir la pagina y la cantidad de elementos por pagina a retornar usamos la interfaz Pageable, de la siguiente forma:

```java
private Pageable pageable = PageRequest.of(pagina, cantidadElementos);
// PageRequest.of() ... crea una peticion paginada y espera la pagina a delver y la cantidad de elementos
// tambien se le puede agregar un parametro para definir el orden de los elementos
```

Luego, pasamos como parámetro este objeto Pageable al método `findAll()` de nuestro repositorio (heredado de `JpaRepository<>`) para recibir la información paginada.

**Ejemplo de Uso:**

Si tenemos una clase Usuario con su repositorio definido y que queremos devolver en forma paginada, **en la clase service:**

```java
public Page<Usuario> findAll(Pageable pageable) {
	return usuarioRepository.findAll(pageable)
}
```

**Y en el controlador:**

```java
@GetMapping("/usuarios")
public Page<Usuario> findAll(
			// esperamos los valores de paginacion por parametro en la url
			// si no se dan, definirlos con valores por defecto
			@RequestParam(defaultValue = "0") int pagina,
			@RequestParam(defaultValue = "10") int cantidadElementos
) {
	// crear el pedido paginado
	Pageable pageable = PageRequest.of(pagina, cantidadElementos);
	// retornar el resultado
	return usuarioService.findAll(pageable);
}
```