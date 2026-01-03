package com.implemetacion.jwt.service;

import com.implemetacion.jwt.model.UserEntity;
import com.implemetacion.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // agregar usuario
    public ResponseEntity<Map<String, Object>> save(UserEntity user) {
        user = userRepository.save(user);
        var response = new HashMap<String, Object>();
        response.put("nombre de usuario", user.getFirstName() + user.getLastName());
        response.put("email", user.getEmail());
        response.put("fecha de creacion", user.getCreatedAt());
        return ResponseEntity.ok(response);
    }

    // listar usuarios
    public ResponseEntity<List<UserEntity>> listAllUsers() {
        var users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    // mostrar usuario por id
    public ResponseEntity<UserEntity> getUserById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

        return ResponseEntity.ok(user);
    }

    // actualizar usuario
    public ResponseEntity<UserEntity> updateUser(UserEntity user) {
        if (!userRepository.existsById(user.getId())) {
            throw new UsernameNotFoundException("User not found with id: " + user.getId());
        }

        var savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    // eliminar usuario por id
    public ResponseEntity<HashMap<String, Object>> deleteUserById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

        var response = new HashMap<String, Object>();
        response.put("nombre de usuario eliminado", user.getFirstName() + user.getLastName());
        response.put("fecha y hora de elimincaion", LocalDateTime.now());

        userRepository.deleteById(id);

        return ResponseEntity.ok(response);
    }
}
