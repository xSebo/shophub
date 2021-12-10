package com.example.clientproject.data;


import com.example.clientproject.data.categories.Categories;
import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.services.ShopActiveService;
import org.jetbrains.annotations.ApiStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(scripts={"/schema-test-h2.sql","/script-test-h2.sql"})
@ActiveProfiles("h2")
@DirtiesContext
public class ShopActivityTests {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    ShopActiveService shopActiveService;

    @Autowired
    ShopsRepo shopsRepo;

    @Test
    public void activeShopsDecreasedBy1AfterMethodCalled(){
        List<Shops> activeShopsListBeforeChange = shopsRepo.findActiveShops();
        shopActiveService.updateShopActive(6, 0, new MockHttpSession());
        List<Shops> activeShopsListAfterChange = shopsRepo.findActiveShops();
        assertEquals(activeShopsListBeforeChange.size()-1, activeShopsListAfterChange.size());
        //size after change should be equal to size before change minus one
    }

}
