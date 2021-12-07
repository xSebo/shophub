package com.example.clientproject.web.controllers;

import com.example.clientproject.data.rewards.Rewards;
import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.socials.Socials;
import com.example.clientproject.data.socials.SocialsRepo;
import com.example.clientproject.data.stampBoards.StampBoards;
import com.example.clientproject.data.stampBoards.StampBoardsRepo;
import com.example.clientproject.data.userPermissions.UserPermissions;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.services.ShopDeleter;
import com.example.clientproject.services.ShopActiveService;
import com.example.clientproject.services.UserShopLinked;
import com.example.clientproject.web.forms.userSettingsPage.NameEmailProfileChangeForm;
import com.example.clientproject.web.forms.userSettingsPage.PasswordChangeForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    public JWTUtils jwtUtils;
    public UserShopLinked userShopLinked;
    public UserPermissionsRepo userPermissionsRepo;
    public ShopsRepo shopsRepo;
    public StampBoardsRepo stampBoardsRepo;
    public SocialsRepo socialsRepo;
    public ShopDeleter shopDeleter;
    public ShopActiveService shopActiveService;

    public AdminController(JWTUtils jwt, UserShopLinked usl, UserPermissionsRepo upr, ShopsRepo sr,          StampBoardsRepo sbr, SocialsRepo socialRepo, ShopActiveService sas){
        jwtUtils = jwt;
        userShopLinked = usl;
        userPermissionsRepo = upr;
        shopsRepo = sr;
        stampBoardsRepo = sbr;
        socialsRepo = socialRepo;
        shopDeleter = sd;
        shopActiveService = sas;
    }

    @GetMapping("/settings")
    public String getAdminPage(
            Model model,
            HttpSession session,
            @RequestParam(name="shopId", required = false) Optional<Integer> shopId
    ) {
        Optional<Users> user = jwtUtils.getLoggedInUserRow(session);
        if(!user.isPresent()) {
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

        model.addAttribute("linkedShop", false);

        //Get Shops the user is associated with
        if(highestPerm > 1 || userShopLinked.hasShop(user.get().getUserId())){
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
            List<Socials> socialList = socialsRepo.findByShopId(shop.getShopId());

            List<UserPermissions> linkedList = userPermissionsRepo.findByShopID(shop.getShopId());

            List<Users> linkedUsers = new ArrayList<>();

            //userPermissionsRepo.findAll().forEach(x -> System.out.println(x.getUser().getUserId() +":"+ x.getShop().getShopId()));


            for(UserPermissions u:linkedList){
                if(u.getUser().getUserEmail().equalsIgnoreCase(user.get().getUserEmail())){
                    continue;
                }
                linkedUsers.add(u.getUser());
            }

            long highestShopLevel = 1;

            List<UserPermissions> userShops = userPermissionsRepo.findByUserId(user.get().getUserId());
            for(UserPermissions u:userShops){
                if(u.getShop().getShopId() == shop.getShopId()){
                    highestShopLevel = u.getAdminType().getAdminTypeId();
                }
            }
            System.out.println(highestShopLevel);
            //System.out.println(userShopLinked.hasShop(user.get().getUserId()));
            model.addAttribute("linkedShop", userShopLinked.hasShop(user.get().getUserId()));
            model.addAttribute("highestShopLevel", highestShopLevel);
            model.addAttribute("staffMembers", linkedUsers);
            model.addAttribute("socials", socialList);
            model.addAttribute("shop",shop);

            //Gets shop activity and passes it into model/thymeleaf
            int intShopId = (int) shop.getShopId();
            List<Integer> shopActive = Collections.singletonList(shopActiveService.isShopActive(intShopId));
            model.addAttribute("shopActive", shopActive);



            //Get the stamp board for the chosen shop
            StampBoards stampBoard = stampBoardsRepo.findById(shop.getStampBoard().getStampBoardId()).get();
            model.addAttribute("stampBoard",stampBoard);

        }

        model.addAttribute("highestPerm", highestPerm);
        model.addAttribute("loggedInUser", user.get());
        model.addAttribute("nameEmailProfileChangeForm", new NameEmailProfileChangeForm());
        model.addAttribute("passwordChangeForm", new PasswordChangeForm());

        model.addAttribute("nameEmailProfileChangeForm", new NameEmailProfileChangeForm());
        model.addAttribute("passwordChangeForm", new PasswordChangeForm());

        return "admin";
    }

    /**
     * @param shopId - the shopID of the shop that the user is going to delete
     * @param session - HttpsSession/JWTsession
     * @return - return doesn't really matter, Javascript will redirect to "/businessRegister"
     */
    @PostMapping("/deleteShop")
    public String deleteShop(@RequestParam(name="shopId", required = true) Integer shopId, HttpSession session) {
        long shopPermissionLevel = 0;
        //checks a valid shopId has been passed through
        if (shopId > 0) {
            List<UserPermissions> userShops = userPermissionsRepo.findByUserId(jwtUtils.getLoggedInUserId(session).get());
            for (UserPermissions u : userShops) { //loops through userPermissions and saves their permission level
                if (u.getShop().getShopId() == shopId) {
                    shopPermissionLevel = u.getAdminType().getAdminTypeId();
                }
            }
            if (shopPermissionLevel == 2 || shopPermissionLevel == 3) {
                System.out.println("shop is being deleted");
                shopDeleter.deleteShop(shopId);
            }
            return "redirect:/settings";
        }
        return "redirect:/settings";
    }
}
