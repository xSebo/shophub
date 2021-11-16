package com.example.clientproject.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

    @GetMapping("/register")
    public String registerBusiness(Model model){
        return "registerbusiness.html";
    }
}
