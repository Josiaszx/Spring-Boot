# Desplegar proyecto desde CMD

## Desplegar sin compilar

Para desplegar el archivo sin compilar en un .jar, ejecutamos el programa `mvnw`  (Maven Wrapper) con el parámetro `spring-boot:run` , en PowerShell:

```powershell
.\mvnw spring-boot:run
```

✅ Ideal para desarrollo local, porque se puede lanzar sin empaquetar todo.

## Desplegar compilando el proyecto en un .jar

Primero comprobamos que la carpeta target no tenga ningún archivo de tipo jar o war.

Luego nos dirigimos a la carpeta base del proyecto y ejecutamos el  programa `mvnw` con los parámetros `clean` y `package`, en PowerShell:

```powershell
.\mvnw clean package
```

- Con `clean` indicamos que se borre cualquier build antigua para evitar conflictos con la nueva
- Con `package` indicamos a maven que compile y empaque el proyecto según el pom.xml

Una vez terminado el proceso, se generara un archivo en la carpeta target con un nombre tipo:

`proyecto-0.0.1-SNAPSHOT.jar`

Si el archivo se genero correctamente, nos posicionamos en la carpeta target y ejecutamos el siguiente comando:

```bash
java -jar .\hello-springboot-0.0.1-SNAPSHOT.jar 
```

- Con `java` llamamos a la JVM
- Con `-jar` le indicamos a la JVM que ejecute el archivo .jar que le pasaremos

Con esto se desplegara nuestro proyecto.