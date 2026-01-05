package com.implemetacion.jwt.controller;

import com.implemetacion.jwt.model.dto.PostUserDto;
import com.implemetacion.jwt.service.UserService;
import com.implemetacion.jwt.model.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody PostUserDto user) {
        return userService.save(user);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> listAllUsers() {
        return userService.listAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteUserById(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }

}
