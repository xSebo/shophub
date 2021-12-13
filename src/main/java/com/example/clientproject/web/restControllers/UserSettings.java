package com.example.clientproject.web.restControllers;

import com.example.clientproject.data.misc.MiscQueries;
import com.example.clientproject.exceptions.ForbiddenErrorException;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.service.searches.UsersSearch;
import com.example.clientproject.web.forms.userSettingsPage.NameEmailProfileChangeForm;
import com.example.clientproject.web.forms.userSettingsPage.PasswordChangeForm;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class UserSettings {
    private MiscQueries miscQueries;
    private UsersSearch usersSearch;
    private JWTUtils jwtUtils;

    public UserSettings(MiscQueries aMiscQueries, UsersSearch aUsersSearch, JWTUtils aJWTUtils) {
        this.miscQueries = aMiscQueries;
        this.usersSearch = aUsersSearch;
        this.jwtUtils = aJWTUtils;
    }

    /**
     * Function for when the "Change Password" button is pressed on the "User Settings" page
     * @param nameEmailProfileChangeForm - the form filled by the users
     * @param httpSession - the page's session
     * @return - the admin page, with the required model attributes
     */
    @PostMapping("/nameEmailProfilePicture/update")
    public String nameEmailProfilePictureChangePost(NameEmailProfileChangeForm nameEmailProfileChangeForm,
                                                    HttpSession httpSession) {
        // Get the userId for the currently logged-in user
        int userId = jwtUtils.getLoggedInUserId(httpSession).get();

        // If the user is changing their First Name
        if (!(nameEmailProfileChangeForm.getNewFirstName() == null)) {
            System.out.println("Changing First Name");
            // Set the user's first name to the one entered
            miscQueries.updateUser(
                    userId,
                    "User_First_Name",
                    nameEmailProfileChangeForm.getNewFirstName(),
                    httpSession
            );
        }

        // If the user is changing their Last Name
        if (!(nameEmailProfileChangeForm.getNewLastName() == null)) {
            System.out.println("Changing Last Name");
            // Set the user's last name to the one entered
            miscQueries.updateUser(
                    userId,
                    "User_Last_Name",
                    nameEmailProfileChangeForm.getNewLastName(),
                    httpSession
            );
        }

        // If the user is changing their Email
        if (!(nameEmailProfileChangeForm.getNewEmail() == null)) {
            System.out.println("Changing Email");
            // Set the user's email to the one entered
            miscQueries.updateUser(
                    userId,
                    "User_Email",
                    nameEmailProfileChangeForm.getNewEmail().toLowerCase(),
                    httpSession
            );
        }

        // If the user is changing their Profile Picture
        if (!(nameEmailProfileChangeForm.getNewProfilePic() == null)) {
            System.out.println("Changing Profile Pic");
            // Set the user's profile picture to the one entered
            miscQueries.updateUser(
                    userId,
                    "User_Profile_Picture",
                    nameEmailProfileChangeForm.getNewProfilePic(),
                    httpSession
            );
        }

        return "success";
    }

    /**
     * Function for when the "Change Password" button is pressed on the "User Settings" page
     * @param passwordChangeForm - the form that was completed
     * @param httpSession - the page's session
     * @return - appropriate error message
     */
    @PostMapping("/password/update")
    public String passwordChangePostAPI(PasswordChangeForm passwordChangeForm,
                                     HttpSession httpSession) throws ForbiddenErrorException{
        // Get the userId for the currently logged-in user
        int userId = jwtUtils.getLoggedInUserId(httpSession).get();

        // If the new password fields do not match
        if (!passwordChangeForm.getNewPassword().equals(
                passwordChangeForm.getNewPasswordConfirm()
        )) {
            throw new ForbiddenErrorException("Passwords entered do not match");
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
            // Return an error
            throw new ForbiddenErrorException("Old password doesn't match");
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

        // Return a success message to the settings page
        return "success";
    }
}
