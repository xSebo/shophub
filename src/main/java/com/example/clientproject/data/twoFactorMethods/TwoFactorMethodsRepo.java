package com.example.clientproject.data.twoFactorMethods;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TwoFactorMethodsRepo extends JpaRepository<TwoFactorMethods, Long> {
    List<TwoFactorMethods> findAll();
}
