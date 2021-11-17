package com.example.clientproject.data.userStampBoards;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserStampBoardsRepo extends JpaRepository<UserStampBoards, Long> {
    List<UserStampBoards> findAll();

    UserStampBoards save(UserStampBoards userStampBoards);
}
