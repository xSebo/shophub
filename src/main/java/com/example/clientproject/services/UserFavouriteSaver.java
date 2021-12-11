package com.example.clientproject.services;

import com.example.clientproject.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

//This can be deleted, class "UserFavouriteTagSaver" is being used
@Service
public class UserFavouriteSaver {

    JdbcTemplate jdbc;
    LoggingService loggingService;

    public UserFavouriteSaver(JdbcTemplate jdbc, LoggingService loggingService) {
        this.jdbc = jdbc;
        this.loggingService = loggingService;
    }

    /**
     * Takes a user dto and saves it to the DB with jdbc
     * @param urfDTO UserFavouriteDTO
     * @param session - session
     */
    public void save(UserFavouriteDTO urfDTO, HttpSession session){
        String query = "INSERT INTO User_Shop_Links (Shop_Id, User_Id) VALUES ("+ urfDTO.getShopId() +
                ","+urfDTO.getUserId() + ")";
        jdbc.execute(query);
        // Log the change
        loggingService.logEvent(
                "UserShopLink Inserted",
                session,
                "UserShopLink inserted with Shop Id: " + urfDTO.getShopId() +
                        " and User Id: " + urfDTO.getUserId() +
                        " in UserFavouriteSaver.save()"
        );
    }
}
