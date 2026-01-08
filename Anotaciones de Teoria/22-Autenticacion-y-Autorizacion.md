# Autenticacion y Autorizacion

Dos de las funciones principales de spring security son la autenticación y la autorización.

- **Autenticación:** Definir quien es el usuario.
- **Autorización:** Definir que cosas puede o no hacer un usuario.

## Implementación

Para poder manejar estos conceptos, spring security define una interfaz llamada `Authentication` que nos permite manejar la información referente a un usuario. La implementación mas común es la clase `UsernamePasswordAuthenticationToken`, que es utilizada cuando se realiza autentificación mediante un nombre de usuario o email y una contraseña. En caso de crear una implementación propia, por convención de spring security, el nombre de la clase debería de terminar en `AuthenticationToken`. 

Los objetos tipo `Authentication` pueden representar:

- El resultado de una autentificación exitosa, es decir, un usuario autenticado.
- Una solicitud de autentificación.

### Vocabulario

- `Principal`: es la “identidad” del usuario (nombre, email, …).
- `GrantedAuthorities`: son los “permisos del usuario” (roles)

## SecurityContextHolder y SecurityContext

Una vez autentificado un usuario, la implementación de `Authentication` se guarda en un `SecurityContext` que es manejado por un `SecurityContextHolder` que se encarga de proporcionar acceso al `SecurityContext` a través de mecanismos de almacenamiento por hilo (ThreadLocal).

Se implementa de esta forma principalmente para:

- **Separar responsabilidades:** el `SecurityContext` solo maneja y almacena la información mientras que `SecurityContextHolder` gestiona el acceso y almacenamiento. De esta forma se cumple con el principio de responsabilidad unica.
- **Thread-Local Storage (principal razon):** permite cada hilo tenga su propio contexto y sea seguro para aplicaciones que manejan multiples usuarios de forma simultanea.

### Ventajas

1. **Transparencia**: No necesitas pasar el usuario como parámetro
2. **Thread-safe**: Cada hilo tiene su propia instancia
3. **Limpieza automática**: Spring limpia el ThreadLocal al final de cada request
4. **Testing sencillo**

Con el `SecurityContextHolder` podemos acceder los datos del usuario autenticado desde fuera de la capa de seguridad obteniendo el `ContextHolder` y luego obteniendo el objeto `Authentication`.

```java
// obtener Authentication de usuario 
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
// metodos:
auth.getPrincipal(); // retorna la informacion completa del usuario
auth.getName(); // retorna nombre de usuario o email (depende de la implementacion que hayamos utilizado)
auth.getCredentials(); // retorna las credenciales del usuario
auth.getAuthorities(); // retorna las autoridades del usuario
auth.getDetails(); // retorna informacion relacionada con la request

```

⚠️ Las credenciales del usuario solo se pueden obtener desde dentro de la capa de seguridad.