package com.example.clientproject.web.controllers;

import com.example.clientproject.data.rewards.Rewards;
import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.stampBoards.StampBoards;
import com.example.clientproject.data.stampBoards.StampBoardsRepo;
import com.example.clientproject.data.userPermissions.UserPermissions;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.services.UserShopLinked;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    public JWTUtils jwtUtils;
    public UserShopLinked userShopLinked;
    public UserPermissionsRepo userPermissionsRepo;
    public ShopsRepo shopsRepo;
    public StampBoardsRepo stampBoardsRepo;

    public AdminController(JWTUtils jwt, UserShopLinked usl, UserPermissionsRepo upr, ShopsRepo sr, StampBoardsRepo sbr){
        jwtUtils = jwt;
        userShopLinked = usl;
        userPermissionsRepo = upr;
        shopsRepo = sr;
        stampBoardsRepo = sbr;
    }

    @GetMapping("/settings")
    public String getAdminPage(Model model, HttpSession session, @RequestParam(name="shopId", required = false) Optional<Integer> shopId){
        Optional<Users> user = jwtUtils.getLoggedInUserRow(session);
        if(user.isPresent()){
        }else{
            return "redirect:/login";
        }

        //Get the highest permission level of user
        Long highestPerm = 1L;
        List<UserPermissions> perms =  userPermissionsRepo.findByUserId(user.get().getUserId());
        for (UserPermissions perm : perms)
        {
            Long newPerm = perm.getAdminType().getAdminTypeId();
            if(newPerm>highestPerm){
                highestPerm=newPerm;
            }
        }

        //Get Shops the user is associated with
        if(highestPerm > 1){
            List<Integer> shops = userShopLinked.getByUserId(user.get().getUserId());
            //Check if user has defined a shop to look at in the url
            Shops shop;
            if(shopId.isPresent()){
                if(shops.contains(shopId.get())){
                    shop = shopsRepo.getById(Long.valueOf(shopId.get()));
                }else{
                    //Else choose the first one
                    shop = shopsRepo.getById(Long.valueOf(shops.get(0)));
                }
            }else{
                //Else choose the first one
                shop = shopsRepo.getById(Long.valueOf(shops.get(0)));
            }
            model.addAttribute("shop",shop);

            //Get the stamp board for the chosen shop
            StampBoards stampBoard = stampBoardsRepo.findById(shop.getStampBoard().getStampBoardId()).get();
            model.addAttribute("stampBoard",stampBoard);

        }

        model.addAttribute("highestPerm", highestPerm);
        model.addAttribute("loggedInUser", user.get());

        return "admin";
    }
}
