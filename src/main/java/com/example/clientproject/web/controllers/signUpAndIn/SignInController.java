package com.example.clientproject.web.controllers.signUpAndIn;

import com.example.clientproject.data.categories.Categories;
import com.example.clientproject.data.categories.CategoriesRepo;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.exceptions.ForbiddenErrorException;
import com.example.clientproject.service.LoggingService;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.service.dtos.UsersDTO;
import com.example.clientproject.service.searches.UsersSearch;
import com.example.clientproject.services.BusinessRegisterDTO;
import com.example.clientproject.services.BusinessRegisterSaver;
import com.example.clientproject.services.UserLinked;
import com.example.clientproject.services.UserShopLinked;
import com.example.clientproject.web.forms.BusinessRegisterForm;
import com.example.clientproject.web.forms.signUpAndIn.LoginForm;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
public class SignInController {
    public static boolean loggedIn = false;
    private UsersSearch usersSearch;
    private BusinessRegisterSaver saveBusiness;
    private JWTUtils jwtUtils;
    private UserLinked userLinked;
    private UserPermissionsRepo userPermissionsRepo;
    private CategoriesRepo catRepo;
    private LoggingService loggingService;

    public SignInController(UsersSearch aUsersSearch, BusinessRegisterSaver sBusiness, JWTUtils ajwtUtils,
                            UserLinked aUserShopLinked,
                            UserPermissionsRepo aUserPermissionsRepo,
                            CategoriesRepo aCatRepo,
                            LoggingService aLoggingService) {
        usersSearch = aUsersSearch;
        saveBusiness = sBusiness;
        jwtUtils = ajwtUtils;
        userLinked = aUserShopLinked;
        userPermissionsRepo = aUserPermissionsRepo;
        catRepo = aCatRepo;
        loggingService = aLoggingService;
    }

    @PostMapping("/businessRegister")
    public String submitBusinessInfo(@Valid BusinessRegisterForm brf, BindingResult bindingResult, HttpSession session){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "registerbusiness.html";
        }
        saveBusiness.save(new BusinessRegisterDTO(brf), jwtUtils.getLoggedInUserId(session).get(), session);
        return "redirect:/redirect?url=businessRegister";
    }

    @GetMapping("/businessRegister")
    public String registerBusiness(Model model, HttpSession session){
        Optional<Users> user = jwtUtils.getLoggedInUserRow(session);
        if(user.isPresent()){
        }else{
            return "redirect:/login";
        }

        //System.out.println(userShopLinked.hasShop(jwtUtils.getLoggedInUserId(session).get()));
        if(userLinked.isAnyAdmin(jwtUtils.getLoggedInUserId(session).get())){

            int shopId = userLinked.userAdminShopId(jwtUtils.getLoggedInUserId(session).get());

            return "redirect:/redirect?url=businessDetails?shopId="+shopId;
        }
        List<Categories> categories = catRepo.findAll();
        model.addAttribute("loggedInUser", user.get());
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
    public String loginPageAccess(Model model, HttpSession session) {
        Optional<Users> user = jwtUtils.getLoggedInUserRow(session);
        if(user.isPresent()){
            return "redirect:/";
        }

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
            Model model,
            HttpSession session) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("loggedIn", loggedIn);
            return "account-login.html";
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
                loggedIn = true;
                // Log the successful login
                loggingService.logEvent(
                        "Successful Login",
                        session,
                        "Successful login with User Id: " + usersDTOOptional.get().getUserId()
                );
            // Otherwise, throw an exception with the correct error message
            } else {
                // Log the failed login
                loggingService.logEvent(
                        "Failed Login",
                        session,
                        "Failed login with User Email: " + usersDTOOptional.get().getUserEmail() +
                                " due to incorrect password"
                );
                //Changed this as it is a security risk exposing which field is incorrect
                //throw new ForbiddenErrorException("Password Incorrect");
                throw new ForbiddenErrorException("Details Incorrect");
            }
        // Else - assumes that the email is incorrect
        } else {
            // Log the successful login
            loggingService.logEvent(
                    "Failed Login",
                    session,
                    "Failed login with Email: " + loginForm.getLoginEmail() +
                            " due to incorrect email"
            );
            //Changed this as it is a security risk exposing which field is incorrect
            //throw new ForbiddenErrorException("Email Incorrect");
            throw new ForbiddenErrorException("Details Incorrect");
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
        jwtUtils.logOutUser(session);
        return "redirect:/login";
    }
}
