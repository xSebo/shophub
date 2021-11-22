package com.example.clientproject.web.controllers;

import com.example.clientproject.service.dtos.UsersDTO;
import com.example.clientproject.service.searches.UsersSearch;
import com.example.clientproject.web.forms.LoginForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.*;

@Controller
public class SignInController {
    public static boolean loggedIn = false;

    private UsersSearch usersSearch;

    public SignInController(UsersSearch aUsersSearch) {
        usersSearch = aUsersSearch;
    }

    @GetMapping("/register")
    public String registerBusiness(Model model){
        ArrayList<String> categories = new ArrayList<>(Arrays.asList("Food and drink","Animals","Alcohol"));
        model.addAttribute("categories", categories);
        model.addAttribute("loggedIn", loggedIn);
        return "registerbusiness.html";
    }

    /**
     * Method for accessing the login page
     * An attribute is assigned to the model called "loggedIn", which is false by default as all members accessing this page will not be logged in by this point
     * @param model - Model object which will be populated and passed into the view
     * @return - the page to redirect to
     */
    @GetMapping("/login")
    public String loginPageAccess(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        model.addAttribute("loggedIn", loggedIn);
        return "account-login.html";
    }

    @PostMapping("/login")
    public String signInChecks(@Valid LoginForm loginForm,
            BindingResult bindingResult,
            Model model) {

        System.out.println("Hello World");

        if (bindingResult.hasErrors()) {
            model.addAttribute("loggedIn", loggedIn);
            return "account-login.html";
        }

        Optional<UsersDTO> usersDTOOptional = usersSearch.findByEmail(loginForm.getLoginEmail());
        // If the optional is present - the search found a user with that email
        if (usersDTOOptional.isPresent()) {
            Random random = new SecureRandom();
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            // Check the password given (after encoding) and the user's DB password match
            boolean passwordMatch = usersDTOOptional
                    .get()
                    .getUserPassword()
                    .equals(
                            passwordEncoder.encode(
                                    loginForm
                                            // Commented for testing purposes
                                            //.getLoginPassword() + generatedSalt
                                            // TODO - replace with getter for this user's random salt instead of "EXAMPLESALT"
                                            .getLoginPassword() + "EXAMPLESALT"
                            )
                    );

            // If they match, set the loggedIn flag to true
            if (passwordMatch) {
                loggedIn = true;
            // Otherwise, redirect back to the login page with an appropriate error
            } else {
                model.addAttribute("incorrectPassword", true);
                model.addAttribute("loggedIn", loggedIn);
                return "account-login.html";
            }
        // Else - assumes that the email is incorrect
        } else {
            model.addAttribute("incorrectEmail", true);
            model.addAttribute("loggedIn", loggedIn);
            return "account-login.html";
        }

        model.addAttribute("loggedIn", loggedIn);
        return "index.html";
    }
}
