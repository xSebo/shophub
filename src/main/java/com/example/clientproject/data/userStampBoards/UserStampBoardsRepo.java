package com.example.clientproject.data.userStampBoards;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA Repository for the "UserStampBoards" Entity
 * Extends the JpaRepository library with the types of "UserStampBoards" and "Long"
 */
public interface UserStampBoardsRepo extends JpaRepository<UserStampBoards, Long> {
    /**
     * FindAll method
     * @return list of UserStampBoards found
     */
    List<UserStampBoards> findAll();

    /**
     * Save method
     * @param userStampBoards - the object to save
     * @return - the object
     */
    UserStampBoards save(UserStampBoards userStampBoards);
}
