package com.example.clientproject.web.controllers;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.stampBoards.StampBoards;
import com.example.clientproject.data.stampBoards.StampBoardsRepo;
import com.example.clientproject.data.userStampBoards.UserStampBoards;
import com.example.clientproject.data.userStampBoards.UserStampBoardsRepo;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.data.users.UsersRepo;
import com.example.clientproject.service.Utils.JWTUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class BusinessDetails {

    private ShopsRepo shopsRepo;

    private StampBoardsRepo stampRepo;

    private UsersRepo usersRepo;

    private JWTUtils jwtUtils;


    public BusinessDetails(ShopsRepo aShopRepo, StampBoardsRepo aStampBoard,
                           UsersRepo aUsersRepo,
                           JWTUtils ajwtUtils){
        shopsRepo = aShopRepo;
        stampRepo = aStampBoard;
        usersRepo = aUsersRepo;
        jwtUtils = ajwtUtils;
    }

    @GetMapping("/businessDetails")
    public String getBusiness(@RequestParam(value = "shopId") int shopId, Model model, HttpSession session){
        Optional<Users> user = jwtUtils.getLoggedInUserRow(session);
        if(user.isPresent()){
        }else{
            return "redirect:/login";
        }

        //UserStampBoards userStampBoard;
        StampBoards stampBoard;
        Shops shop;
        try {
            shop = shopsRepo.getById(Long.valueOf(shopId));
            try {
                stampBoard = stampRepo.findById(Long.valueOf(shopId)).get();
            }catch(NoSuchElementException e){
                stampBoard = stampRepo.findById(1L).get();
            }
            long userId = jwtUtils.getLoggedInUserId(session).get();
            //userStampBoard = usersRepo.getById(userId).getUserStampBoards();
        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/";
        }
        //model.addAttribute("stampBoard", stampBoard);
        model.addAttribute("loggedInUser", user.get());
        model.addAttribute("shop", shop);
        model.addAttribute("stampBoard", stampBoard);
        return "shopDetails.html";
    }
}
