package com.example.clientproject.services;

import com.example.clientproject.data.socials.SocialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserSocialSave {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    SocialsRepo socialRepo;

    private String queryGenerator(int shopId, String socialPlatform, String socialName){
        String query;
        query = "UPDATE Socials SET Social_Name = '"+
            socialName+
            "' WHERE Shop_Id = "+ shopId +
            " AND Social_Platform = '" + socialPlatform +"'";
        return query;
        }

    public void updateSocials(UserSocialDTO usDTO){
        int shopId = usDTO.getShopId();
        String query;
        query = queryGenerator(shopId, "Instagram", usDTO.getInstagram());
        jdbc.execute(query);
        query = queryGenerator(shopId, "Facebook", usDTO.getFacebook());
        jdbc.execute(query);
        query = queryGenerator(shopId, "Twitter", usDTO.getTwitter());
        jdbc.execute(query);
        query = queryGenerator(shopId, "TikTok", usDTO.getTiktok());
        jdbc.execute(query);
        query = "UPDATE Shops SET Shop_Website = '" +
                usDTO.getShopUrl() +
                "' WHERE Shop_Id = " + shopId;

        jdbc.execute(query);

    }

}
