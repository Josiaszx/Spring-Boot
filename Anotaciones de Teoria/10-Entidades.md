# Entidades

Una entidad (Entity) es una clase Java que representa una tabla en la base de datos, donde cada instancia representa una fila y cada parámetro representa una columna de la tabla.

Para definir una clase como entidad, usamos la anotación `@Entity` antes de definir la clase.

```java
@Entity 
public class Usuario{ ... }
// defininos a la clase Usuario como entidad
```

💬 Hibernate se encargara de mapear los registros de la tabla en objetos de tipo entidad para poder trabajar con ellos mediante java.

## Anotación @Id

Cuando definimos una entidad también debemos definir el Primary Key de dicha entidad, esto lo hacemos mediante la anotación `@Id`.

```java
@Entity 
public class Usuario{ 
	@Id // definie el campo como id de la tabla, por lo tanto tambien lo define como clave primaria
	private Integer id; // representa la columna ID de la tabla Usuario 
}
```

⚠️ Se recomienda utilizar las clases wrapper `Integer` y `Long` por sobre sus tipos primitivos para manejar los ids de las tablas, ya que estos pueden manejar valores `NULL` para evitar ambigüedades.

Al trabajar con ids de entidades, podemos definir como serán creados sus valores. Para lograr esto utilizamos la anotación `@GeneneratedValue` con el atributo `strategy`.

**Ejemplo:**

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) 
// GenerationType.IDENTITY --> La BD genera el ID automáticamente (columna AUTO_INCREMENT en MySQL)
private Integer id;
```

## Anotación @Table

Para relacionar una tabla con una entidad deben tener el mismo nombre, en caso de no tenerlo, usamos la anotación `@Table`, que nos permite definir el nombre de la tabla a mapear.

```java
@Entity 
@Table(name = "users") // definimos el nombre de la tabla de la bd como users
public class Usuario{ ... }
// defininos a la clase Usuario como entidad que representa a la tabla llamada users 
```

## Anotación @Column

`@Column` permite definir propiedades de cada columna de la tabla (que correspondería a cada parámetro del objeto).

**Ejemplo:**

```java
@Column(
	name = "u_name", // el nombre de la columna en la tabla es u_name
	length = 20, // la longitud del string sera de 20, en la tabla --> varchar(20)
	unique = true // la columna tendra valores que unicos
)
Private String columnaDeTabla; // representacion de la columna previamente definida
```

**Ejemplo de implementación:**

```java
@Entity
@Table(name = "users")  // Opcional si el nombre coincide
public class User {
    
    @Id //
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_name", nullable = false, length = 100)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    private int age;  // Por defecto crea columna "age"
    
    @Transient  // No se persiste en BD
    private String temporalData;
    
    // Constructores, getters y setters
}
```