package com.testing.testing;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    final private UserServices userServices;

    @GetMapping
    public List<User> findAll() {
        return userServices.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userServices.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED) // define el status de la respuesta en 201 CREATED
    public void save(@RequestBody User user) {
        userServices.save(user);
    }

    // endpoint para cargar datos de prueba
    @GetMapping("/create")
    public String create() {
        var user1 = new User(1L, "Josias", "josias@email.com");
        var user2 = new User(2L, "Juan", "juan@email.com");
        var user3 = new User(3L, "Carlos", "carlos@email.com");

        userServices.save(user1);
        userServices.save(user2);
        userServices.save(user3);

        return "Users created";
    }
}
