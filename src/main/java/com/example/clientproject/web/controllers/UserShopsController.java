package com.example.clientproject.web.controllers;

import com.example.clientproject.data.rewards.RewardsRepo;
import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.stampBoards.StampBoardsRepo;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import com.example.clientproject.data.userStampBoards.UserStampBoards;
import com.example.clientproject.data.userStampBoards.UserStampBoardsRepo;
import com.example.clientproject.data.users.UsersRepo;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.services.DashboardStampLoader;
import com.example.clientproject.services.UserFavouriteDTO;
import com.example.clientproject.services.UserFavouriteToggle;
import com.example.clientproject.web.forms.UserFavouriteForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class UserShopsController {

    private JWTUtils jwtUtils;
    private UsersRepo userRepo;
    private DashboardStampLoader stampLoader;


    public UserShopsController(JWTUtils jwt,
                               UsersRepo aUserRepo,
                               DashboardStampLoader aStampLoader){
        stampLoader = aStampLoader;
        userRepo = aUserRepo;
        jwtUtils = jwt;
    }

    @GetMapping("/userShops")
    public String getUserShops(Model model, HttpSession session) throws Exception{
        if(!jwtUtils.getLoggedInUserId(session).isPresent()){
            return "redirect:/";
        }

        int userId = jwtUtils.getLoggedInUserId(session).get();

        model.addAttribute("loggedInUser", userRepo.getById((long)userId));
        model.addAttribute("favouriteShops", stampLoader.getData(userId).get("favourited"));
        model.addAttribute("activeShops",stampLoader.getData(userId).get("purchased"));

        return "allUserShops.html";


    }
}
