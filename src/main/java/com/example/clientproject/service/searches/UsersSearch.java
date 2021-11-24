package com.example.clientproject.service.searches;

import com.example.clientproject.service.dtos.UsersDTO;

import java.util.List;
import java.util.Optional;

public interface UsersSearch {
    List<UsersDTO> findAll();

    Optional<UsersDTO> findByEmail(String email);
}
