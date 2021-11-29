package com.example.clientproject.data.twoFactorMethods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for the "TwoFactorMethods" Entity
 * Extends the JpaRepository library with the types of "TwoFactorMethods" and "Long"
 */
public interface TwoFactorMethodsRepo extends JpaRepository<TwoFactorMethods, Long> {
    /**
     * Search by the id of the two factor method
     * @param twoFactorMethodId - the id to search by
     * @return - an option containing the object if one is found
     */
    @Query("select t from TwoFactorMethods t where t.twoFactorMethodId = ?1")
    Optional<TwoFactorMethods> findByTwoFactorMethodId(long twoFactorMethodId);
}
