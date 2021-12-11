package com.example.clientproject.web.controllers.signUpAndIn;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts={"/schema-test-h2.sql","/script-test-h2.sql"})
@ActiveProfiles("h2")
@DirtiesContext
@SpringBootTest
@AutoConfigureMockMvc
public class SignUpTests {
    @Autowired
    MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource({"Josh,Gill,joshgill2013@gmail.com,Password123",
            "Shraya,BELUSKO,randeepccovery@email.comm,password123",
            "John,Watkins,jwatkins@email.com,helloWorld123"})
    public void givenValidFormsExpectCorrectResult(
            String forename, String surname, String email, String password) throws Exception {
        // If the signUp should pass
        if (!email.equals("ShrayaBELUSKO@email.com")) {
            mockMvc.perform(post("/signUp")
                            .param("newUserForename", forename)
                            .param("newUserLastname", surname)
                            .param("newUserEmail", email)
                            .param("newUserPassword", password)
                    )
                    .andDo(print())
                    .andExpect(status().is3xxRedirection());
        // Otherwise
        } else {
            mockMvc.perform(post("/signUp")
                            .param("newUserForename", forename)
                            .param("newUserLastname", surname)
                            .param("newUserEmail", email)
                            .param("newUserPassword", password)
                    )
                    .andDo(print())
                    .andExpect(model().attributeExists("emailInUse"));
        }
    }
}
