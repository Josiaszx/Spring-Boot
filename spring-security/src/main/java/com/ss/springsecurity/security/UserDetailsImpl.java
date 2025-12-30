package com.ss.springsecurity.security;

import com.ss.springsecurity.entity.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


/*
    La interfaz UserDetails define una implementacion que permite a spring conocer las credenciales necesarias
    par la validacion y autenticacion de un usuario independientemente de los atributos que maneje dicho usuario.
    En este caso manejemos una clase User para representar el usuario dentro de la aplicacion, el problema es que spring
    no sabe que atributos maneja dicho usuario, por lo que requiere de una implementacion especifica para manejar
    la autenticacion de dicho usuario.
*/


public class UserDetailsImpl implements UserDetails{

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    /*
        Definir los roles o permisos del usuario.
        En este caso lo definiremos como READ para todos los usuarios a modo de ejemplo.
        Si hacemos una solicitud GET /auth/usuarios/read se deberia mostrar la lista de usuarios.
        Si hacemos una solicitud GET /auth/usuarios/admin se deberia mostrar un mensaje de error 403.
    */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> "READ");
    }


    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "UserDetailsImpl{" +
                "username =" + user.getNombre() +
                "password = " + user.getPassword() +
                '}';
    }
}
