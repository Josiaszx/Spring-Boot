package com.ss.springsecurity.security;

import com.ss.springsecurity.entity.User;
import com.ss.springsecurity.repository.UserRepoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/*
    Esta clase en una implementacion propia de la interfaz UserDetailsService para validar los UserDetails.
    los componentes implementado con UserDetailService se encarga de validar los datos del usuario comparando
    con los datos de alguna fuente de informacion (en este caso la base de datos), y si son correctos retorna
    un objeto UserDetails que sera utilizado para validar el componente Authentication.
*/

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // inyectamos el repositorio de usuarios del que validaran los datos
    @Autowired
    private UserRepoitory userRepository;


    /*
        Implementamos el metodo loadUserByUsername solicitado por la interfaz,
        este metodo va a validar que exista el usuario solicitado.
        Si el usuario no existe lanza una exepcion UsernameNotFoundException
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // en este caso validamos con el email
        User user = userRepository.findByEmail((username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new UserDetailsImpl(user);
    }
}
