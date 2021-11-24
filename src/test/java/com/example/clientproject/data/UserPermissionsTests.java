package com.example.clientproject.data;

import com.example.clientproject.data.adminTypes.AdminTypes;
import com.example.clientproject.data.adminTypes.AdminTypesRepo;
import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.twoFactorMethods.TwoFactorMethods;
import com.example.clientproject.data.twoFactorMethods.TwoFactorMethodsRepo;
import com.example.clientproject.data.userPermissions.UserPermissions;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
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
public class UserPermissionsTests {
    @Autowired
    UserPermissionsRepo userPermissionsRepo;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    ShopsRepo shopsRepo;
    @Autowired
    TwoFactorMethodsRepo twoFactorMethodsRepo;
    @Autowired
    AdminTypesRepo adminTypesRepo;

    @Test
    public void shouldGet157Permissions() {
        List<UserPermissions> userPermissionsList = userPermissionsRepo.findAll();
        assertEquals(157, userPermissionsList.size());
    }

    @Test
    public void shouldGet158PermissionsAfterInsert() throws Exception {
        Shops newShop = new Shops("", "", "", 0, "", "", true);
        shopsRepo.save(newShop);
        TwoFactorMethods twoFactorMethods = twoFactorMethodsRepo.findByTwoFactorMethodId(1).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Users newUser = new Users("", "", "", "",
                "", "",
                LocalDateTime.now().format(formatter), twoFactorMethods);
        usersRepo.save(newUser);
        AdminTypes adminTypes = adminTypesRepo.findByAdminTypeId(1).get();

        UserPermissions newUserPermission = new UserPermissions(newUser, newShop, adminTypes);
        userPermissionsRepo.save(newUserPermission);

        List<UserPermissions> userPermissionsList = userPermissionsRepo.findAll();
        assertEquals(158, userPermissionsList.size());
    }
}
