package com.example.clientproject.data.tags;

import com.example.clientproject.data.shops.Shops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for the "Tags" Entity
 * Extends the JpaRepository library with the types of "Tags" and "Long"
 */
public interface TagsRepo extends JpaRepository<Tags, Long> {
    /**
     * Save method
     * @param tags - the object to save
     * @return - the object
     */
    Tags save(Tags tags);

    @Query("select t.tagId from Tags t where t.tagId = (select max(t.tagId) from Tags t)")
    int findMostRecent();

    @Query("select t from Tags t where t.tagName = ?1")
    Optional<Tags> findByTagName(String tagName);

    @Query("select t from Tags t where t.tagName = ?1")
    Optional<Tags> findByTagNameIgnoreCase(String tagName);
}
