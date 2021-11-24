package com.example.clientproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserFavouriteSaver {

    @Autowired
    JdbcTemplate jdbc;

    /**
     * Takes a user dto and saves it to the DB with jdbc
     * @param urfDTO UserfavouriteDTO
     */
    public void save(UserFavouriteDTO urfDTO){

        String query = "INSERT INTO User_Shop_Links (Shop_Id, User_Id) VALUES ("+ urfDTO.getShopId() +
                ","+urfDTO.getUserId() + ")";

        //System.out.println(query);

        jdbc.execute(query);

    }
}
