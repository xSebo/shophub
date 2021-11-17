package com.example.clientproject.data.twoFactorMethods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TwoFactorMethodsRepo extends JpaRepository<TwoFactorMethods, Long> {
    List<TwoFactorMethods> findAll();

    @Query("select t from TwoFactorMethods t where t.twoFactorMethodId = ?1")
    public Optional<TwoFactorMethods> findByTwoFactorMethodId(long twoFactorMethodId);
}
