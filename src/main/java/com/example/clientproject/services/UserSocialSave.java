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
        if(!usDTO.getInstagram().equalsIgnoreCase("")){
            query = queryGenerator(shopId, "Instagram", usDTO.getInstagram());
            System.out.println(query);
            jdbc.execute(query);
            System.out.println("Instagram updated");
        }
        if(!usDTO.getFacebook().equalsIgnoreCase("")){
            query = queryGenerator(shopId, "Facebook", usDTO.getFacebook());
            jdbc.execute(query);
        }
        if(!usDTO.getTwitter().equalsIgnoreCase("")){
            query = queryGenerator(shopId, "Twitter", usDTO.getTwitter());
            jdbc.execute(query);
        }
        if(!usDTO.getTiktok().equalsIgnoreCase("")){
            query = queryGenerator(shopId, "TikTok", usDTO.getTiktok());
            jdbc.execute(query);
        }
        if(!usDTO.getShopUrl().equalsIgnoreCase("")){
            //DO SOMETHING
        }
    }

}
