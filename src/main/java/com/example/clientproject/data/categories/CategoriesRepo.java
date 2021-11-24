package com.example.clientproject.data.categories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for the "Categories" Entity
 */
public interface CategoriesRepo extends JpaRepository<Categories, Long> {
    /**
     * Find all method
     * @return - list of all the categories
     */
    List<Categories> findALl();

    /**
     * Save method
     * @param category - the category to save
     * @return - the category
     */
    Categories save(Categories category);
}
