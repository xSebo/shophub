package com.example.clientproject.services;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.stampBoards.StampBoards;
import com.example.clientproject.data.stampBoards.StampBoardsRepo;
import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.userStampBoards.UserStampBoards;
import com.example.clientproject.data.userStampBoards.UserStampBoardsRepo;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.data.users.UsersRepo;
import com.example.clientproject.exceptions.ForbiddenErrorException;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.web.forms.UserFavouriteForm;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class RecommendationGenerator {
    JWTUtils jwtUtils;
    UserFavouriteToggle favouriteToggle;
    StampBoardsRepo stampBoardsRepo;
    UserStampBoardsRepo userStampBoardsRepo;
    UsersRepo usersRepo;
    ShopsRepo shopsRepo;

    public RecommendationGenerator(JWTUtils jwtUtils, UserFavouriteToggle favouriteToggle, StampBoardsRepo stampBoardsRepo, UserStampBoardsRepo userStampBoardsRepo, UsersRepo usersRepo, ShopsRepo shopsRepo) {
        this.jwtUtils = jwtUtils;
        this.favouriteToggle = favouriteToggle;
        this.stampBoardsRepo = stampBoardsRepo;
        this.userStampBoardsRepo = userStampBoardsRepo;
        this.usersRepo = usersRepo;
        this.shopsRepo = shopsRepo;
    }

    public List<Shops> getRecommendations(HttpSession session, List<Shops> shopsToRecommend) throws Exception {

        Long userId = 1L;
        List<Tags> tags = new ArrayList<Tags>();

        Optional<Users> user = jwtUtils.getLoggedInUserRow(session);
        if(user.isPresent()){
            tags = user.get().getFavouriteTags();
            userId = user.get().getUserId();
        }else{
            throw new ForbiddenErrorException("User Not Logged In");
        }

        //Make the user weights list
        HashMap<String, Double> tagWeights = new HashMap<>();

        //Add 1 point of relevancy for each tag that the user favoured on startup
        for(Tags tag : tags){
            tagWeights.put(tag.getTagName().toLowerCase().strip(), 1.0d);
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
                        tagWeights.put(tagName, 1.0d);
                    }

                }
            }
        }

        //Get the shops the user has stamps with
        Set<UserStampBoards> userStampBoards = usersRepo.findById(user.get().getUserId()).get().getUserStampBoards();
        List<Long> purchasedFromShops = new ArrayList<>();
        //For every board linked to the user
        for(UserStampBoards board : userStampBoards){
            //Calculate the factor to multiply the relevance by
            double multFactor = 1 + (0.01*board.getUserStampPosition());

            //Get shop linked
            Optional<Shops> shop = shopsRepo.findByStampId(board.getStampBoard().getStampBoardId());
            if (shop.isPresent()){
                Shops s = shop.get();
                //Add to list of shops purchased from
                purchasedFromShops.add(s.getShopId());
                //Get that shop's tags
                List<Tags> shopTags = s.getShopTags();
                //For each tag
                for(Tags tag : shopTags){
                    //Add 2*factor to tag relevancy or add the tag at 1 if not exists
                    String tagName = tag.getTagName().toLowerCase().strip();
                    if(tagWeights.containsKey(tagName)){
                        tagWeights.put(tagName, tagWeights.get(tagName) + (2 * multFactor));
                    }else{
                        tagWeights.put(tagName, 2.0d*multFactor);
                    }
                }
            }
        }

        //Calculate weights for each shop, later do this based off a list of passed in shops
        //Ignore shops that have been starred or where the user has a stamp
        List<HashMap<Shops, Double>> weightedShops = new ArrayList<>();
        for(Shops shop : shopsToRecommend){
            //If the shop isn't starred or purchased from
            if(!purchasedFromShops.contains(shop.getShopId()) && !favoriteShops.contains(shop.getShopId())){
                double weight = 0;
                //Loop of each tag of the shop and add relevancy accordingly
                for(Tags tag : shop.getShopTags()){
                    //If the tag has a weight
                    if(tagWeights.containsKey(tag.getTagName())){
                        weight += tagWeights.get(tag.getTagName());
                    }
                }
                HashMap<Shops, Double> shopPair = new HashMap<>();
                shopPair.put(shop, weight);
                weightedShops.add(shopPair);
            }
        }

        Collections.sort(weightedShops, new Comparator<HashMap<Shops, Double>>(){
            public int compare(HashMap<Shops, Double> one, HashMap<Shops, Double> two) {
                return two.entrySet().iterator().next().getValue().compareTo(one.entrySet().iterator().next().getValue());
            }
        });

        //Only return the shop objects
        List<Shops> recommendations = new ArrayList<>();
        for(HashMap<Shops, Double> s: weightedShops){
            recommendations.add(s.entrySet().iterator().next().getKey());
        }

        return recommendations;
    }
}
