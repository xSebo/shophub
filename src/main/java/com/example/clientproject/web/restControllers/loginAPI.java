package com.example.clientproject.web.restControllers;

import com.example.clientproject.exceptions.ForbiddenErrorException;
import com.example.clientproject.service.LoggingService;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.service.dtos.UsersDTO;
import com.example.clientproject.service.searches.UsersSearch;
import com.example.clientproject.services.BusinessRegisterSaver;
import com.example.clientproject.web.forms.signUpAndIn.LoginForm;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class loginAPI {
    private UsersSearch usersSearch;
    private JWTUtils jwtUtils;
    LoggingService loggingService;

    public loginAPI(UsersSearch aUsersSearch, JWTUtils jwt, LoggingService aLoggingService) {
        usersSearch = aUsersSearch;
        jwtUtils = jwt;
        loggingService = aLoggingService;
    }

    @PostMapping("login_api")
    public String signInChecks(@Valid LoginForm loginForm,
                               BindingResult bindingResult,
                               Model model,
                               HttpSession session) {
        if (bindingResult.hasErrors()) {
            throw new ForbiddenErrorException("Details Incorrect");
        }

        Optional<UsersDTO> usersDTOOptional = usersSearch.findByEmail(loginForm.getLoginEmail().toLowerCase());
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
                jwtUtils.makeUserJWT(
                        (int) usersDTOOptional.get().getUserId(),
                        session);
                // Log the successful login
//                loggingService.logEvent(
//                        "Successful Login",
//                        session,
//                        "Successful login for User with Id: " + (int) usersDTOOptional.get().getUserId()
//                );
            } else {
                // Log the Failed login
                loggingService.logEvent(
                        "Failed Login",
                        session,
                        "Failed login for User with Id: " + usersDTOOptional.get().getUserId()
                );
                //Changed this as it is a security risk exposing which field is incorrect
                //throw new ForbiddenErrorException("Password Incorrect");
                throw new ForbiddenErrorException("Details Incorrect");

            }
            // Else - assumes that the email is incorrect
        } else {
            // Log the Failed login
            loggingService.logEvent(
                    "Failed Login",
                    session,
                    "Failed login for User with Email: " + loginForm.getLoginEmail()
            );
            //Changed this as it is a security risk exposing which field is incorrect
            //throw new ForbiddenErrorException("Email Incorrect");
            throw new ForbiddenErrorException("Details Incorrect");
        }

        return "Logged In";
    }
}
