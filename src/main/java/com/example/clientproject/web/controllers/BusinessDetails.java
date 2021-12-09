package com.example.clientproject.web.controllers;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.socials.Socials;
import com.example.clientproject.data.socials.SocialsRepo;
import com.example.clientproject.data.stampBoards.StampBoards;
import com.example.clientproject.data.stampBoards.StampBoardsRepo;
import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.userPermissions.UserPermissions;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import com.example.clientproject.data.userStampBoards.UserStampBoards;
import com.example.clientproject.data.userStampBoards.UserStampBoardsRepo;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.data.users.UsersRepo;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.services.UserFavouriteTagSaver;
import com.example.clientproject.services.UserStampBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class BusinessDetails {

    private ShopsRepo shopsRepo;

    private StampBoardsRepo stampRepo;

    private UsersRepo usersRepo;

    private JWTUtils jwtUtils;

    private UserStampBoardService userStampService;

    private SocialsRepo socialsRepo;

    UserPermissionsRepo userPermissionsRepo;


    public BusinessDetails(ShopsRepo aShopRepo, StampBoardsRepo aStampBoard,
                           UsersRepo aUsersRepo, UserStampBoardService aUserStampService,
                           JWTUtils ajwtUtils, SocialsRepo aSocialsRepo,
                           UserPermissionsRepo upr){
        shopsRepo = aShopRepo;
        stampRepo = aStampBoard;
        usersRepo = aUsersRepo;
        jwtUtils = ajwtUtils;
        userStampService = aUserStampService;
        socialsRepo = aSocialsRepo;
        userPermissionsRepo = upr;
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
                stampBoard = shop.getStampBoard();
            }catch(NoSuchElementException e) {
                stampBoard = stampRepo.findById(1L).get();
            }catch(javax.persistence.EntityNotFoundException e){
                stampBoard = stampRepo.findById(1L).get();
            }
            //userStampBoard = usersRepo.getById(userId).getUserStampBoards();
        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/";
        }

        List<Socials> socialMedia = socialsRepo.findByShopId(shop.getShopId());

        //gets users permission level for shop
        long shopPermissionLevel = 0;
        List<UserPermissions> userShops = userPermissionsRepo.findByUserId(jwtUtils.getLoggedInUserId(session).get());
        //loops through userPermissions and saves it to variable to be passed into model
        for (UserPermissions u : userShops) {
            if (u.getShop().getShopId() == shop.getShopId()) {
                shopPermissionLevel = u.getAdminType().getAdminTypeId();
            }
        }
        //creates an object to pass into model
        ArrayList <Integer> userShopPermissionOBJ = new ArrayList<>();
        userShopPermissionOBJ.add((int) shopPermissionLevel);
        model.addAttribute("userPermission", userShopPermissionOBJ);


        model.addAttribute("socials", socialMedia);

        int userId = jwtUtils.getLoggedInUserId(session).get();
        int UserStampPos = userStampService.getUserStampPos(userId, (int) shop.getStampBoard().getStampBoardId());

        ArrayList <Integer> UserStampPosOBJ = new ArrayList<>();
        UserStampPosOBJ.add(UserStampPos);
        model.addAttribute("UserStampPos", UserStampPosOBJ);

        String tags = "Tags: ";
        for(int i=0; i<shop.getShopTags().size(); i++){
            if(i != shop.getShopTags().size()-1){
                tags+=shop.getShopTags().get(i).getTagName() + ", ";
            }else{
                tags += shop.getShopTags().get(i).getTagName();
            }
        }

        //model.addAttribute("stampBoard", stampBoard);
        model.addAttribute("loggedInUser", user.get());
        model.addAttribute("tags", tags);
        model.addAttribute("shop", shop);
        model.addAttribute("stampBoard", stampBoard);
        return "shopDetails.html";
    }
}
