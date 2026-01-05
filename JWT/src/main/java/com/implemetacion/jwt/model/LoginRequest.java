package com.implemetacion.jwt.model;

/*
    Esta clase representa el formato que espara el controlador de autenticacion
    para recibir las solicitudes de inicio de sesion (login).
    Contiene los atributos necesarios para autenticar a un usuario, como el email y la contrasena.
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}
