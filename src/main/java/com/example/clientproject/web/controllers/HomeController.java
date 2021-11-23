package com.example.clientproject.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.example.clientproject.web.controllers.SignInController.loggedIn;

@Controller
public class HomeController {
    @GetMapping({"/", "dashboard"})
    public String index(Model model){
        if (!loggedIn) {
            model.addAttribute("loggedIn", loggedIn);
            return "redirect:/login";
        }
        //System.out.println(shopsRepo.findAll());
        model.addAttribute("shops", shopsRepo.findAll());
        model.addAttribute("tags", new String[]{"Coffee", "Vegan", "Sustainable"});
        return "index";
    }
}
