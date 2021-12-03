package com.example.clientproject.services;

import com.example.clientproject.data.adminTypes.AdminTypesRepo;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserShopLinked {

    private UserPermissionsRepo userPermissionsRepo;

    public UserShopLinked(UserPermissionsRepo aUserPermissionsRepo){
        userPermissionsRepo = aUserPermissionsRepo;
    }

    public boolean hasShop(long userId){
        try{
            if(userPermissionsRepo.findByUserId(userId).get(0).getShop().getShopId() != 1){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
