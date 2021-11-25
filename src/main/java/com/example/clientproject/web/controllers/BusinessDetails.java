package com.example.clientproject.web.controllers;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.stampBoards.StampBoards;
import com.example.clientproject.data.stampBoards.StampBoardsRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BusinessDetails {

    private ShopsRepo shopsRepo;

    private StampBoardsRepo stampRepo;

    public BusinessDetails(ShopsRepo aShopRepo, StampBoardsRepo aStampBoard){
        shopsRepo = aShopRepo;
        stampRepo = aStampBoard;
    }

    @GetMapping("/businessDetails")
    public String getBusiness(@RequestParam(value = "shopId") int shopId, Model model){
        StampBoards stampBoard;
        Shops shop;
        try {
            shop = shopsRepo.getById(Long.valueOf(shopId));
            //stampBoard = stampRepo.findById(Long.valueOf(shopId)).get();

        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/";
        }
        //model.addAttribute("stampBoard", stampBoard);
        model.addAttribute("shop", shop);
        return "shopDetails.html";
    }
}
