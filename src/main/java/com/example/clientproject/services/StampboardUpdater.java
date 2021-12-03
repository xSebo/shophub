package com.example.clientproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StampboardUpdater {
    @Autowired
    JdbcTemplate jdbc;

    //When using ensure you have validated authority first

    public void updateColour(Integer shopId, String colour){
        String query = "UPDATE stamp_boards SET Stamp_Board_Colour = '"+ colour +"' WHERE Stamp_Board_Id = (" +
                "SELECT stamp_boards.Stamp_Board_Id FROM mydb.stamp_boards INNER JOIN " +
                "shops on shops.Stamp_Board_Id = stamp_boards.Stamp_Board_Id where shops.Shop_Id = "+ shopId +" limit 1);";

        jdbc.execute(query);
    }

    public void updateRewards(Integer shopId, Map<String,Object> rewards){
        String deleteQuery = "delete from rewards where Stamp_Board_Id = (" +
                "SELECT Stamp_Board_Id from shops where Shop_Id = 12);";

        jdbc.execute(deleteQuery);

        rewards.entrySet().forEach(r -> {
            String insertQuery = "INSERT into rewards (Reward_Name, Reward_Stamp_Location,Stamp_Board_Id) values (\""+r.getValue()+"\", " +
                    r.getKey()+",(SELECT Stamp_Board_Id from shops where Shop_Id = "+ shopId.toString() +"));";

            jdbc.execute(insertQuery);
        });
    }

    public void updateStampboardSize(Integer shopId, Integer size){
        String query = "UPDATE stamp_boards SET Stamp_Board_Size = "+ size +" WHERE Stamp_Board_Id = (" +
                "SELECT stamp_boards.Stamp_Board_Id FROM mydb.stamp_boards INNER JOIN " +
                "shops on shops.Stamp_Board_Id = stamp_boards.Stamp_Board_Id where shops.Shop_Id = "+ shopId +" limit 1);";

        jdbc.execute(query);
    }
}
