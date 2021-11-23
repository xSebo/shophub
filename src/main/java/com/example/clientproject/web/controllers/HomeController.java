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

        return "index";
    }
}
