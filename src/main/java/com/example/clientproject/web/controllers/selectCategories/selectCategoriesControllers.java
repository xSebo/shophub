package com.example.clientproject.web.controllers.selectCategories;

import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.tags.TagsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class selectCategoriesControllers {


    public final TagsRepo tagsRepo;

    public selectCategoriesControllers(TagsRepo tagsRepo) {
        this.tagsRepo = tagsRepo;
    }

    /**
     * @param listOfTagIDs is a string that contains all the tags the user has selected
     * @return should redirect the user to their dashboard
     */
    @PostMapping("/selectCategories")
    public String selectCategories(@RequestParam String listOfTagIDs){
        System.out.println(listOfTagIDs);
        //listOfIDs will be a string of each ID separated by "," for example: ",2,6,7,9,14"
        List<String> TagID_List = Arrays.asList(listOfTagIDs.split(",")); //splits it into string list for easier handling
        return("index");
    }


    /**
     * @return will return a list of all the tags for thymeleaf templating
     */
    @GetMapping("/selectCategories")
    public String selectCategories(Model model){
        List<Tags> allTags = tagsRepo.findAll();
        model.addAttribute("allTagsAttributeName", allTags);
        System.out.println(allTags);
        return("selectCategories");
    }
}
