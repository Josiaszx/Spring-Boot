package com.testing.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/*
    testing unitario de UserServices con JUnit y Mockito.
*/

@ExtendWith(MockitoExtension.class) // implementar funcionalidades de la libreria Mockito
@DisplayName("Test UsuarioServices") // (opcional) nombre a mostrar en las pruebas (por defecto toma el nombre de la clase)
class UserServicesTest {

    // 1. identificamos las dependendecias necesarias y las definimos con la anotacion @Mock
    @Mock // @Mock: Define un atributo como dependecia necesaria
            UserRepository userRepository;


    // 2. inicializamos la clase que queremos testear con la anotacion @InjectMocks
    @InjectMocks // @InjectMocks: Marca el atributo en el que se inyectaran las dependencias marcadas con @Mock
            UserServices userServices;

    User user;

    @BeforeEach
    void setUp() {
        user = new User(1L , "Josias", "josias@email.com");
    }

    @Test
    void findById() {
        // evitamos llamar funciones externas para aislar la prueba
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(user));
        // cuando se llame al metodo findById del repositorio, directamente se retornara el usuario esperado

        // guardamos la respuesta del metodo
        var result = userServices.findById(1L);

        // comparamos si la respuesta coincide con lo esperado
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void findAll() {
        when(userRepository.findAll())
                .thenReturn(List.of(user));

        var result = userServices.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

}