# Inyección de dependencias

Patron de diseño utilizado para manejar relaciones entre objetos de manera automática.

💬 **Dependencia:** Es cualquier clase u objeto que es necesario para que una clase funcione

La inyección de dependencias nos permite recibir todas las dependencias necesarias en un contenedor sin la necesidad de crearlas e instanciarlas manualmente porque spring se encarga de ello.

💬 Por defecto los beans inyectados por spring siguen el patron de diseño de singleton (crea una unica instancia de la clase e inyecta dicha instancia en todo el proyecto).

## Tipos de inyección de dependencias

### Inyección por constructor (recomendada)

Permite la inyección mediante el constructor. El constructor recibe las dependencias mediante sus parámetros.

**Funcionamiento:**

```java
@Service
public class UserServices {
		// primero definimos una constante del bean a inyectar
    final UsersRepo usersRepo;
    
		// definimos el constructor (spring se encargara de proveer los parametros necesarios
    public UserServices(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }
}
```

Uso recomendado para dependencias obligatorias de la clase.

### Inyección por campo (No recomendada)

Permite inyectar directamente en un campo de la clase. No recomendada porque dificulta el testing y no permite campos `final`.

Para inyectar directamente al campo utilizamos la anotación `@autowired`.

**Funcionamiento:**

```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
}
```

Solo se recomienda su uso para clases de prueba.

### Inyección por setters

Permite inyectar dependencias por medio de los setters de los campos de nuestra clase.

Tambien utilizamos la anotación `@autowired` .

**Funcionamiento:**

```java
@Service
public class UserService {
    private UserRepository userRepository; // campo a ser inyectado
    
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository; // inyeccion automatica por spring
    }
}
```

Recomendado para cuando trabajamos con dependencias opcionales.

## Estructura normal de proyecto

Normalmente trabajamos con estos tres directorios de proyecto:

📁 **Models** → Para clases que representan entidades de una base de datos, objetos,  etc.

- Ej.: Usuario, Persona, Empleado, etc.

📁 **Services** → Para clases que manejaran la lógica empresarial.

📁 **Repositorios** → Para clases que representaran un conjunto de modelos e interactúan con bases de datos.