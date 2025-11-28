package com.testing.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioMapperTest {

    UsuarioMapper usuarioMapper =  new UsuarioMapper();

    @Test
    @DisplayName("Usuario a UsuarioDTO")
    void UsuarioToUsuarioDTO() {

        // definir el parametro que pasaremos
        Usuario usuario = new Usuario(
                1,
                "Juan",
                "Jimenez",
                "juan@email.com"
        );

        // definir el resutado esperado de la opercion
        UsuarioDTO esperado = new UsuarioDTO(
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail()
        );

        // guardar el resultado
        UsuarioDTO respuesta = usuarioMapper.UsuarioToUsuarioDTO(usuario);

        // comprobar resultado
        assertNotNull(respuesta);
        assertEquals(esperado.getNombre(), respuesta.getNombre());
        assertEquals(esperado.getApellido(), respuesta.getApellido());
        assertEquals(esperado.getEmail(), respuesta.getEmail());

    }

    @Test
    @DisplayName("UsuarioDTO a Usuario")
    void UsuarioDTOToUsuario() {
        // definimos el parametros que pasaremos
        UsuarioDTO param =  new UsuarioDTO(
                "Josias",
                "Miadana",
                "maidanajosias@email.com"
        );

        // definimos el resultado esperado con el parametro dado
        Usuario esperado = new Usuario(
                param.getNombre(),
                param.getApellido(),
                param.getEmail()
        );

        // guardamos la respueta
        Usuario respuesta = usuarioMapper.UsuarioDTOToUsuario(param);

        // verificamos la respuesta
        assertNotNull(respuesta);
        assertEquals(esperado.getNombre(), respuesta.getNombre());
        assertEquals(esperado.getApellido(), respuesta.getApellido());
        assertEquals(esperado.getEmail(), respuesta.getEmail());
    }

}