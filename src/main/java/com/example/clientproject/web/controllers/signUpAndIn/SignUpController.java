package com.example.clientproject.web.controllers.signUpAndIn;

import com.example.clientproject.data.twoFactorMethods.TwoFactorMethods;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.data.users.UsersRepo;
import com.example.clientproject.service.LoggingService;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.service.dtos.UsersDTO;
import com.example.clientproject.service.searches.UsersSearch;
import com.example.clientproject.web.forms.signUpAndIn.SignUpForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class SignUpController {
    private UsersSearch usersSearch;
    private UsersRepo usersRepo;
    private JWTUtils jwtUtils;
    private LoggingService loggingService;

    public SignUpController(UsersSearch aUsersSearch, UsersRepo aUsersRepo, JWTUtils jwt, LoggingService aLoggingService) {
        this.usersSearch = aUsersSearch;
        this.usersRepo = aUsersRepo;
        this.jwtUtils = jwt;
        this.loggingService = aLoggingService;
    }

    @GetMapping("/signUp")
    public String signUpGet(Model model, HttpSession session, HttpServletResponse httpResponse) throws Exception {
        Optional<Users> user = jwtUtils.getLoggedInUserRow(session);
        if(user.isPresent()){
            return "redirect:/";
        }

        SignUpForm signUpForm = new SignUpForm();
        model.addAttribute("signUpForm", signUpForm);
        return "signUp.html";
    }

    @PostMapping("/signUp")
    public String signUpPost(@Valid SignUpForm signUpForm,
                             BindingResult bindingResult,
                             HttpSession httpSession,
                             Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "signUp.html";
        }

        // Check if a user already exists using the given email
        Optional<UsersDTO> usersDTOOptional = usersSearch.findByEmail(signUpForm.getNewUserEmail());
        // If one does
        if (usersDTOOptional.isPresent()) {
            // Add it to the model to alert the user
            model.addAttribute("emailInUse", true);
            // Return to the SignUp page
            return "signUp.html";
        // Otherwise
        } else {
            // Create a BCrypt object
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            // Create a new user using the provided details
            Users newUser = new Users(
                    signUpForm.getNewUserForename(),
                    signUpForm.getNewUserLastname(),
                    signUpForm.getNewUserEmail().toLowerCase(),
                    passwordEncoder.encode(signUpForm.getNewUserPassword()),
                    signUpForm.getUserProfilePicture(),
                    new TwoFactorMethods(1, "None")
                    );

            // Save the new user
            usersRepo.save(newUser);
            System.out.println(newUser.getUserEmail());

            // Get the user
            usersDTOOptional = usersSearch.findByEmail(signUpForm.getNewUserEmail().toLowerCase());
            // Create a JWTSession
            jwtUtils.makeUserJWT(
                    (int) usersDTOOptional.get().getUserId(),
                    httpSession);

            // Log the change
            loggingService.logEvent(
                    "New Shop User",
                    httpSession,
                    "New user created with Email: " + newUser.getUserEmail() +
                            " in SignUpController.signUpPost()"
            );
            // Redirect to the dashboard
            return "redirect:/dashboard";
        }
    }
}
