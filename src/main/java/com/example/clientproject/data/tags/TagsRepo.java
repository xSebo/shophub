package com.example.clientproject.data.tags;

import com.example.clientproject.data.shops.Shops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    @Query("select t.tagId from Tags t where t.tagId = (select max(t.tagId) from Tags t)")
    int findMostRecent();
}
