# Spring Boot desde cero

Spring Boot es un modulo de Spring Framework, de código abierto basado en Java que nos permite crear aplicaciones autónomas acelerando y simplificando el desarrollo de microservicios y aplicaciones web.

Para crear una plantilla de aplicación con spring boot podemos usar SpringInitializr.

🔗 [SpringIntilizr](https://start.spring.io/) |
⚙️ [Guia SpringInitilizr](Anotaciones%20de%20Teoria/SpringInitializr_Guía.md) |
⬆️ [Desplegar Proyecto Desde Consola](Anotaciones%20de%20Teoria/Desplegar-Proyecto-Desde-CMD.md) |
📂 [Organizacion de Proyectos](Anotaciones%20de%20Teoria/14-Organización-de-Proyectos.md)

## ✅ Finalidad
Este repositorio tiene la finalidad de guardar notas, apuntes e implementacion en codigo en Spring Boot a medida que voy avanzando y aprendiendo sobre la herramienta.
El contenido esta enfocado mayormente al desarrollo de APIs REST implementando tambien otras tecnologias basadas tambien en Spring como Spring Data JPA y Spring Security. 

## 📗 Indice de temas

1. [Inyecion de dependecias](#inyeccion-de-dependencias)
2. [Anotacion @Values](#@values)
3. [Sping profiles](#spring-profiles)
4. [REST](#rest-con-spring-boot)
5. [Spring data JPA](#spring-data-jpa)
6. [Testing unitario con JUnit y Mockito](#testing-unitario-con-junit-y-mockito)
7. [Spring Security](#spring-security)
8. [Manejo de exepciones](#manejo-de-exepciones)
9. [Extras y complementos](#extras-y-complementos)

## 📗 Temas
### Inyeccion de dependencias
- [Teoria](Anotaciones%20de%20Teoria/01-Inyección-de-Dependencias.md)
- [Implemetacion](basicos/src/main/java/com/primerproyecto/basicos)

### @Values
- [Teoria](Anotaciones%20de%20Teoria/02-Values.md)
- [Implemetacion](basicos/src/main/java/com/primerproyecto/basicos/PrimerServicio.java)

### Spring profiles
- [Teoria](Anotaciones%20de%20Teoria/03-Spring-Profiles.md)
- [Implemetacion](spring-profiles/src/main/java/com/profiles/springprofiles)

### REST con spring boot
- [Clase Controller](Anotaciones%20de%20Teoria/04-Clase-Controlador.md)
- [@GetMaping](Anotaciones%20de%20Teoria/05-Get-Mapping.md)
- [@PostMapping + @RequestBody](Anotaciones%20de%20Teoria/06-Post-Mapping.md)
- [@PathVariable](Anotaciones%20de%20Teoria/07-Path-Variable.md)
- [@RequestParam](Anotaciones%20de%20Teoria/08-Request-Param.md)
- [Implemetacion](REST-basics/src/main/java/com/rest/restbasics/FirstController.java)

### Spring Data JPA
- [Teoria](Anotaciones%20de%20Teoria/09-JPA-&-Hibernate.md)
- [@Entity](Anotaciones%20de%20Teoria/10-Entidades.md)
- [Repositorios](Anotaciones%20de%20Teoria/11-Repositorios.md)
- [Relaciones entre entidades](Anotaciones%20de%20Teoria/12-Relaciones-entre-entidades.md)
- [DTOs](Anotaciones%20de%20Teoria/13-DTOs.md)
- [Implemetacion](JPA/src/main/java/com/springjpa/jpa)

### Testing Unitario con JUnit y Mockito
- [Teoria](Anotaciones%20de%20Teoria/15-Testing.md)
- [Implemetacion](testing/src/test/java/com/testing/testing)

### Paginacion de respuestas
- [Teoria](Anotaciones%20de%20Teoria/16-Paginación-de-respuestas.md)
- [Implementacion]()

### Spring Security
- [Security filter chain](Anotaciones%20de%20Teoria/17-Security-Filter-Chain.md)
- [Flujo de autenticacion de usuarios](Anotaciones%20de%20Teoria/19-Flujo-de-Autenticación-de-Usuarios.md)
- [Implementacion](spring-profiles/src/main/java/com/profiles/springprofiles)

### Manejo de exepciones
- [Teoria](Anotaciones%20de%20Teoria/18-Manejo-de-Exepciones.md)
- [Implementacion](manejo-de-errores/src/main/java/com/exepciones/manejodeerrores)

### Extras y complementos
- [REST teoria](Anotaciones%20de%20Teoria/REST.md)
- [HTTP teoria](Anotaciones%20de%20Teoria/HTTP.md)
- [Paginacion de respuestas](Anotaciones%20de%20Teoria/16-Paginación-de-respuestas.md)