package com.example.clientproject.data;

import com.example.clientproject.data.adminTypes.AdminTypes;
import com.example.clientproject.data.adminTypes.AdminTypesRepo;
import com.example.clientproject.data.categories.CategoriesRepo;
import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.stampBoards.StampBoardsRepo;
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
    @Autowired
    StampBoardsRepo stampRepo;
    @Autowired
    CategoriesRepo categoriesRepo;

    @Test
    public void shouldGet18Permissions() {
        List<UserPermissions> userPermissionsList = userPermissionsRepo.findAll();
        assertEquals(13, userPermissionsList.size());
    }

    @Test
    public void shouldGet19PermissionsAfterInsert() throws Exception {
        Shops newShop = new Shops("", "", "", 0, "", "", "", true, stampRepo.getById(1L), categoriesRepo.getById(1L));
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
        assertEquals(14, userPermissionsList.size());
    }
}
