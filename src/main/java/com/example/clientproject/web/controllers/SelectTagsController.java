package com.example.clientproject.web.controllers;

import com.example.clientproject.data.tags.TagsRepo;
import com.example.clientproject.service.dtos.TagsDTO;
import com.example.clientproject.service.searches.TagSearch;
import com.example.clientproject.web.forms.signUpAndIn.SignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.text.html.Option;
import java.util.List;

@Controller
public class SelectTagsController {
    private TagSearch tagsSearch;
    private TagsRepo tagsRepo;

    public SelectTagsController(TagSearch aTagsSearch, TagsRepo aTagsRepo) {
        this.tagsSearch = aTagsSearch;
        this.tagsRepo = aTagsRepo;
    }

    @GetMapping("/selectTags")
    public String tagsGet(Model model) {
        List<TagsDTO> Tags = tagsSearch.findAll();
        //System.out.println(Tags);
        return "selectCategories.html";
    }

}
