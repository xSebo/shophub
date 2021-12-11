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

    /**
     * Function for when the "Change Password" button is pressed on the "User Settings" page
     * @param nameEmailProfileChangeForm - the form filled by the users
     * @param bindingResult - any errors from the form
     * @param httpSession - the page's session
     * @param model - the model, to be filled with attributes
     * @return - the admin page, with the required model attributes
     */
    @PostMapping("/nameEmailProfilePictureChange")
    public String nameEmailProfilePictureChangePost(@Valid NameEmailProfileChangeForm nameEmailProfileChangeForm,
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
        if (bindingResult.hasErrors()) {
            // Return to the admin page
            return "admin";
        }

        // If the user is changing their First Name
        if (!nameEmailProfileChangeForm.getNewFirstName().isEmpty()) {
            // Set the user's first name to the one entered
            miscQueries.updateUser(
                    userId,
                    "User_First_Name",
                    nameEmailProfileChangeForm.getNewFirstName(),
                    httpSession
            );
        }

        // If the user is changing their Last Name
        if (!nameEmailProfileChangeForm.getNewLastName().isEmpty()) {
            // Set the user's last name to the one entered
            miscQueries.updateUser(
                    userId,
                    "User_Last_Name",
                    nameEmailProfileChangeForm.getNewLastName(),
                    httpSession
            );
        }

        // If the user is changing their Email
        if (!nameEmailProfileChangeForm.getNewEmail().isEmpty()) {
            // Set the user's email to the one entered
            miscQueries.updateUser(
                    userId,
                    "User_Email",
                    nameEmailProfileChangeForm.getNewEmail().toLowerCase(),
                    httpSession
            );
        }

        // If the user is changing their Profile Picture
        if (!nameEmailProfileChangeForm.getNewProfilePic().isEmpty()) {
            // Set the user's profile picture to the one entered
            miscQueries.updateUser(
                    userId,
                    "User_Profile_Picture",
                    nameEmailProfileChangeForm.getNewProfilePic(),
                    httpSession
            );
        }

        return "admin";
    }

    /**
     * Function for when the "Change Password" button is pressed on the "User Settings" page
     * @param passwordChangeForm - the form that was completed
     * @param bindingResult - any errors from the passwordChangeForm
     * @param httpSession - the page's session
     * @param model - the model which will have required attributes added to it
     * @return - the admin page, with required model attributes
     */
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
        )
        ) {
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
                userId,
                "User_Password",
                passwordEncoder.encode(
                        passwordChangeForm.getNewPassword()
                ),
                httpSession
        );
        // Add an attribute to the model
        model.addAttribute("passwordChangeSuccess", true);
        // Return to the settings page
        return "admin";
    }
}
