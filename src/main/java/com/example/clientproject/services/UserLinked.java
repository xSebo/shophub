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
}
