package com.example.clientproject.web.controllers.selectCategories;

import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.tags.TagsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class selectCategoriesControllers {


    public final TagsRepo tagsRepo;

    public selectCategoriesControllers(TagsRepo tagsRepo) {
        this.tagsRepo = tagsRepo;
    }

    @PostMapping("/selectCategories")
    public String selectCategories(){

        return("selectCategories");
    }


    @GetMapping("/selectCategories")
    public String selectCategories(Model model){
        List<Tags> allTags = tagsRepo.findAll();
        model.addAttribute("allTags", allTags);
        for (Tags allTag : allTags) {
            System.out.println(allTag);
        }
        System.out.println("Test");
        System.out.println(allTags);
        return("selectCategories");
    }
}
