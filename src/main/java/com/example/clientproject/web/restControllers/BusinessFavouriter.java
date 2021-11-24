package com.example.clientproject.web.restControllers;

import com.example.clientproject.service.searches.UsersSearch;
import com.example.clientproject.services.*;
import com.example.clientproject.web.forms.UserFavouriteForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/favouriteBusiness")
    public String favouriteBusiness(UserFavouriteForm uff){
        UserFavouriteDTO ufDTO = new UserFavouriteDTO(uff);
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
