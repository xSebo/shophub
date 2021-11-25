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

    public BusinessFavouriter(UserFavouriteSaver ufs, UserFavouriteToggle uft, UserFavouriteDeleter ufd) {
        saveFavourite = ufs;
        toggleFavourite = uft;
        deleteFavourite = ufd;
    }


    /**
     *
     * @param submitted form, contains a UserID and ShopID
     * @return ERROR or OK depending on whether it any errors are thrown.
     */
    @PostMapping("/favouriteBusiness")
    public String favouriteBusiness(UserFavouriteForm uff, HttpSession session){
        UserFavouriteDTO ufDTO;
        try{
            ufDTO = new UserFavouriteDTO(uff, JWTUtils.getLoggedInUserId(session).get());
        }catch(Exception e){
            return "BAD SESSION";
        }
        try{
            if(toggleFavourite.alreadyInDb(ufDTO)){
                deleteFavourite.delete(ufDTO);
            }else{
                saveFavourite.save(ufDTO);
            }
            return "OK";
        }catch(Exception e){
            e.printStackTrace();
            return "ERROR";
        }

    }
}
