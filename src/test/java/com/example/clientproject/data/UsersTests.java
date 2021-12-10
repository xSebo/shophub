package com.example.clientproject.data;

import com.example.clientproject.data.twoFactorMethods.TwoFactorMethods;
import com.example.clientproject.data.twoFactorMethods.TwoFactorMethodsRepo;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.data.users.UsersRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Sql(scripts={"/schema-test-h2.sql","/script-test-h2.sql"})
@ActiveProfiles("h2")
@DirtiesContext
public class UsersTests {
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    TwoFactorMethodsRepo twoFactorMethodsRepo;

    @Test
    public void shouldGet21Users() throws Exception {
        List<Users> usersList = usersRepo.findAll();
        assertEquals(13, usersList.size());
    }

    @Test
    public void shouldGet22UsersAfterInsert() throws Exception {
        TwoFactorMethods twoFactorMethods = twoFactorMethodsRepo.findByTwoFactorMethodId(1).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Users newUser = new Users("", "", "", "",
                "", "",
                LocalDateTime.now().format(formatter), twoFactorMethods);
        Users users = usersRepo.save(newUser);

        List<Users> usersList = usersRepo.findAll();
        assertEquals(14, usersList.size());
    }
}

