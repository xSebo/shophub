package com.example.clientproject.web.restControllers;

import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.socials.SocialsRepo;
import com.example.clientproject.data.stampBoards.StampBoardsRepo;
import com.example.clientproject.data.userPermissions.UserPermissions;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.services.ShopActiveService;
import com.example.clientproject.services.UserShopLinked;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ToggleShopActive {

    public JWTUtils jwtUtils;
    public UserPermissionsRepo userPermissionsRepo;
    public ShopsRepo shopsRepo;
    public ShopActiveService shopActiveService;

    public ToggleShopActive(JWTUtils jwt, UserPermissionsRepo upr, ShopsRepo sr, ShopActiveService sas){
        jwtUtils = jwt;
        userPermissionsRepo = upr;
        shopsRepo = sr;
        shopActiveService = sas;
    }


    @PostMapping("/toggleShop")
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
                if(shopActiveService.isShopActive(shopId) == 0){//if shop is currently un-active
                    shopActiveService.updateShopActive(shopId, 1, session);//enables shop
                } else if(shopActiveService.isShopActive(shopId) == 1){//if shop is currently active
                    shopActiveService.updateShopActive(shopId, 0, session);//disables shop
                } else {
                    System.out.println("an error has occured updating shop activity, shop may potentially have an active value other than 1 or 0");
                }
            }
            return "redirect:/settings";
        }
        return "redirect:/settings";
    }

}
