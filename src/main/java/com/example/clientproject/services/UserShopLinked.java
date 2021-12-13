package com.example.clientproject.services;

import com.example.clientproject.data.adminTypes.AdminTypesRepo;
import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserShopLinked {

    private UserPermissionsRepo userPermissionsRepo;

    @Autowired
    JdbcTemplate jdbc;

    public UserShopLinked(UserPermissionsRepo aUserPermissionsRepo){
        userPermissionsRepo = aUserPermissionsRepo;
    }

    public boolean hasShop(long userId){
        try{
            //System.out.println(userId);
            //System.out.println(userPermissionsRepo.findByUserId(userId).get(1).getShop().getShopId());
            if((userPermissionsRepo.findByUserId(userId).get(0).getShop().getShopId() != 1) ||
                    (userPermissionsRepo.findByUserId(userId).get(1).getShop().getShopId() != 1)){
                return true;
            }else{
                return false;
            }
        }catch(IndexOutOfBoundsException e){
            //e.printStackTrace();
            return false;
        }
    }

    public List<Integer> getByUserId(Long userId){
        String query = "SELECT s.Shop_Id FROM User_Permissions s WHERE (User_Id = " +userId.toString() +
                " AND Shop_Id != 1)";

        List<Map<String, Object>> rs = jdbc.queryForList(query);
        List<Integer> shops = new ArrayList<Integer>();

        for(Map<String, Object> shopLink : rs){
            shops.add((Integer) shopLink.get("Shop_Id"));
        }

        return shops;
    }
}
