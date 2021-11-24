package com.example.clientproject.data.categories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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
}
