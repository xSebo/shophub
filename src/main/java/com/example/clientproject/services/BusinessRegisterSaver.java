package com.example.clientproject.services;

import com.example.clientproject.data.categories.Categories;
import com.example.clientproject.data.categories.CategoriesRepo;
import com.example.clientproject.data.rewards.Rewards;
import com.example.clientproject.data.rewards.RewardsRepo;
import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.socials.Socials;
import com.example.clientproject.data.socials.SocialsRepo;
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
import java.util.ArrayList;
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
    SocialsRepo socialsRepo;

    @Autowired
    TagsRepo tagsRepo;

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    LinkUserShop linkShop;

    @Autowired
    RewardsRepo rewardsRepo;

    public void save(BusinessRegisterDTO business, long userId){

        String query = "INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (8, '#ff0000', 'stamp.jpg')";
        jdbc.execute(query);

        long currentStampId = stampBoards.findAll().get(stampBoards.findAll().size()-1).getStampBoardId();
        String rewardsQuery = "INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES (\"10% off\", 4," +
                currentStampId +  ")";
        //System.out.println(rewardsQuery);
        jdbc.execute(rewardsQuery);

        StampBoards stampBoard = stampBoards.findAll().get(stampBoards.findAll().size()-1);

        Categories category;

        category = categoriesRepo.findByName(business.getBusinessCategory());

        Shops shop = new Shops(business.getBusiness_register_name(),
                business.getBusiness_register_url(),
                business.getBusiness_register_desc(),
                business.getEarnings(),
                "shopPic.png",
                "shopBanner.png",
                "UK United Kingdom",
                false,
                stampBoard,
                category);

        //System.out.println(category.getCategoryId());

        //System.out.println(shop.getStampBoard());

        shopsRepo.save(shop);
        List<Tags> tagsList = tagsRepo.findAll();

        linkShop.linkUserShop(shop.getShopId(), userId, 2L);

        for(String t: business.getBusinessTags()){
            if(tagsList.contains(new Tags(t))){
                continue;
            }
            //long id = 0;
            Tags tag = new Tags(t);
            tagsRepo.save(tag);


            query = "INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES ("+ shop.getShopId() +
                    ","+tag.getTagId() + ")";

            jdbc.execute(query);

        }

        socialsRepo.save(new Socials(shop, "Facebook", business.getFacebook()));
        socialsRepo.save(new Socials(shop, "Twitter", business.getTwitter()));
        socialsRepo.save(new Socials(shop, "Instagram", business.getInstagram()));
        socialsRepo.save(new Socials(shop, "TikTok", business.getTiktok()));


        //System.out.println(shop.getShopId());

        //System.out.println(tagsRepo.findAll());

        //System.out.println(shopsRepo.findByShopName(business.getBusiness_register_name()).get().getShopName());
    }

}
