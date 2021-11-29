package com.example.clientproject.data.adminTypes;

import com.example.clientproject.data.twoFactorMethods.TwoFactorMethods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for the "AdminTypes" Entity
 * Extends the JpaRepository library with the types of "AdminTypes" and "Long"
 */
public interface AdminTypesRepo extends JpaRepository<AdminTypes, Long> {
    /**
     * Find an AdminType by the Id
     * @param adminTypeId - id of the AdminType to find
     * @return - Optional object containing the AdminType found, if it's present
     */
    @Query("select a from AdminTypes a where a.adminTypeId = ?1")
    Optional<AdminTypes> findByAdminTypeId(long adminTypeId);
}
