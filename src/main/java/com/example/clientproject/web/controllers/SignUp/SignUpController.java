package com.example.clientproject.web.controllers.SignUp;

import com.example.clientproject.data.twoFactorMethods.TwoFactorMethodsRepo;
import com.example.clientproject.data.twoFactorMethods.TwoFactorMethods;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.data.users.UsersRepo;
import com.example.clientproject.domain.AccountRegister;
import com.example.clientproject.services.findUserByEmailService;
import com.example.clientproject.services.newAccountDTO;
import com.example.clientproject.services.registerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Controller
public class SignUpController {


    private registerUserService regUserService;


    @Autowired
    public SignUpController(registerUserService rService) {
        regUserService = rService;
    }

    @Autowired
    private findUserByEmailService findUserByEmail;

    @PostMapping("/signup")
    public String signUp(Model model, AccountRegister accountRegister) {
        newAccountDTO newAccountDTO1 = new newAccountDTO(accountRegister.getName(), accountRegister.getSurname(), accountRegister.getEmail(), accountRegister.getPassword());

        //get all emails

        Optional<Users> User =  findUserByEmail.findByUserEmail(accountRegister.getEmail());

        if (User.isPresent()) { //if email is already taken it will not save user to DB and will return error msg
            model.addAttribute("Email is already taken");
            System.out.println("Email exists in database");
            return "signup";
        }

        //compare email provided


        regUserService.save(newAccountDTO1);
        return "signup";
    }

        @RequestMapping("/signup")
        public String signupGet(Model model){return "signup";}
    }