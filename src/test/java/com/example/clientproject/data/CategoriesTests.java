package com.example.clientproject.data;

import com.example.clientproject.data.categories.Categories;
import com.example.clientproject.data.categories.CategoriesRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Sql(scripts={"/schema-test-h2.sql","/script-test-h2.sql"})
@ActiveProfiles("h2")
@DirtiesContext
public class CategoriesTests {
    @Autowired
    CategoriesRepo categoriesRepo;

    @Test
    public void shouldGet7Categories(){
        List<Categories> categoriesList = categoriesRepo.findAll();
        System.out.println(categoriesList);
        assertEquals(7, categoriesList.size());
    }

    @Test
    public void shouldGet8CategoriesAfterInsert(){
        categoriesRepo.save(new Categories("Test"));
        List<Categories> categoriesList = categoriesRepo.findAll();
        assertEquals(8, categoriesList.size());
    }
}
