package com.example.clientproject.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class UserFavouriteTagSaver {

    @Autowired
    JdbcTemplate jdbc;

    public void saveUserFavTag(int UserID, String TagID){

        String disableFKeyChecks = "SET FOREIGN_KEY_CHECKS=0;";
        String enableFKeyChecks = "SET FOREIGN_KEY_CHECKS=1;";
        jdbc.execute(disableFKeyChecks);

        String query = "INSERT INTO user_favourite_tags (User_Id, Tag_Id) VALUES ("+ UserID + ","+TagID + ")";
        jdbc.execute(query);
        jdbc.execute(enableFKeyChecks);
    }

}


