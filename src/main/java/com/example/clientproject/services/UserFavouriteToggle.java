package com.example.clientproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@Service
public class UserFavouriteToggle {

    @Autowired
    JdbcTemplate jdbc;

    /**
     * Checks whether the user has already favourited a shop
     * @param urfDTO, Userfavourite DTO
     * @return Boolean, true if it's already favourited false if not.
     * @throws Exception
     */
    public boolean alreadyInDb(UserFavouriteDTO urfDTO) throws Exception{

        String query = "SELECT s.User_Shop_Link_Id FROM User_Shop_Links s WHERE (Shop_Id = " +
                urfDTO.getShopId() + " AND User_Id = " +
                urfDTO.getUserId() + " )";

        List<Map<String, Object>> rs = jdbc.queryForList(query);
        return rs.size() != 0;



    }

}
