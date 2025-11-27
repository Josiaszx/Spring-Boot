# JPA & Hibernate

**JPA:** es una especificación de Java que define cómo mapear objetos Java a tablas de bases de datos relacionales y viceversa. 

**Hibernate:** es una implementación concreta de la especificación de JPA. Implementa todas las interfaces y comportamientos definidos por JPA, pero también ofrece funcionalidades adicionales propias que van más allá del estándar.

JPA NO es una implementación, es solo una especificación (interfaz) que define:

- Cómo mapear clases Java a tablas
- Cómo realizar operaciones CRUD
- Cómo hacer consultas
- Cómo gestionar transacciones
- Cómo manejar relaciones entre entidades

## Conectar BD con proyecto

Para conectar una base de datos con nuestro proyecto de spring, primero debemos tener descargadas las dependencias de JPA y el driver del motor de base de datos que estemos utilizando. Esto podemos hacerlo al crear el proyecto en Initilizr o agregando las siguientes dependencias en el `pom.xml`

Para agregar dependencias de JPA:

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

Para agregar dependencias de MySQL (driver): 

```xml
<dependency>
	<groupId>com.mysql</groupId>
	<artifactId>mysql-connector-j</artifactId>
	<scope>runtime</scope>
</dependency>
```

Luego tenemos que configurar la conexión a la base de datos, esto lo hacemos en el `application.properties` o en el `application.yml`.

### En application.properties

```
#definimos la dirección de nuestra base de datos
spring.datasource.url=jdbc:mysql://localhost:3306

# usuario de bd
spring.datasource.username=usuario_de_bd

# contraseña de bd
spring.datasource.password=contreseña_de_bd

# driver de bd
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### En application.yml

```
spring:
  application:
    name: JPA
  
  datasource:
    url: jdbc:mysql://localhost:3306/demo_db
    username: usuario_de_bd
    password: contreseña_de_bd
    driver-class-name: com.mysql.cj.jdbc.Driver
```