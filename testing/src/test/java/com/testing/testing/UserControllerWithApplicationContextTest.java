package com.testing.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
    Testing de integracion cargando SpringWebContext (sin http) con RestTestClient
*/


@SpringBootTest // cargar el contexto de Spring sin levantar el servidor
class UserControllerWithApplicationContextTest {

    @Autowired
    private WebApplicationContext context; // application context

    @Autowired
    private UserServices userServices; // inyectamos dependencia necesaria

    private RestTestClient client; // cliente para realizar peticiones rest y testear respuestas


    @BeforeEach
    void setUp() {
        client = RestTestClient.bindToApplicationContext(context).build(); // definimos cliente y contexto
    }

    @Test
    void findAll() {
        var responseBody = client.get() // inicia una peticion GET
                .uri("/api/users") // endpoint de la peticion
                .exchange() // envia la peticion y recibe la respuesta
                .expectStatus().isOk() // verificar que el estado de la respuesta sea 200 OK
                .expectBody(new ParameterizedTypeReference<List<User>>() {}) // deserializar la respuesta a un objeto List<User>
                .returnResult() // extraer la lista deserializada
                .getResponseBody(); // retornar el body de la respuesta (List<User>)

        assertNotNull(responseBody);
        assertTrue(responseBody.isEmpty());
    }

    @Test
    void findById() {

        // cargar datos de prueba
        setData();

        var responseBody = client.get()
                .uri("/api/users/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();

        var expectedUser = new User(1L, "Josias", "josias@email.com");

        assertNotNull(responseBody);
        assertEquals(expectedUser, responseBody);
    }

    @Test
    void save() {
        var newUser = new User(1L, "Josias", "josias@email.com");

        client.post()
                .uri("/api/users")
                .body(newUser)
                .exchange()
                .expectStatus().isCreated() // verificar que el estado de la respuesta sea 201 Created
                .expectBody().isEmpty(); // verificar que el body de la respuesta este vacio
    }

    // funcion para cargar datos de prueba
    private void setData() {
        client.get()
                .uri("/api/users/create")
                .exchange();
    }


}