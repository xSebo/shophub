package com.example.clientproject.web.controllers;

import com.example.clientproject.web.forms.BusinessRegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class SignInController {

    @PostMapping("/businessRegister")
    public String submitBusinessInfo(BusinessRegisterForm brf){
        System.out.println(brf.getBusinessTags());
        return "registerbusiness.html";
    }

    @GetMapping("/businessRegister")
    public String registerBusiness(Model model){
        ArrayList<String> categories = new ArrayList<>(Arrays.asList("Food and drink","Animals","Alcohol"));
        model.addAttribute("categories", categories);
        return "registerbusiness.html";
    }
}
