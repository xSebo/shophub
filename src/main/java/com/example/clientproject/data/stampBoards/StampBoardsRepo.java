package com.example.clientproject.data.stampBoards;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StampBoardsRepo extends JpaRepository<StampBoards, Long> {
    List<StampBoards> findAll();

    StampBoards save(StampBoards stampBoards);
}
