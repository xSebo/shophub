package com.example.clientproject.data.adminTypes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminTypesRepo extends JpaRepository<AdminTypes, Long> {
    List<AdminTypes> findAll();
}
