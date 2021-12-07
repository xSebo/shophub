package com.example.clientproject.data.userStampBoards;

import com.example.clientproject.data.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for the "UserStampBoards" Entity
 * Extends the JpaRepository library with the types of "UserStampBoards" and "Long"
 */
public interface UserStampBoardsRepo extends JpaRepository<UserStampBoards, Long> {
    /**
     * Save method
     * @param userStampBoards - the object to save
     * @return - the object
     */
    UserStampBoards save(UserStampBoards userStampBoards);
}
