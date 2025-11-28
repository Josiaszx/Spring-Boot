package com.testing.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // implementar funcionalidades de la libreria Mockito
@DisplayName("Test UsuarioServices") // (opcional) nombre a mostrar en las pruebas (por defecto toma el nombre de la clase)
class UsuarioServicesTest {

    // purebas unitarias para clases que requieren dependencias:

    // 1. identificamos las dependendecias necesarias y las definimos con la anotacion @Mock
    @Mock // @Mock: Define un atributo como dependecia necesaria
    UsuarioRepository repositorio;

    // 2. inicializamos la clase que queremos testear con la anotacion @InjectMocks
    @InjectMocks // @InjectMocks: Marca el atributo en el que se inyectaran las dependencias marcadas con @Mock
    UsuarioServices servicios;

    @BeforeEach
    void setUp() {
        repositorio.deleteAll();
    }

    @Test
    @DisplayName("findByID")
    void findById() {
        // definimos el resultado esperado
        Usuario usuario = new Usuario(
                1,
                "Joasias",
                "Maidana",
                "josias@email.com"
        );

        // evitamos llamar funciones externas
        when(repositorio.findById(1L)).thenReturn(Optional.of(usuario));
        // cuando se llame al metodo findById del repositorio, directamente se retornara el usuario

        // guardamos la respuesta del metodo
        Usuario respuesta = servicios.findById(1);

        // comparamos si la respuesta coincide con lo esperado
        assertNotNull(respuesta);
        assertEquals(usuario.getId(), respuesta.getId());
        assertEquals(usuario.getNombre(), respuesta.getNombre());
        assertEquals(usuario.getApellido(), respuesta.getApellido());
        assertEquals(usuario.getEmail(), respuesta.getEmail());
    }

    @Test
    @DisplayName("save")
    void save() {
        Usuario usuario = new Usuario(
                0,
                "Juan",
                "carlos",
                "ramirez"
        );

        when(repositorio.save(usuario)).thenReturn(usuario);
        Usuario respuesta = servicios.save(usuario);

        assertNotNull(respuesta);
        assertEquals(usuario.getId(), respuesta.getId());
        assertEquals(usuario.getNombre(), respuesta.getNombre());
        assertEquals(usuario.getApellido(), respuesta.getApellido());
        assertEquals(usuario.getEmail(), respuesta.getEmail());
    }
}