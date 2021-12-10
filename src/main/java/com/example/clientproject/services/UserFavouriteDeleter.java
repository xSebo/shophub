package com.example.clientproject.services;

import com.example.clientproject.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserFavouriteDeleter {

    JdbcTemplate jdbc;
    LoggingService loggingService;

    public UserFavouriteDeleter(JdbcTemplate jdbc, LoggingService loggingService) {
        this.jdbc = jdbc;
        this.loggingService = loggingService;
    }

    /**
     * Takes a userfavourite DTO and removes it from the database.
     * @param usfDTO - usfDTO
     * @param session - session
     */
    public void delete(UserFavouriteDTO usfDTO, HttpSession session){

        String query = "DELETE FROM User_Shop_Links WHERE (Shop_Id = " +
                usfDTO.getShopId() +" AND User_Id = " +
                usfDTO.getUserId() +")";
        jdbc.execute(query);
        // Log the change
        loggingService.logEvent(
                "UserShopLink Deleted",
                session,
                "UserShopLink deleted with Shop Id: " + usfDTO.getShopId() +
                        " and User Id: " + usfDTO.getUserId() +
                        " in UserFavouriteDeleter.delete()"
        );
    }
}
