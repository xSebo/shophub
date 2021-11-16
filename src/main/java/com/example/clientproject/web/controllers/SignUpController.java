package com.example.clientproject.web.controllers;

import com.example.clientproject.domain.AccountRegister;
import com.example.clientproject.services.newAccountDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignUpController {

    @PostMapping("/signup")
    public String signUp(Model model, AccountRegister accountRegister) {
        newAccountDTO newAccountDTO1 = new newAccountDTO(accountRegister.getName(), accountRegister.getSurname(), accountRegister.getEmail(), accountRegister.getPassword());
        //System.out.println(accountRegister.getEmail());
        return "signUp";
    }

    @GetMapping("/signup")
    public String signupGet(Model model){
        return "signup";
    }
}
