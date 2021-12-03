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

    }

    public void updateStampboardSize(Integer shopId, Integer size){
        String query = "UPDATE stamp_boards SET Stamp_Board_Size = "+ size +" WHERE Stamp_Board_Id = (" +
                "SELECT stamp_boards.Stamp_Board_Id FROM mydb.stamp_boards INNER JOIN " +
                "shops on shops.Stamp_Board_Id = stamp_boards.Stamp_Board_Id where shops.Shop_Id = "+ shopId +" limit 1);";

        jdbc.execute(query);
    }
}
