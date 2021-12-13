package com.example.clientproject.web.restControllers;

import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.service.searches.UsersSearch;
import com.example.clientproject.services.*;
import com.example.clientproject.web.forms.UserFavouriteForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class BusinessFavouriter {

    private UserFavouriteSaver saveFavourite;
    private UserFavouriteToggle toggleFavourite;
    private UserFavouriteDeleter deleteFavourite;
    private JWTUtils jwtUtils;

    public BusinessFavouriter(UserFavouriteSaver ufs, UserFavouriteToggle uft, UserFavouriteDeleter ufd, JWTUtils jwt) {
        saveFavourite = ufs;
        toggleFavourite = uft;
        deleteFavourite = ufd;
        jwtUtils = jwt;
    }


    @PostMapping("/favouriteBusiness")
    public String favouriteBusiness(UserFavouriteForm uff, HttpSession session){
        UserFavouriteDTO ufDTO;
        try{
            ufDTO = new UserFavouriteDTO(uff, jwtUtils.getLoggedInUserId(session).get());
        }catch(Exception e){
            return "BAD SESSION";
        }
        try{
            if(toggleFavourite.alreadyInDb(ufDTO)){
                deleteFavourite.delete(ufDTO, session);
            }else{
                saveFavourite.save(ufDTO, session);
            }
            return "OK";
        }catch(Exception e){
            e.printStackTrace();
            return "ERROR";
        }



    }

}
