package com.example.clientproject.service.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CheckFavourite {
    @Autowired
    JdbcTemplate jdbc;

    public boolean isFavourite(int shopId, int userId){
        String query = "SELECT s.User_Shop_Link_Id FROM User_Shop_Links s WHERE (Shop_Id = " +
                shopId + " AND User_Id = " +
                userId + ")";

        List<Map<String, Object>> rs = jdbc.queryForList(query);
        return rs.size() != 0;
    }
}
