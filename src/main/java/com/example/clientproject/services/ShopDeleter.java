package com.example.clientproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ShopDeleter {
    @Autowired
    JdbcTemplate jdbc;

    /**
     * @param shopId - the shopID of the shop that the stored procedure is going to delete
     */
    public void deleteShop(Integer shopId){
        String query = "CALL `mydb`.`deleteShop`('" + shopId + "');";
        jdbc.execute(query);
    }

}
