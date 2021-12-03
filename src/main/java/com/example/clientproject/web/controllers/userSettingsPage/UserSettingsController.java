package com.example.clientproject.web.controllers.userSettingsPage;

import com.example.clientproject.data.misc.MiscQueries;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.service.searches.UsersSearch;
import com.example.clientproject.web.forms.userSettingsPage.NameEmailProfileChangeForm;
import com.example.clientproject.web.forms.userSettingsPage.PasswordChangeForm;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserSettingsController {
    private MiscQueries miscQueries;
    private UsersSearch usersSearch;
    private JWTUtils jwtUtils;

    public UserSettingsController(MiscQueries aMiscQueries, UsersSearch aUsersSearch, JWTUtils aJWTUtils) {
        this.miscQueries = aMiscQueries;
        this.usersSearch = aUsersSearch;
        this.jwtUtils = aJWTUtils;
    }

//    @PostMapping("/nameEmailProfilePictureChange")
//    public String nameEmailProfilePictureChangePost(@Valid NameEmailProfileChangeForm nameEmailProfileChangeForm,
//                                                    BindingResult bindingResult,
//                                                    HttpSession httpSession,
//                                                    Model model) {
//
//    }

    @PostMapping("/passwordChange")
    public String passwordChangePost(@Valid PasswordChangeForm passwordChangeForm,
                                     BindingResult bindingResult,
                                     HttpSession httpSession,
                                     Model model) {
        // Get the userId for the currently logged-in user
        int userId = jwtUtils.getLoggedInUserId(httpSession).get();
        // Add the user to the model
        model.addAttribute(
                "loggedInUser",
                usersSearch
                        .findById(userId)
                        .get()
        );

        // If the binding result has errors or if the new password fields do not match
        if (bindingResult.hasErrors() || (
                !passwordChangeForm.getNewPassword().equals(
                        passwordChangeForm.getNewPasswordConfirm()
                )
        )) {
            // If the problem is that the passwords do not match
            if (passwordChangeForm.getNewPassword().equals(
                    passwordChangeForm.getNewPasswordConfirm()
            )) {
                // Add an attribute to the model
                model.addAttribute("passwordsDoNotMatch", true);
            }
            // Return to the settings page
            return "admin";
        }

        // Check that the oldPassword field (after encryption) matches the encrypted
        // password from the database
        boolean oldPasswordMatches = BCrypt.checkpw(
                passwordChangeForm.getOldPassword(),
                usersSearch
                        .findById(userId)
                        .get()
                        .getUserPassword()
        );
        if (!oldPasswordMatches) {
            model.addAttribute("oldPasswordDoesNotMatch", true);
            return "admin";
        }

        // Setup a password encoder
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Update the password, now that all validations have been completed
        miscQueries.updateUser(
                usersSearch.findById(userId).get(),
                "User_Password",
                passwordEncoder.encode(
                        passwordChangeForm.getNewPassword()
                )
        );
        // Add an attribute to the model
        model.addAttribute("passwordChangeSuccess", true);
        // Return to the settings page
        return "admin";
    }
}
