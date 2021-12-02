package com.example.clientproject.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {

    @GetMapping("/test")
    public String test(){
        return "userSocials.html";
    }
}
