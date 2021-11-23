package com.example.clientproject.services;

import com.example.clientproject.data.users.Users;

import java.util.Optional;

public interface findUserByEmailService {
    public Optional<Users> findByUserEmail(String email);
}
