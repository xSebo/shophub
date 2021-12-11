package com.example.clientproject.services;

import com.example.clientproject.service.LoggingService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.http.HttpSession;

@Service
public class UserFavouriteTagSaver {

    JdbcTemplate jdbc;
    LoggingService loggingService;

    public UserFavouriteTagSaver(JdbcTemplate jdbc, LoggingService loggingService) {
        this.jdbc = jdbc;
        this.loggingService = loggingService;
    }

    public void saveUserFavTag(int userID, String tagID, HttpSession session){

        String disableFKeyChecks = "SET FOREIGN_KEY_CHECKS=0;";
        String enableFKeyChecks = "SET FOREIGN_KEY_CHECKS=1;";
        jdbc.execute(disableFKeyChecks);

        String query = "INSERT INTO user_favourite_tags (User_Id, Tag_Id) VALUES ("+ userID + ","+tagID + ")";
        jdbc.execute(query);
        jdbc.execute(enableFKeyChecks);
        // Log the changes
        loggingService.logEvent(
                "UserFavouriteTag Inserted",
                session,
                "UserFavouriteTag Inserted with User Id: " + userID +
                        "and Tag Id: " + tagID +
                        " in UserFavouriteTagSaver.saveUserFavTag()"
        );
    }
}


