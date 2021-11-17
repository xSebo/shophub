package com.example.clientproject.data.adminTypes;

import com.example.clientproject.data.twoFactorMethods.TwoFactorMethods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdminTypesRepo extends JpaRepository<AdminTypes, Long> {
    List<AdminTypes> findAll();

    @Query("select a from AdminTypes a where a.adminTypeId = ?1")
    Optional<AdminTypes> findByAdminTypeId(long adminTypeId);
}
