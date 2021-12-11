package com.example.clientproject.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Throw500 {

    @GetMapping("/return500")
    public String throw500(){

        return "index.html";

    }
}
