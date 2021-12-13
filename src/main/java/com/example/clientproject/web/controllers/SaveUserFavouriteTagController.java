package com.example.clientproject.web.controllers;

import com.example.clientproject.data.users.Users;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.services.UserFavouriteTagSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class SaveUserFavouriteTagController {

    private UserFavouriteTagSaver FavTagService;
    private JWTUtils jwtUtils;

    @Autowired
    public SaveUserFavouriteTagController(UserFavouriteTagSaver rService, JWTUtils jwt) {
        jwtUtils = jwt;
        FavTagService = rService;
    }

    @PostMapping("/selectCategories")
    public String selectCategories(@RequestParam String listOfTagIDs, HttpSession session){
        Optional<Users> user = jwtUtils.getLoggedInUserRow(session);
        if(user.isEmpty()){
            return "redirect:/login";
        }
        //System.out.println(listOfTagIDs);
        //listOfIDs will be a string of each ID separated by "," for example: ",2,6,7,9,14"
        List<String> TagID_List = Arrays.asList(listOfTagIDs.split(",")); //splits it into string list for easier handling
        for (String TagID : TagID_List){
            //System.out.println(TagID_List.size());
            //System.out.println(TagID);
            int UserID = jwtUtils.getLoggedInUserId(session).get();
            FavTagService.saveUserFavTag(UserID,TagID, session);
        }
        return("redirect:/");
    }

}
