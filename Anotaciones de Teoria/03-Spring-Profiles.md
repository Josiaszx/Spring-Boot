# Spring Profiles

Los Spring Profiles (Perfiles de Spring) son un mecanismo que permite definir diferentes configuraciones de la aplicación según el entorno o contexto en el que se ejecute (desarrollo, testing, producción, etc.). Permiten activar o desactivar beans, configuraciones específicas, definir diferentes propiedades según el contexto, entre otras cosas.

## Activar perfiles

Para definir un perfil activo dentro de nuestro proyecto, podemos hacerlo mediante `application.properties` , de forma programática (en el código mismo) o por linea de comandos (al ejecutar el programa).

### Definir en las propiedades de la aplicación

En `application.properties` agregamos la propiedad `spring.profiles.active` con los perfiles que queremos agregar como valores, seguidos de una coma.

**Ejemplo:**

Si queremos activar los perfiles dev y test, agregamos lo siguiente:

```
spring.profiles.active=dev,text
```

### Definir programáticamente

En la clase de la aplicación:

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setAdditionalProfiles("dev"); // agregamos el perfil dev
        app.run(args);
    }
}
```

### Definir por linea de comandos

Para ejecutar el archivo, lo hacemos con:

```bash
java -jar aplicacion.jar --spring.profiles.active=dev
# simplemente activara el perfil en la propiedades de la aplicacion
```

## Definir propiedades de perfiles

Para definir las propiedades de la aplicación según los perfiles que estén activos, creamos un nuevo archivos de propiedades en la carpeta resources para definirlas ahi.

⚠️ El archivo de propiedades debe tener por nombre: `application-NOMBRE_PERFIL.properties`.

Estas propiedades serán agregadas al archivo de propiedades de la aplicación (`application.properties`) si es que es una propiedad que no esta definida ahi, en caso de que si este definida, lo sobrescribirá.

**Ejemplo:**

Si queremos definir las propiedades de la aplicación cuando este en el perfil `dev` , creamos el archivo `application-dev.properties` y dentro escribimos sus propiedades.

Si tenemos varios perfiles activados que comparten una propiedad especifica, esta propiedad se ira sobrescribiendo según el orden en el que definen los perfiles como activos.

**Ejemplo:**

Si tenemos tres perfiles: dev, test y local  definidos como activos en ese oren, donde los tres comparten una propiedad llamada `app.env` . Al momento de ejecutar el programa el valor de dicha propiedad sera la asignada en el perfil de local porque fue el ultimo perfil en ser activado, por lo que sobrescribió el valor de la propiedad de los demás perfiles.

## Definir componentes y beans para determinados perfiles

Para lograr esto simplemente añadimos la anotación `@Profile` antes del bean que queramos definir para determinado perfil, indicándole entre paréntesis el nombre del perfil.

**Definir clase según perfil activo:**

```java
@Profile("dev")
@Component
public class Clase { } // Clase solo funcionara si el perfil dev esta activo
```

**Definir un Bean inyectado por método según perfil activo:**

```java
@Bean
@Profile("dev")
public Clase bean1() {
    return new Clase("dev first"); 
}

@Bean
@Profile("test")
public Clase bean2() {
    return new Clase("test");
}
// el bean que se inyectara dependera del perfil en el que se trabaje
```

⚠️ Si ambos perfiles están activos, esto dará error, una solución para ello podría ser agregar la anotación `@Primary` antes de alguno para poder decidir cual usar en dicho caso.

💬 Dentro de `@Profile` también podemos usar operadores lógicos, esto para definir en caso de querer seguir alguna lógica entre los perfiles. Esto también puede ser una solución al problema de la advertencia anterior.