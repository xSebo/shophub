package com.example.clientproject.services;

import com.example.clientproject.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public class StampboardUpdater {
    JdbcTemplate jdbc;
    LoggingService loggingService;

    public StampboardUpdater(JdbcTemplate jdbc, LoggingService loggingService) {
        this.jdbc = jdbc;
        this.loggingService = loggingService;
    }

    //When using ensure you have validated authority first

    public void updateColour(Integer shopId, String colour, HttpSession session){
        String query = "UPDATE stamp_boards SET Stamp_Board_Colour = '"+ colour +"' WHERE Stamp_Board_Id = (" +
                "SELECT stamp_boards.Stamp_Board_Id FROM stamp_boards INNER JOIN " +
                "shops on shops.Stamp_Board_Id = stamp_boards.Stamp_Board_Id where shops.Shop_Id = "+ shopId +" limit 1);";
        jdbc.execute(query);
        // Log the change
        loggingService.logEvent(
                "Stamp Board Updated",
                session,
                "Stamp Board updated for Shop: " + shopId +
                        " with field: Stamp_Board_Colour with value: " + colour +
                        " in StampboardUpdater.updateColour()"
        );
    }

    public void updateRewards(Integer shopId, Map<String,Object> rewards, HttpSession session){
        String deleteQuery = "delete from rewards where Stamp_Board_Id = (" +
                "SELECT Stamp_Board_Id from shops where Shop_Id = "+shopId+");";
        jdbc.execute(deleteQuery);
        // Log the change
        loggingService.logEvent(
                "Reward Deleted",
                session,
                "Rewards deleted for StampBoard for Shop: " + shopId +
                        " in StampboardUpdater.updateRewards()"
        );

        rewards.entrySet().forEach(r -> {
            String insertQuery = "INSERT into rewards (Reward_Name, Reward_Stamp_Location,Stamp_Board_Id) values (\""+r.getValue()+"\", " +
                    r.getKey()+",(SELECT Stamp_Board_Id from shops where Shop_Id = "+ shopId.toString() +"));";
            jdbc.execute(insertQuery);
            // Log the change
            loggingService.logEvent(
                    "New Reward",
                    session,
                    "Reward inserted for StampBoard for Shop: " + shopId +
                            " in StampboardUpdater.updateRewards()"
            );
        });
    }

    public void updateStampboardSize(Integer shopId, Integer size, HttpSession session){
        String query = "UPDATE stamp_boards SET Stamp_Board_Size = "+ size +" WHERE Stamp_Board_Id = (" +
                "SELECT stamp_boards.Stamp_Board_Id FROM stamp_boards INNER JOIN " +
                "shops on shops.Stamp_Board_Id = stamp_boards.Stamp_Board_Id where shops.Shop_Id = "+ shopId +" limit 1);";
        jdbc.execute(query);
        // Log the change
        loggingService.logEvent(
                "Stamp Board Updated",
                session,
                "StampBoard updated for Shop: " + shopId +
                        " with: field Stamp_BoardSize with value:" + size +
                        " in StampboardUpdater.updateStampboardSize()"
        );
    }

    public void updateIconURL(Integer shopId, String url, HttpSession session){
        String query = "UPDATE stamp_boards SET Stamp_Board_Icon = '"+ url +"' WHERE Stamp_Board_Id = (" +
                "SELECT stamp_boards.Stamp_Board_Id FROM stamp_boards INNER JOIN " +
                "shops on shops.Stamp_Board_Id = stamp_boards.Stamp_Board_Id where shops.Shop_Id = "+ shopId +" limit 1);";
        jdbc.execute(query);
        // Log the change
        loggingService.logEvent(
                "Stamp Board Updated",
                session,
                "StampBoard updated for Shop: " + shopId +
                        " with: field Stamp_Board_Icon with value:" + url +
                        " in StampboardUpdater.updateIconURL()"
        );
    }
}
