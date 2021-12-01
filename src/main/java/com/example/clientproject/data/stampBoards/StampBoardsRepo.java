package com.example.clientproject.data.stampBoards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for the "StampBoards" Entity
 * Extends the JpaRepository library with the types of "StampBoards" and "Long"
 */
public interface StampBoardsRepo extends JpaRepository<StampBoards, Long> {
    /**
     * Save Method
     * @param stampBoards - the StampBoard to save
     * @return - the object
     */
    StampBoards save(StampBoards stampBoards);

    /**
     * Find a stamp board by a given Id value
     * @param id - the id to search by
     * @return - an optional containing the stampboard found
     */
    @Query("SELECT s from StampBoards s where s.stampBoardId = ?1")
    Optional<StampBoards> findById(long id);

}
