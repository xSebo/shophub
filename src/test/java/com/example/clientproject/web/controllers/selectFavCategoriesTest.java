package com.example.clientproject.web.controllers;


import com.example.clientproject.domain.AccountRegister;
import com.example.clientproject.services.newAccountDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for the "SignUpController"
 */
@Sql(scripts={"/schema-test-h2.sql","/script-test-h2.sql"})
@ActiveProfiles("h2")
@DirtiesContext
@SpringBootTest
@AutoConfigureMockMvc
public class selectFavCategoriesTests {
    @Autowired
    MockMvc mockMvc;

    /**
     * Parameterised Test to test "/signup" route given a valid SignUp object
     * takes object aAccount as parameter
     * this object contains the users firstname, surname, email and password
     */
    @ParameterizedTest
    public void correctResponseFromLoginRoute() throws Exception {

        mockMvc.perform(post("/selectCategories")
                        .param("listOfTagIDs", "2,5,8,13")
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}
