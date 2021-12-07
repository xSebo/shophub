package com.example.clientproject.services;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.exceptions.ForbiddenErrorException;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.web.forms.UserFavouriteForm;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class RecommendationGenerator {
    public JWTUtils jwtUtils;
    public ShopsRepo shopsRepo;
    public UserFavouriteToggle favouriteToggle;

    public RecommendationGenerator(JWTUtils jwt, ShopsRepo sr, UserFavouriteToggle uft){
        jwtUtils = jwt;
        shopsRepo = sr;
        favouriteToggle = uft;
    }

    public String getRecommendations(HttpSession session) throws Exception {

        Long userId = 1L;
        List<Tags> tags = new ArrayList<Tags>();

        Optional<Users> user = jwtUtils.getLoggedInUserRow(session);
        if(user.isPresent()){
            tags = user.get().getFavouriteTags();
            userId = user.get().getUserId();
        }else{
            return "User not logged in";
        }

        //Make the user weights list
        HashMap<String, Float> tagWeights = new HashMap<>();

        //Add 1 point of relevancy for each tag that the user favoured on startup
        for(Tags tag : tags){
            tagWeights.put(tag.getTagName().toLowerCase().strip(), 1.0f);
        }

        //Get the shops that the user has starred
        List<Shops> allShops = shopsRepo.findAll();
        List<Long> favoriteShops = new ArrayList<>();
        //For each shop
        for(Shops s : allShops){
            UserFavouriteForm uff = new UserFavouriteForm(s.getShopId());
            //If the shop is starred
            if(favouriteToggle.alreadyInDb(new UserFavouriteDTO(uff, userId))){
                favoriteShops.add(s.getShopId());
                //Get that shop's tags
                List<Tags> shopTags = s.getShopTags();
                //For each tag
                for(Tags tag : shopTags){
                    //Add 1 to tag relevancy or add the tag at 1 if not exists
                    String tagName = tag.getTagName().toLowerCase().strip();
                    if(tagWeights.containsKey(tagName)){
                        tagWeights.put(tagName, tagWeights.get(tagName) + 1);
                    }else{
                        tagWeights.put(tagName, 1.0f);
                    }

                }
            }
        }

        //Get the shops the user has stamps with


        return tagWeights.toString();

    }
}
