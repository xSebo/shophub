package com.example.clientproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ShopActiveService {
    @Autowired
    JdbcTemplate jdbc;


    /**
     * @param shopId - uses shopId to select which shop will have its active field checked
     * @return - an int of Shop activity 1 = active || 0 = not active
     */
    public int isShopActive(Integer shopId){
        String query = "SELECT Shop_Active FROM shops WHERE Shop_Id = " + shopId + ";";
        try{
            List<Map<String, Object>> rs = jdbc.queryForList(query);

            System.out.println((int) rs.get(0).get("Shop_Active"));
            return (int) rs.get(0).get("Shop_Active");
        }catch (Exception e){
            return 404;
        }
    }

    /**
     * @param shopId - uses shopId to select which shop will have its active field changed/updated
     * @param active - will either be 1 or 0 and will update shops "Shop_Active" field accordingly
     */

    public void updateShopActive(Integer shopId, Integer active){
        if(active == 0 || active == 1){ //only allows active values of 0 or 1
            String query = "UPDATE shops SET Shop_Active = " + active + " WHERE Shop_Id = " + shopId + ";";
            jdbc.execute(query);
        }
    }


}