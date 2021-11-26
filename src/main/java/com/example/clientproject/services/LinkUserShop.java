package com.example.clientproject.services;

import com.example.clientproject.data.adminTypes.AdminTypes;
import com.example.clientproject.data.adminTypes.AdminTypesRepo;
import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.userPermissions.UserPermissions;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.data.users.UsersRepo;
import org.springframework.stereotype.Service;

@Service
public class LinkUserShop {

    UsersRepo userRepo;
    ShopsRepo shopsRepo;
    AdminTypesRepo adminTypesRepo;
    UserPermissionsRepo userPermissionsRepo;

    public LinkUserShop(UsersRepo aUsersRepo,
                        ShopsRepo aShopsRepo,
                        AdminTypesRepo anAdminTypesRepo,
                        UserPermissionsRepo aUserPermissionsRepo){
        userRepo = aUsersRepo;
        shopsRepo = aShopsRepo;
        adminTypesRepo = anAdminTypesRepo;
        userPermissionsRepo = aUserPermissionsRepo;
    }

    public void linkUserShop(long shopId, long userID){
        try {
            Users user = userRepo.findById(userID).get();
            Shops shop = shopsRepo.getById(shopId);
            AdminTypes adminType = adminTypesRepo.getById(2L);

            UserPermissions link = new UserPermissions(user, shop, adminType);

            userPermissionsRepo.save(link);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
