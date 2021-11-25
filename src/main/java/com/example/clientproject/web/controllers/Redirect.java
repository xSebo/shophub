package com.example.clientproject.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Redirect {

    @GetMapping("/redirect")
    public String redirect(@RequestParam(name="url") String url){
        try{
            return "redirect:/"+url;
        }catch(Exception e){
            return "redirect:/";
        }
    }

}
