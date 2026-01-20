# Tests de Integracion y end-to-end

Para realizar test de integración usaremos la herramienta de `WebTestClient`, incluida en Spring Framework 7 y SpringBoot 4. `WebTestClient` es una herramienta para test unificada que nos permite realizar tests unitarios, de integración y end-to-end a nuestras APIs REST. 

Para realizar los tests podemos seleccionar una de las siguientes opciones:

1. Levantar el `ApplicationWebContext` necesario de spring web (sin http porque no se levanta la aplicación completa).
2. Levantar la aplicación completa

**Diferencias:**

| Opcion | Tipo | Velocidad | Que testea | Casos de uso |
| --- | --- | --- | --- | --- |
| Levantar `ApplicationContext` y spring MVC | Integracion | Rapido (pero mas lento que las pruebas unitarias) | Aplicacion completa (sin http) | Tests de servicios con BD sin levantar server |
| Levantar aplicacion completa | end-to-end | Mas lento | Aplicacion completa (con http) | Test completo sobre todas las capas de la aplicacion |

## Tests levantando solo `ApplicationContext` y Spring MVC

Para testear un endpoint sin levantar la aplicación completa usamos la anotación `@SpringBootTest`. 

```java
@SpringBootTest // cargar el contexto de Spring sin levantar el servidor
class UserControllerWithApplicationContextTest {

    @Autowired
    private WebApplicationContext context; // application context
    
    private RestTestClient client; // cliente para hacer peticiones http y hacer validaciones
    
    // si se necesitan dependenicas -> inyectar con @Autowired
    
    // configurar RestTestClient
    @BeforeEach
    void setUp() {
        client = RestTestClient.bindToApplicationContext(context).build(); // definimos cliente y contexto
    }
    
    // tests ...

}
```

## Tests levantando aplicación completa

Para testear endpoints levantando la aplicación completa usamos la anotación `@SpringBootTest` pasándole como parámetro `webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT`.

```java
// levantar aplicacion completa en un puerto aleatorio
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationIntegrationTest {

    @LocalServerPort
    private int port; // puerto de la aplicacion

    private RestTestClient client;
    
    // si se necesitan dependenicas -> inyectar con @Autowired
    
    // configurar RestTestClient
    @BeforeEach
    void setUp() {
        client = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + port) // url del server + puerto
                .build();
    }
    
    // tests ...

}
```

## Peticiones y validaciones con `RestTestClient`

Una vez configurado `RestTestClient`, podemos hacer hacer una peticion de la siguiente forma:

```java
User responseBody = client.get() // inicia una peticion GET
        .uri("/api/users/1") // endpoint de la peticion
        .exchange() // envia la peticion y recibe la respuestahttps://github.com/Josiaszx/Hello-Java/blob/main/Anotaciones/09-JUnit.md$0
        .expectStatus().isOk() // verificar que el estado de la respuesta sea 200 OK
        .expectBody(User.Class) // deserializar la respuesta a un objeto tipo User
        .returnResult() // extraer el objeto deserializado
        .getResponseBody(); // retornar el body de la respuesta (User)
```

Si se espera recibir una lista de algún objeto, en `.expectBody()` se debe pasar como parámetro lo siguiente:

```java
.expectBody(new ParameterizedTypeReference<List<User>>() {})
// en este caso, User -> Entidad esperada dentro de la lista
```

**Recursos Utiles:**
- [Testing unitario con JUnit](https://github.com/Josiaszx/Hello-Java/blob/main/Anotaciones/09-JUnit.md)
- [rest-test-cliet by: Dan Vega](https://github.com/danvega/rest-test-client)