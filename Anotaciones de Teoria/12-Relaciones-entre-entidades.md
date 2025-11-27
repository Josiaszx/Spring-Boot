# Relaciones entre entidades

Para formar una relación entre dos clases ambas tienen que estar definidas como entidades con sus respectivos Ids. 

Las relaciones pueden ser:

- **Unidireccionales:** cuando una sola entidad tiene conocimiento y acceso a la otra.
- **Bidireccionales:** cuando ambas entidades tienen conocimiento y acceso sobre al otra.

⚠️ En el caso de que la relación sea bidireccional y trabajemos con APIs REST, al querer serializar las entidades se formara un bucle infinito en le JSON (esto porque tenemos instancias de sus relaciones en ambas clases), para evitar eso podemos utilizar DTOs o agregar las anotaciones `@JsonMangedReference` (define la clase “principal o padre”) y `@JsonBackReference` (define la clase “secundaria o dependiente” y evita la serialización de la referencia de la clase padre).

# Relación 1:1 (uno a uno)

### Relación bidireccional

Si tenemos dos clases, Estudiante y Perfil, y queremos relacionarlas entre si, hacemos lo siguiente:

**En Estudiante:**

```java
@Entity
Public Class Estudiante {
	// ... definimos el id y los atributos 
	
	@OneToOne(
		mappedBy = "estudiante", // nombre de la instancia de Estudiante en el objeto de tipo Perfil
		cascade = CascadeType.ALL // indicaciones para Hibernate
	)
	@JsonManagedReference // (si no usamos DTO) evitar recusion a la hora de deserializar el objeto y definir la clase principal de la relacion
	private Perfil perfil; // instancia relacionada al estudiante
	
	// ... getters y setters
}
```

**En Perfil:**

```java
Public Class Perfil {
	// ... definimos id y atributos 
	@OneToOne
	@JoinColumn(name = "perfil") // nueva columna de clave foranea
	@JsonBackReference // (si no usamos DTO) evitar recusion a la hora de deserializar el objeto y definir la clase secundaria de la relacion
	private Estudiante estudiante; // instancia relacionada al perfil
	
	// ... getters y setters
}
```

### Relación unidireccional

Si tenemos dos clases, Estudiante y Perfil, y queremos relacionarlas entre si, hacemos lo siguiente:

**En Estudiante:**

```java
@Entity
Public Class Estudiante {
	// ... definimos el id y los atributos 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(
		name = "perfil_id", // nombre de la columna de relacion (para clave foranea)
		referencedColumnName = "id" // nombre de la columna relacionada en Perfil
	)

	private Perfil perfil; // instancia relacionada al estudiante
	
	// ... getters y setters
}
```

**En Perfil:**

```java
Public Class Perfil {
	// ... definimos id y atributos 	
	// ... getters y setters
}
```

## Relación 1:n (uno a muchos)

Si tenemos dos clases, Estudiante y Colegio, y queremos relacionarlas entre si, hacemos lo siguiente:

### Relación bidireccional

**En Estudiante:**

```java
@Entity
Public Class Estudiante {
	// ... definimos el id y los atributos 
	
	@ManyToOne
	@JoinColumn(name = "colegio_id")
	@JsonManagedReference
	private Colegio colegio; // instancia relacionada al estudiante
	
	// ... getters y setters
}
```

**En Colegio:**

```java
@Entity
Public Class Colegio {
	// ... definimos el id y los atributos 
	
	@OneToMany(
		mappedBy = "colegio", // nombre de la instancia de Colegio en Estudiante
	)
	@JsonManagedReference
	private List<Estudiante> estudiantes; // lista de los estudiantes relacionados al colegio
	
	// ... getters y setters
}
```

### Relación unidireccional

**En Estudiante:**

```java
@Entity
Public Class Estudiante {
	// ... definimos el id y los atributos
	 
	@JoinColumn(name = "colegio_id")
	
	// ... getters y setters
}
```

**En Colegio:**

```java
@Entity
Public Class Colegio {
	// ... definimos el id y los atributos 
	
	@OneToMany
	@JsonManagedReference
	private List<Estudiante> estudiantes; // lista de los estudiantes relacionados al colegio
	
	// ... getters y setters
}
```