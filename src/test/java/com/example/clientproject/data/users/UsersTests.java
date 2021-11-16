package com.example.clientproject.data.users;

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
public class UsersTests {
    @Autowired
    UsersRepo usersRepo;

    @Test
    public void shouldGet160Users() throws Exception {
        List<Users> usersList = usersRepo.findAll();
        assertEquals(160, usersList.size());
    }
}
