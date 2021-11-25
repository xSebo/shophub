package com.example.clientproject.data;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.stampBoards.StampBoardsRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @Autowired
    StampBoardsRepo stampRepo;

    @Test
    public void shouldGet11Shops() throws Exception {
        List<Shops> shopsList = shopsRepo.findAll();
        assertEquals(11, shopsList.size());
    }

    @Test
    public void shouldGet12ShopsAfterInsert() throws Exception {
        Shops newShop = new Shops("", "", "", 0, "", "", true, stampRepo.getById(1L));
        Shops shop = shopsRepo.save(newShop);

        List<Shops> shopsList = shopsRepo.findAll();
        assertEquals(12, shopsList.size());
    }

    @ParameterizedTest
    @CsvSource({"1,7","2,0","4,0"})
    public void shouldGetXShopsFromCategories(long categoryId, int numExpected) {
        List<Shops> shopsList = shopsRepo.findByCategoryId(categoryId);
        assertEquals(numExpected, shopsList.size());
    }
}
