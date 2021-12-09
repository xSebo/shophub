package com.example.clientproject.services;

import com.example.clientproject.data.userPermissions.UserPermissions;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLinked {
    @Autowired
    UserPermissionsRepo userPermRepo;

    public boolean isLinked(int userId, int shopId){
        List<UserPermissions> allLinks = userPermRepo.findByUserId(userId);
        for(UserPermissions u:allLinks){
            if(u.getShop().getShopId() == shopId){
                return true;
            }
        } return false;
    }
    public boolean isAdmin(int userId, int shopId){
        List<UserPermissions> allLinks = userPermRepo.findByUserId(userId);
        for(UserPermissions u:allLinks){
            if(u.getShop().getShopId() == shopId){
                if (u.getAdminType().getAdminTypeId() == 2) {
                    return true;
                }
            }
        } return false;
    }

    public boolean isAnyAdmin(int userId){
        List<UserPermissions> allLinks = userPermRepo.findByUserId(userId);
        for(UserPermissions u:allLinks){
            if(u.getAdminType().getAdminTypeId() == 2){
                return true;
            }
        }return false;
    }

    public int userAdminShopId(int userId){
        List<UserPermissions> allLinks = userPermRepo.findByUserId(userId);
        for(UserPermissions u:allLinks){
            if(u.getAdminType().getAdminTypeId() == 2){
                return (int) u.getShop().getShopId();
            }
        }return 0;
    }
}
