# Values

La anotación `@Values` nos permite obtener valores de archivos de propiedades del proyecto, por ejemplo, podemos obtener valores del archivo `application.properties` para usarlas dentro del programa. 

**Ejemplo:**

Si tenemos en nuestro archivo `application.properties`:

```
custom.propertie=HOLA
```

Dentro de las clases del proyecto podemos obtener sus propiedades con:

```java
@Value("custom.propertie") // obtendra el valor de la clave dada y lo guardara en la variable siguiente
String propiedad; // propiedad = "HOLA"
```

## Obtener valores desde otro archivos de propiedades

Primero le indicamos a spring el nombre del archivo con la anotación `@PropertySource`, luego la buscamos con `@Value`.

⚠️ El archivo debe estar en la carpeta resources.

**Ejemplo:**

Si tenemos un archivo de propiedades personalizadas llamada `custom.properties`, donde guardamos:

```
name=Josias
```

para acceder a sus propiedades con `@Value`, le indicamos a spring su nombre agregando lo siguiente al principio de la clase:

```java
@PropertySource("classpath:custom.properties") // indicamos el nombre del archivo
public class Clase { ... }
```

Y luego simplemente accedemos a ellas con `@Value`:

```java
@Value("name") // obtendra el valor de name en custom.properties
String propiedadPersonalizada; // aqui se guardara el valor
```

## Trabajar con varios archivos de propiedades

Para trabajar con varios archivos, podemos indicarle a spring el nombre de todos los archivos con `@PropertySources` de la siguiente forma:

```java
@PropertySources({
	@PropertySource("classpath:custom.properties1"), // indicamos el nombre del archivo 1
	@PropertySource("classpath:custom.properties2"), // indicamos el nombre del archivo 2
	// ... 
	@PropertySource("classpath:custom.propertiesN"), // indicamos el nombre del archivo n
})
public class Clase { ... }
```

Simplemente le pasamos un array con los `@PropertySource` que queramos agregar.

⚠️ Todos los archivos deben estar en la carpeta sources.