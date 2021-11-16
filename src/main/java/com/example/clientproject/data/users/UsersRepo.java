package com.example.clientproject.data.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users, Long> {
    List<Users> findAll();

    Optional<Users> findByUserEmail(String email);
}
