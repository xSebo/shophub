package com.example.clientproject.data.tags;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA Repository for the "Tags" Entity
 * Extends the JpaRepository library with the types of "Tags" and "Long"
 */
public interface TagsRepo extends JpaRepository<Tags, Long> {
    /**
     * FindAll method
     * @return list of Tags found
     */
    List<Tags> findAll();

    /**
     * Save method
     * @param tags - the object to save
     * @return - the object
     */
    Tags save(Tags tags);
}
