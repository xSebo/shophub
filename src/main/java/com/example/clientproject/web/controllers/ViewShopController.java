package com.example.clientproject.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewShopController {

    @GetMapping("/viewshop")
    public String redirectBusiness(){
        return "viewShop.html";
    }


}
