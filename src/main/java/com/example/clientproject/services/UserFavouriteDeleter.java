package com.example.clientproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserFavouriteDeleter {

    @Autowired
    JdbcTemplate jdbc;

    /**
     * Takes a userfavourite DTO and removes it from the database.
     * @param usfDTO
     */
    public void delete(UserFavouriteDTO usfDTO){

        String query = "DELETE FROM User_Shop_Links WHERE (Shop_Id = " +
                usfDTO.getShopId() +" AND User_Id = " +
                usfDTO.getUserId() +")";

        //System.out.println(query);

        jdbc.execute(query);

    }
}
