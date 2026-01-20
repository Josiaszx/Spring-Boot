package com.testing.testing;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();

    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.id().equals(id))
                .findFirst();
    }

    public List<User> findAll() {
        return users;
    }

    public void save(User user) {
        users.add(user);
    }
}
