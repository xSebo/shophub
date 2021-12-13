package com.example.clientproject.service.Utils;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.userPermissions.UserPermissions;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckUserOwner {

    @Autowired
    UserPermissionsRepo permissionsRepo;

    public boolean checkOwner(int userId, int shopId){
        List<UserPermissions> permissions = permissionsRepo.findByShopID(shopId);

        for(UserPermissions u:permissions){
            if(u.getUser().getUserId() == userId){
                if(u.getAdminType().getAdminTypeId() == 2){
                    return true;
                }
            }
        }return false;
    }
}
