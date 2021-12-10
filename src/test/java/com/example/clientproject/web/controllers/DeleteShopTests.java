package com.example.clientproject.web.controllers;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.services.ShopDeleter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(scripts={"/schema-test-h2.sql","/script-test-h2.sql"})
@ActiveProfiles("h2")
@DirtiesContext
public class DeleteShopTests {

    @Autowired
    ShopDeleter shopDeleter;

    @Autowired
    ShopsRepo shopsRepo;

    //this test wont work because stored procedure is on MYSQL and this is running on h2
    //in future make a jdbc service that will do the exact same thing as the stored procedure
    @Test
    public void shouldDeleteAllShopData() throws Exception{
        shopDeleter.deleteShop(12, null);
        List<Shops> shopsList = shopsRepo.findAll();
        assertEquals(11, shopsList.size());
    }



}
