package com.example.clientproject.web.controllers.selectCategories;

import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.tags.TagsRepo;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.services.findUserByEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.clientproject.services.UserFavTagSaver;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class selectCategoriesControllers {


    public final TagsRepo tagsRepo;

    private UserFavTagSaver FavTagService;

    @Autowired
    public selectCategoriesControllers(TagsRepo tagsRepo, UserFavTagSaver rService) {
        this.tagsRepo = tagsRepo;
        FavTagService = rService;
    }

    @Autowired
    private findUserByEmailService findUserByEmail;


    /**
     * @param listOfTagIDs is a string that contains all the tags the user has selected
     * @return should redirect the user to their dashboard
     */
    @PostMapping("/selectCategories")
    public String selectCategories(@RequestParam String listOfTagIDs, HttpSession session){

        //code to check if user has session || if not redirect to signup
        Optional<Integer> user = JWTUtils.getLoggedInUserId(session);
        if(user.isPresent()){System.out.println(user.get());
        }else{ return "redirect:/login";}

        //listOfIDs will be a string of each ID separated by "," for example: ",2,6,7,9,14"
        List<String> TagID_List = Arrays.asList(listOfTagIDs.split(",")); //splits it into string list for easier handling


        Integer UserID = JWTUtils.getLoggedInUserId(session).get();


        for (String TagID : TagID_List){
            System.out.println(TagID_List.size());
            System.out.println(TagID);
            FavTagService.saveUserFavTag(UserID,TagID);
        }
        return("index");
    }


    /**
     * @return will return a list of all the tags for thymeleaf templating
     */
    @GetMapping("/selectCategories")
    public String selectCategories(Model model, HttpSession session){

        //code to check if user has session || if not redirect to signup
        Optional<Integer> user = JWTUtils.getLoggedInUserId(session);
        if(user.isPresent()){System.out.println(user.get());
        }else{ return "redirect:/login";}

        List<Tags> allTags = tagsRepo.findAll();
        model.addAttribute("allTagsAttributeName", allTags);
        System.out.println(allTags);
        return("selectCategories");
    }
}
