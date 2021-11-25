package com.example.clientproject.web.controllers.SignUp;

import com.example.clientproject.data.twoFactorMethods.TwoFactorMethodsRepo;
import com.example.clientproject.data.twoFactorMethods.TwoFactorMethods;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.data.users.UsersRepo;
import com.example.clientproject.domain.AccountRegister;
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


@Controller
public class SignUpController {


    private registerUserService regUserService;

    @Autowired
    public SignUpController(registerUserService rService) {
        regUserService = rService;
    }


    @PostMapping("/signup")
    public String signUp(Model model, AccountRegister accountRegister) {
        newAccountDTO newAccountDTO1 = new newAccountDTO(accountRegister.getName(), accountRegister.getSurname(), accountRegister.getEmail(), accountRegister.getPassword());
        //System.out.println(accountRegister.getEmail());
        //System.out.println(accountRegister.getPassword());
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //TwoFactorMethods twoFactorMethods = twoFactorMethodsRepo.findByTwoFactorMethodId(1).get();
        //Users newUser = new Users(accountRegister.getName(), accountRegister.getSurname(), accountRegister.getEmail(), accountRegister.getPassword(),
                //"", "",
                //LocalDateTime.now().format(formatter), twoFactorMethods);

        //usersRepo.save(newUser);
        regUserService.save(newAccountDTO1);
        return "signup";
    }

        @GetMapping("/signup")
        public String signupGet(Model model){
            return "signup";
        }
    }