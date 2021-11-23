package com.example.clientproject.web.controllers;

import com.example.clientproject.services.BusinessRegisterDTO;
import com.example.clientproject.services.BusinessRegisterSaver;
import com.example.clientproject.web.forms.BusinessRegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class SignInController {

    private BusinessRegisterSaver saveBusiness;

    @Autowired
    public SignInController(BusinessRegisterSaver sBusiness){
        saveBusiness = sBusiness;
    }

    @PostMapping("/businessRegister")
    public String submitBusinessInfo(@Valid BusinessRegisterForm brf, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println("Error");
            return "registerbusiness.html";
        }

        saveBusiness.save(new BusinessRegisterDTO(brf));
        //System.out.println(brf.getBusinessTags());
        return "redirect:registerbusiness.html";
    }

    @GetMapping("/businessRegister")
    public String registerBusiness(Model model){
        ArrayList<String> categories = new ArrayList<>(Arrays.asList("Food and drink","Animals","Alcohol"));
        model.addAttribute("categories", categories);

        return "registerbusiness.html";
    }
}
