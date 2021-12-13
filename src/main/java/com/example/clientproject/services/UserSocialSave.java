package com.example.clientproject.services;

import com.example.clientproject.data.socials.SocialsRepo;
import com.example.clientproject.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserSocialSave {

    JdbcTemplate jdbc;
    SocialsRepo socialRepo;
    LoggingService loggingService;

    public UserSocialSave(JdbcTemplate jdbc, SocialsRepo socialRepo, LoggingService loggingService) {
        this.jdbc = jdbc;
        this.socialRepo = socialRepo;
        this.loggingService = loggingService;
    }

    private String queryGenerator(int shopId, String socialPlatform, String socialName){
        String query;
        query = "UPDATE Socials SET Social_Name = '"+
            socialName+
            "' WHERE Shop_Id = "+ shopId +
            " AND Social_Platform = '" + socialPlatform +"'";
        return query;
        }

    public void updateSocials(UserSocialDTO usDTO, HttpSession session){
        int shopId = usDTO.getShopId();
        String query;
        query = queryGenerator(shopId, "Instagram", usDTO.getInstagram());
        jdbc.execute(query);
        // Log the changes
        loggingService.logEvent(
                "Social Updated",
                session,
                "Social updated for Shop Id: " + shopId +
                        " in UserSocialSave.updateSocials()"
        );

        query = queryGenerator(shopId, "Facebook", usDTO.getFacebook());
        jdbc.execute(query);
        // Log the changes
        loggingService.logEvent(
                "Social Updated",
                session,
                "Social updated for Shop Id: " + shopId +
                        " in UserSocialSave.updateSocials()"
        );

        query = queryGenerator(shopId, "Twitter", usDTO.getTwitter());
        jdbc.execute(query);
        // Log the changes
        loggingService.logEvent(
                "Social Updated",
                session,
                "Social updated for Shop Id: " + shopId +
                        " in UserSocialSave.updateSocials()"
        );

        query = queryGenerator(shopId, "TikTok", usDTO.getTiktok());
        jdbc.execute(query);
        // Log the changes
        loggingService.logEvent(
                "Social Updated",
                session,
                "Social updated for Shop Id: " + shopId +
                        " in UserSocialSave.updateSocials()"
        );

        query = "UPDATE Shops SET Shop_Website = '" +
                usDTO.getShopUrl() +
                "' WHERE Shop_Id = " + shopId;
        jdbc.execute(query);
        // Log the changes
        loggingService.logEvent(
                "ShopWebsite Updated",
                session,
                "Shop Website updated for Shop Id: " + shopId +
                        " in UserSocialSave.updateSocials()"
        );
    }
}
