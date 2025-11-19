# SpringInitializr Guía

Spring Initializr es una herramienta web oficial de Spring que te permite generar rápidamente la estructura básica de un proyecto Spring Boot sin tener que crear todos los archivos iniciales a mano.

🔗 Enlace: [SpringInitilizr](https://start.spring.io/)

## Sección project metadata

- **Group:** nombre del grupo ( generalmente es el dominio inverso del proyecto)

  Ej: com.prueba → prueba.com

- **Artifact:** El nombre de la carpeta principal del proyecto (y del archivo `.jar` que se genera).
- **Name:** Nombre del proyecto (usado en el `pom.xml` o `build.gradle`).
- **Description:** Descripción del proyecto

## Sección dependencies

Aquí seleccionas qué **módulos** de Spring (y librerías externas) querés incluir.

Ejemplos:

- **Spring Web** → para crear APIs REST o páginas web.
- **Spring Data JPA** → para trabajar con bases de datos con JPA/Hibernate.
- **Thymeleaf** → para renderizar HTML desde templates.
- **Spring Security** → para autenticación/autorización.
- **MySQL Driver / PostgreSQL Driver** → para conectarte a esas bases de datos.