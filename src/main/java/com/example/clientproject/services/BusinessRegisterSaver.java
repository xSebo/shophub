package com.example.clientproject.services;

import com.example.clientproject.data.categories.Categories;
import com.example.clientproject.data.categories.CategoriesRepo;
import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.stampBoards.StampBoards;
import com.example.clientproject.data.stampBoards.StampBoardsRepo;
import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.tags.TagsRepo;
import com.example.clientproject.data.userStampBoards.UserStampBoards;
import com.example.clientproject.data.userStampBoards.UserStampBoardsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
public class BusinessRegisterSaver {

    @Autowired
    ShopsRepo shopsRepo;

    @Autowired
    StampBoardsRepo stampBoards;

    @Autowired
    CategoriesRepo categoriesRepo;

    @Autowired
    TagsRepo tagsRepo;

    @Autowired
    JdbcTemplate jdbc;

    public void save(BusinessRegisterDTO business){

        StampBoards stampBoard = stampBoards.findById(1L).get();
        Categories categories = categoriesRepo.findById(1L).get();

        Shops shop = new Shops(business.getBusiness_register_name(),
                business.getBusiness_register_url(),
                business.getBusiness_register_desc(),
                business.getEarnings(),
                "shopPic.png",
                "UK United Kingdom",
                false,
                stampBoard,
                categories);

        shopsRepo.save(shop);
        List<Tags> tagsList = tagsRepo.findAll();

        for(String t: business.getBusinessTags()){
            if(tagsList.contains(new Tags(t))){
                // Link shop to tag
                continue;
            }
            //long id = 0;
            Tags tag = new Tags(t);
            tagsRepo.save(tag);


            String query = "INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES ("+ shop.getShopId() +
                    ","+tag.getTagId() + ")";

            jdbc.execute(query);

        }
        //System.out.println(shop.getShopId());

        //System.out.println(tagsRepo.findAll());

        System.out.println(shopsRepo.findByShopName(business.getBusiness_register_name()).get().getShopName());
    }

}
