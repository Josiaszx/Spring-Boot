# Repositorios

Un repositorio es la representación de una tabla al trabajar con JPA. Para definir un repositorio podemos hacerlo creando una interfaz de la siguiente forma:

```java
public interface Repositorio extends JpaRepository<Entidad, Integer> { ... }

```

Heredamos de `JpaRepository<>`, donde le pasamos como primer genérico la entidad que coleccionara el repositorio y como segundo el tipo de dato del Id de la entidad dada.

Hacemos esto porque spring se encargara de la implementación de los métodos para manejar el repositorio sin que nosotros tengamos que definirlos manualmente.

💬 Al extender de `JpaRepository<>` no es necesario usar la anotación `@Repository` ya que spring ya da por hecho de que se trata de uno y directamente lo transforma en un bean.

Ya que spring se encarga de la implementación de los métodos, algunos útiles para operar sobre nuestro repositorio son:

```java
// 1. save(T entidad) ... guarda o actualiza una entidad en el repositorio
Producto producto = new Producto();
producto.setNombre("Laptop");
producto.setPrecio(1500.0);

Producto guardado = productoRepository.save(producto); // retorna la entidad dada

// 2. saveAll(Iterable<T> entitidades) ... guarda o actualiza varias entidades en el repositorio
List<Producto> productos = Arrays.asList(
    new Producto("Laptop", 1500.0),
    new Producto("Mouse", 25.0)
);

List<Producto> guardados = productoRepository.saveAll(productos);

// 2. findById(Integer id) ... retorna una entidad segun su id
Optional<Producto> opcional = productoRepository.findById(1);

// 3. findAll() ... retorna una lista con todas las entidades
List<Producto> todos = productoRepository.findAll();

// 4. deleteById(Integer id) ... elimina una entidad del repositorio segun su id
productoRepository.deleteById(1);
```

## Generar métodos de consulta propios

Si queremos generar un método de consulta que no este definido en `JpaRepository<>` tenemos que definirlo en la interfaz creada anteriormente.

```java

public interface Repositorio extends JpaRepository<Entidad, Integer> {
	// obtener entidad segun un campo de la misma
	public Entidad findByCampo(Campo valor);
	// la implementacion de la funcion es hecha por spring automaticamente
}
```

⚠️ Se deben seguir algunas nomenclaturas específicas para que este método funcione.

Spring analiza el nombre de la función y genera una consulta apropiada.

**Ejemplo:**

```java
List<Producto> findByNombreAndPrecioGreaterThan(String nombre, Double precio);

/*
Spring divide el nombre del método en partes:
find          → Prefijo (tipo de operación)
By            → Separador
Nombre        → Campo de la entidad
And           → Operador lógico
Precio        → Otro campo
GreaterThan   → Comparador
*/ 
```

Y con eso genera la siguiente consulta:

```sql
SELECT p FROM Producto p 
WHERE p.nombre = :nombre 
AND p.precio > :precio
```