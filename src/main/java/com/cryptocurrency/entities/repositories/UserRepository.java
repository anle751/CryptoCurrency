package com.cryptocurrency.entities.repositories;

import com.cryptocurrency.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
