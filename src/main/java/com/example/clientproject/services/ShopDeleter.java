package com.example.clientproject.services;

import com.example.clientproject.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ShopDeleter {
    JdbcTemplate jdbc;
    LoggingService loggingService;

    public ShopDeleter(JdbcTemplate jdbc, LoggingService loggingService) {
        this.jdbc = jdbc;
        this.loggingService = loggingService;
    }

    /**
     * @param shopId - the shopID of the shop that the stored procedure is going to delete
     */
    public void deleteShop(Integer shopId, HttpSession session){
        String query = "CALL `deleteShop`('" + shopId + "');";
        jdbc.execute(query);
        // Log the change
        loggingService.logEvent(
                "Deleted Shop",
                session,
                "Shop deleted with Shop Id: " + shopId +
                        " in ShopDeleter.deleteShop()"
        );

    }

}
