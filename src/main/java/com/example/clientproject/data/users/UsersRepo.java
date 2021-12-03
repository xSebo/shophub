package com.example.clientproject.data.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * JPA Repository for the "Users" Entity
 * Extends the JpaRepository library with the types of "Users" and "Long"
 */
public interface UsersRepo extends JpaRepository<Users, Long> {
    /**
     * Method for finding a user by the email
     * @param email - the email to search by
     * @return - an optional containing the user if one is found
     */
    @Query("select u from Users u where u.userEmail = ?1")
    Optional<Users> findByUserEmail(String email);

    /**
     * Save method
     * @param user - the object to save
     * @return - the object
     */
    Users save(Users user);
}
