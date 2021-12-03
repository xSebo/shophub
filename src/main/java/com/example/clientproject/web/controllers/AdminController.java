package com.example.clientproject.web.controllers;

import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.services.UserShopLinked;
import com.example.clientproject.web.forms.userSettingsPage.NameEmailProfileChangeForm;
import com.example.clientproject.web.forms.userSettingsPage.PasswordChangeForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AdminController {
    public JWTUtils jwtUtils;
    public UserShopLinked userShopLinked;
    public UserPermissionsRepo userPermissionsRepo;

    public AdminController(JWTUtils jwt, UserShopLinked usl, UserPermissionsRepo upr){
        jwtUtils = jwt;
        userShopLinked = usl;
        userPermissionsRepo = upr;
    }

    @GetMapping("/settings")
    public String getAdminPage(Model model, HttpSession session){
        Optional<Users> user = jwtUtils.getLoggedInUserRow(session);
        if(!user.isPresent()) {
            return "redirect:/login";
        }

        PasswordChangeForm passwordChangeForm = new PasswordChangeForm();
        model.addAttribute("passwordChangeForm", passwordChangeForm);
        NameEmailProfileChangeForm nameEmailProfileChangeForm = new NameEmailProfileChangeForm();
        model.addAttribute("nameEmailProfileChangeForm", nameEmailProfileChangeForm);

        boolean userHasShop = userShopLinked.hasShop(jwtUtils.getLoggedInUserId(session).get());

        model.addAttribute("userHasShop", userHasShop);
        model.addAttribute("loggedInUser", user.get());

        return "admin";
    }
}
