package com.example.clientproject.web.controllers;

import com.example.clientproject.data.users.Users;
import com.example.clientproject.service.Utils.JWTUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@Controller
public class TestController {

    @GetMapping("/viewshoptest")
    public String jwtTest(Model model, HttpSession session){

        return "shopDetails";
    }

}




