package com.example.clientproject.web.controllers.selectCategories;

import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.services.getAllTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.desktop.ScreenSleepEvent;
import java.util.List;

@Controller
public class selectCategoriesControllers {

    private getAllTagsService getTagService;

    @Autowired
    public void getAllTagsController(getAllTagsService allTagsService) { getTagService = allTagsService;}

    @PostMapping("/selectCategories")
    public String selectCategories(){

        return("selectCategories");
    }


    @GetMapping("/selectCategories")
    public String selectCategories(Model model){
        List<Tags> allTags = getTagService.findAll();
        model.addAttribute("allTags", allTags);
        for (Tags allTag : allTags) {
            System.out.println(allTag);
        }
        System.out.println("Test");
        return("selectCategories");
    }
}
