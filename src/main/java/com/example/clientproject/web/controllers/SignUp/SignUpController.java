package com.example.clientproject.web.controllers.SignUp;

import com.example.clientproject.data.users.Users;
import com.example.clientproject.domain.AccountRegister;
import com.example.clientproject.services.findUserByEmailService;
import com.example.clientproject.services.newAccountDTO;
import com.example.clientproject.services.registerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class SignUpController {

    private registerUserService regUserService;

    //creates an object/list to pass into Model for Thymeleaf templating
    List<String> EmailTakenContainer = new ArrayList<>();


    @Autowired
    public SignUpController(registerUserService rService) {
        regUserService = rService;
    }

    @Autowired
    private findUserByEmailService findUserByEmail;


    /**
     * @param accountRegister this is an object containing all of the data from the register form
     * @return should redirect the user to the select categories. OR if the user enters an email that already exists in database sends back an error msg
     */
    @PostMapping("/signup")
    public String signUp(Model model, AccountRegister accountRegister) {
        //populates the DTO with the data from the form
        newAccountDTO newAccountDTO1 = new newAccountDTO(accountRegister.getName(), accountRegister.getSurname(), accountRegister.getEmail(), accountRegister.getPassword());
        //Resets the object
        EmailTakenContainer.clear();

        //get all emails
        Optional<Users> User =  findUserByEmail.findByUserEmail(accountRegister.getEmail());

        if (User.isPresent()) { //if email is already taken it will not save user to DB and will return error msg
            EmailTakenContainer.add("yes");
            model.addAttribute("EmailTaken", EmailTakenContainer);
            System.out.println("Email exists in database");
            return "signup";
        }

        //compare email provided


        regUserService.save(newAccountDTO1);
        return "redirect:/selectCategories";
    }

        @RequestMapping("/signup")
        public String signupGet(Model model){return "signup";}
    }