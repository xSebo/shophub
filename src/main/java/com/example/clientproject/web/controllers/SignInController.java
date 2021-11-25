package com.example.clientproject.web.controllers;

import com.example.clientproject.exceptions.ForbiddenErrorException;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.service.dtos.UsersDTO;
import com.example.clientproject.service.searches.UsersSearch;
import com.example.clientproject.services.BusinessRegisterDTO;
import com.example.clientproject.services.BusinessRegisterSaver;
import com.example.clientproject.web.forms.BusinessRegisterForm;
import com.example.clientproject.web.forms.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class SignInController {
    public static boolean loggedIn = false;

    private UsersSearch usersSearch;

    private BusinessRegisterSaver saveBusiness;

    public SignInController(UsersSearch aUsersSearch, BusinessRegisterSaver sBusiness) {
        usersSearch = aUsersSearch;
        saveBusiness = sBusiness;
    }

    @PostMapping("/businessRegister")
    public String submitBusinessInfo(@Valid BusinessRegisterForm brf, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "registerbusiness.html";
        }
        saveBusiness.save(new BusinessRegisterDTO(brf));
        return "redirect:/redirect?url=businessRegister";
    }

    @GetMapping("/businessRedirect")
    public String redirectBusiness(){
        return "redirect:/businessRegister";
    }

    @GetMapping("/businessRegister")
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

    /**
     * Takes in a loginForm, and checks the data against a password in the database in order to validate a login
     * @param loginForm - the login form
     * @param bindingResult - any errors present in the login form
     * @param model - empty model object to be populated with values
     * @return - different pages depending on status of user matching
     */
    @PostMapping("login")
    public String signInChecks(@Valid LoginForm loginForm,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("loggedIn", loggedIn);
            return "account-login.html";
        }

        Optional<UsersDTO> usersDTOOptional = usersSearch.findByEmail(loginForm.getLoginEmail());
        // If the optional is present - the search found a user with that email
        if (usersDTOOptional.isPresent()) {
            // Check the password given (after encoding) and the user's DB password match
            boolean passwordMatch = BCrypt.checkpw(
                                    loginForm.getLoginPassword(),
                                    usersDTOOptional
                                            .get()
                                            .getUserPassword()
                            );

            // If they match, set the loggedIn flag to true
            if (passwordMatch) {
                loggedIn = true;
            // Otherwise, throw an exception with the correct error message
            } else {
                throw new ForbiddenErrorException("Password Incorrect");
            }
        // Else - assumes that the email is incorrect
        } else {
            throw new ForbiddenErrorException("Email Incorrect");
        }

        return "redirect:/dashboard";
    }

    /**
     *
     * @param model
     * @param session the http session of the browser accessing the site
     * @return returns a redirect to the login page after clearing the session jwt
     */
    @GetMapping("/log_out")
    public String jwtLogout(Model model, HttpSession session){
        JWTUtils.logOutUser(session);
        return "redirect:/login";
    }
}
