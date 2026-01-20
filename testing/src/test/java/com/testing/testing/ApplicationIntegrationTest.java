package com.testing.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/*
    Testing de integracion cargando SpringWebContext y levantando el servidor con RestTestClient
*/

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // levantar aplicacion completa en un puerto aleatorio
class ApplicationIntegrationTest {

    @LocalServerPort
    private int port; // puerto de la aplicacion

    private RestTestClient client;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        client = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + port)
                .build();

        setData();
    }

    @AfterEach
    void tearDown() {
        userRepository.getUsers().clear(); // limpiar datos despues de cada prueba
    }

    @Test
    void findById() {
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
    void findAll() {
        var responseBody = client.get()
                .uri("/api/users")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<User>>() {})
                .returnResult()
                .getResponseBody();

        var expectedResponseBody = userRepository.getUsers();

        assertNotNull(responseBody);
        assertEquals(expectedResponseBody, responseBody);
    }

    @Test
    void save() {
        var newUser = new User(1L, "Josias", "josias@email.com");

        client.post()
                .uri("/api/users")
                .body(newUser)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().isEmpty();
    }

    // funcion para cargar datos de prueba
    private void setData() {
        client.get()
                .uri("/api/users/create")
                .exchange();
    }
}