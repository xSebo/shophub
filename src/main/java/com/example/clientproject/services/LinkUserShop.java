package com.example.clientproject.services;

import com.example.clientproject.data.adminTypes.AdminTypes;
import com.example.clientproject.data.adminTypes.AdminTypesRepo;
import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.userPermissions.UserPermissions;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.data.users.UsersRepo;
import com.example.clientproject.service.LoggingService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class LinkUserShop {

    UsersRepo userRepo;
    ShopsRepo shopsRepo;
    AdminTypesRepo adminTypesRepo;
    UserPermissionsRepo userPermissionsRepo;
    LoggingService loggingService;

    public LinkUserShop(UsersRepo aUsersRepo,
                        ShopsRepo aShopsRepo,
                        AdminTypesRepo anAdminTypesRepo,
                        UserPermissionsRepo aUserPermissionsRepo,
                        LoggingService aLoggingService){
        userRepo = aUsersRepo;
        shopsRepo = aShopsRepo;
        adminTypesRepo = anAdminTypesRepo;
        userPermissionsRepo = aUserPermissionsRepo;
        loggingService = aLoggingService;
    }

    public void linkUserShop(long shopId, long userID, long adminTypeId, HttpSession session){
        try {
            Users user = userRepo.findById(userID).get();
            Shops shop = shopsRepo.getById(shopId);
            AdminTypes adminType = adminTypesRepo.getById(adminTypeId);

            UserPermissions link = new UserPermissions(user, shop, adminType);

            userPermissionsRepo.save(link);
            // Log the change
            loggingService.logEvent(
                    "New User Permission",
                    session,
                    "New User Permission added for User: " + user.getUserId() +
                            " with Shop: " + shop.getShopId() +
                            " with Admin Type: " + adminType.getAdminTypeId() +
                            " in LinkUserShop.linkUserShop()"
            );
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
