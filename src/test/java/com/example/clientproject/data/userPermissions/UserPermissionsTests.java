package com.example.clientproject.data.userPermissions;

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
public class UserPermissionsTests {
    @Autowired
    UserPermissionsRepo userPermissionsRepo;

    @Test
    public void shouldGet157Permissions() {
        List<UserPermissions> userPermissionsList = userPermissionsRepo.findAll();
        assertEquals(157, userPermissionsList.size());
    }
}
