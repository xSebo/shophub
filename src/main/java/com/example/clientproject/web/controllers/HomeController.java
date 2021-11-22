package com.example.clientproject.web.controllers;

import com.example.clientproject.data.shops.ShopsRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    public final ShopsRepo shopsRepo;

    public HomeController(ShopsRepo shopsRepo){
        this.shopsRepo = shopsRepo;
    }

    @GetMapping("/")
    public String index(Model model){
        System.out.println(shopsRepo.findAll());
        model.addAttribute("shops", shopsRepo.findAll());
        model.addAttribute("tags", new String[]{"Coffee", "Vegan", "Sustainable"});

        return "index";
    }
}
