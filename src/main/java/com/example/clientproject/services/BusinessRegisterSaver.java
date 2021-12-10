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
import com.example.clientproject.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class BusinessRegisterSaver {

    private ShopsRepo shopsRepo;
    private StampBoardsRepo stampBoards;
    private CategoriesRepo categoriesRepo;
    private SocialsRepo socialsRepo;
    private TagsRepo tagsRepo;
    private JdbcTemplate jdbc;
    private LinkUserShop linkShop;
    private LoggingService loggingService;

    /**
     * Constructor
     * @param shopsRepo - shopsRepo
     * @param stampBoards - stampBoards
     * @param categoriesRepo - categoriesRepo
     * @param socialsRepo - socialsRepo
     * @param tagsRepo - tagsRepo
     * @param jdbc - jdbc
     * @param linkShop - linkShop
     * @param rewardsRepo - rewardsRepo
     * @param loggingService - loggingService
     */
    public BusinessRegisterSaver(ShopsRepo shopsRepo, StampBoardsRepo stampBoards, CategoriesRepo categoriesRepo, SocialsRepo socialsRepo, TagsRepo tagsRepo, JdbcTemplate jdbc, LinkUserShop linkShop, RewardsRepo rewardsRepo, LoggingService loggingService) {
        this.shopsRepo = shopsRepo;
        this.stampBoards = stampBoards;
        this.categoriesRepo = categoriesRepo;
        this.socialsRepo = socialsRepo;
        this.tagsRepo = tagsRepo;
        this.jdbc = jdbc;
        this.linkShop = linkShop;
        this.loggingService = loggingService;
    }

    public void save(BusinessRegisterDTO business, long userId, HttpSession session){

        String query = "INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (8, '#ff0000', 'stamp.jpg')";
        jdbc.execute(query);
        // Log the change
        loggingService.logEvent(
                "New Stamp Board",
                session,
                "New StampBoard created for Shop: " +  business.getBusiness_register_name() +
                        " in BusinessRegisterSaver.save()"
        );

        long currentStampId = stampBoards.findAll().get(stampBoards.findAll().size()-1).getStampBoardId();
        String rewardsQuery = "INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES (\"10% off\", 4," +
                currentStampId +  ")";
        //System.out.println(rewardsQuery);
        jdbc.execute(rewardsQuery);
        // Log the change
        loggingService.logEvent(
                "New Reward",
                session,
                "New Reward created for StampBoard: " +  currentStampId +
                        " in BusinessRegisterSaver.save()"
        );

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
        // Log the change
        loggingService.logEvent(
                "New Shop",
                session,
                "New Shop created for User: " + userId +
                        " in BusinessRegisterSaver.save()"
        );

        List<String> tagsList = new ArrayList<>();
        List<String> tagsLowerList = new ArrayList<>();
        tagsRepo.findAll().forEach(x -> tagsList.add(x.getTagName()));
        tagsRepo.findAll().forEach(x -> tagsLowerList.add(x.getTagName().toLowerCase()));

        System.out.println(tagsLowerList);
        business.getBusinessTags().forEach(x-> System.out.println(x));

        linkShop.linkUserShop(shop.getShopId(), userId, 2L, session);

        for(String t: business.getBusinessTags()){
            Tags tag;
            if(tagsLowerList.contains(t.toLowerCase())){
                tag = tagsRepo.findByTagNameIgnoreCase(t).get();
            }else{
                tag = new Tags(t.toLowerCase());
                tagsRepo.save(tag);
                // Log the change
                loggingService.logEvent(
                        "New Tag",
                        session,
                        "New Tag created with name: " + tag.getTagName() +
                                " in BusinessRegisterSaver.save()"
                );
            }

            query = "INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES ("+ shop.getShopId() +
                    ","+tag.getTagId() + ")";
            jdbc.execute(query);
            // Log the change
            loggingService.logEvent(
                    "New Shop Tag Link",
                    session,
                    "New Shop Tag Link created for shop: " + shop.getShopId() +
                            " and tag: " + tag.getTagId() +
                            " in BusinessRegisterSaver.save()"
            );
        }

        socialsRepo.save(new Socials(shop, "Facebook", business.getFacebook()));
        // Log the change
        loggingService.logEvent(
                "New Social",
                session,
                "New Social created for shop: " + shop.getShopId() +
                        " in BusinessRegisterSaver.save()"
        );

        socialsRepo.save(new Socials(shop, "Twitter", business.getTwitter()));
        // Log the change
        loggingService.logEvent(
                "New Social",
                session,
                "New Social created for shop: " + shop.getShopId() +
                        " in BusinessRegisterSaver.save()"
        );

        socialsRepo.save(new Socials(shop, "Instagram", business.getInstagram()));
        // Log the change
        loggingService.logEvent(
                "New Social",
                session,
                "New Social created for shop: " + shop.getShopId() +
                        " in BusinessRegisterSaver.save()"
        );

        socialsRepo.save(new Socials(shop, "TikTok", business.getTiktok()));
        // Log the change
        loggingService.logEvent(
                "New Social",
                session,
                "New Social created for shop: " + shop.getShopId() +
                        " in BusinessRegisterSaver.save()"
        );


        //System.out.println(shop.getShopId());

        //System.out.println(tagsRepo.findAll());

        //System.out.println(shopsRepo.findByShopName(business.getBusiness_register_name()).get().getShopName());
    }

}
