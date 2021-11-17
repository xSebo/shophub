package com.example.clientproject.data.stampBoards;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA Repository for the "StampBoards" Entity
 * Extends the JpaRepository library with the types of "StampBoards" and "Long"
 */
public interface StampBoardsRepo extends JpaRepository<StampBoards, Long> {
    /**
     * FindAll method
     * @return list of StampBoards found
     */
    List<StampBoards> findAll();

    /**
     * Save Method
     * @param stampBoards - the StampBoard to save
     * @return - the object
     */
    StampBoards save(StampBoards stampBoards);
}
