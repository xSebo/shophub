package com.example.clientproject.web.controllers;

import com.example.clientproject.web.forms.LoginForm;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for the "SignInController" and all login related routes
 */
@Sql(scripts={"/schema-test-h2.sql","/script-test-h2.sql"})
@ActiveProfiles("h2")
@DirtiesContext
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTests {
    @Autowired
    MockMvc mockMvc;

    /**
     * Parameterised Test to test "/login" route given a valid LoginForm object
     * 2nd should provide valid loggedIn response, others should provide error messages
     * @param email - email
     * @param password - password
     */
    @ParameterizedTest
    @CsvSource({"ShrayaBELUSKO@email.com,helloWorld", "ShrayaBELUSKO@email.com,password123", "Testing123@email.com,helloworld123"})
    public void correctResponseFromLoginRoute(String email, String password) throws Exception {
        if (email.equals("ShrayaBELUSKO@email.com") && password.equals("password123")) {
            mockMvc.perform(post("/login")
                            .param("loginEmail", email)
                            .param("loginPassword", password)
                    )
                    .andDo(print())
                    .andExpect(status().is3xxRedirection());
        } else {
            mockMvc.perform(post("/login")
                            .param("loginEmail", email)
                            .param("loginPassword", password)
                    )
                    .andDo(print())
                    .andExpect(status().is(403));
        }
    }
}
