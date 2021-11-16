package com.example.clientproject.data.tags;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagsRepo extends JpaRepository<Tags, Long> {
    List<Tags> findAll();
}
