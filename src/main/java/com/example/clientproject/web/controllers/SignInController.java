package com.example.clientproject.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class SignInController {

    @GetMapping("/register")
    public String registerBusiness(Model model){
        ArrayList<String> categories = new ArrayList<>(Arrays.asList("Food and drink","Animals","Alcohol"));
        model.addAttribute("categories", categories);
        return "registerbusiness.html";
    }
}
