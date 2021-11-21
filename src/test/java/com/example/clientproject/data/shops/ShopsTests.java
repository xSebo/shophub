package com.example.clientproject.data.shops;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Sql(scripts={"/schema-test-h2.sql","/script-test-h2.sql"})
@ActiveProfiles("h2")
@DirtiesContext
public class ShopsTests {
    @Autowired
    ShopsRepo shopsRepo;

    @Test
    public void shouldGet11Shops() throws Exception {
        List<Shops> shopsList = shopsRepo.findAll();
        assertEquals(11, shopsList.size());
    }

    @Test
    public void shouldGet12ShopsAfterInsert() throws Exception {
        Shops newShop = new Shops("", "", "", 0, "", "", true);
        Shops shop = shopsRepo.save(newShop);

        List<Shops> shopsList = shopsRepo.findAll();
        assertEquals(12, shopsList.size());
    }
}