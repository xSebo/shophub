package com.example.clientproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ShopDeleter {
    @Autowired
    JdbcTemplate jdbc;

    public void deleteShop(Integer shopId){
        String query = "CALL `mydb`.`deleteShop`('" + shopId + "');";
        jdbc.execute(query);
    }

}
