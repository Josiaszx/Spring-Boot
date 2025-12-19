package com.ss.springsecurity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepoitory extends JpaRepository<User, Integer> {
}
