package com.example.clientproject.web.controllers;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BusinessDetails {

    private ShopsRepo shopsRepo;

    public BusinessDetails(ShopsRepo aShopRepo){
        shopsRepo = aShopRepo;
    }

    @GetMapping("/businessDetails")
    public String getBusiness(@RequestParam(value = "shopId") int shopId, Model model){
        Shops shop;
        try {
            shop = shopsRepo.getById(Long.valueOf(shopId));
        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/";
        }
        model.addAttribute("shop", shop);
        return "shopDetails.html";
    }
}
