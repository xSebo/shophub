package com.example.clientproject.data.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * Repository for the "Categories" Entity
 */
public interface CategoriesRepo extends JpaRepository<Categories, Long> {
    /**
     * Save method
     * @param category - the category to save
     * @return - the category
     */
    Categories save(Categories category);

    @Query("select c from Categories c where c.categoryName = ?1")
    public Categories findByName(String categoryName);
}
