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
    private ShopsRepo shopsRepo;
    private UsersRepo userRepo;
    private UserPermissionsRepo userPermissionsRepo;
    private UserStampBoardsRepo userStampRepo;
    private StampBoardsRepo stampRepo;
    private RewardsRepo rewardsRepo;
    private UserFavouriteToggle toggleFavourite;

    public UserShopsController(JWTUtils jwt,
                               ShopsRepo aShopsRepo,
                               UserPermissionsRepo permRepo,
                               UserStampBoardsRepo uStampRepo,
                               StampBoardsRepo aStampRepo,
                               RewardsRepo rewardRepo,
                               UserFavouriteToggle uft,
                               UsersRepo aUserRepo){
        userRepo = aUserRepo;
        jwtUtils = jwt;
        shopsRepo = aShopsRepo;
        userPermissionsRepo = permRepo;
        userStampRepo = uStampRepo;
        stampRepo = aStampRepo;
        rewardsRepo = rewardRepo;
        toggleFavourite = uft;
    }

    @GetMapping("/userShops")
    public String getUserShops(Model model, HttpSession session) throws Exception{
        if(!jwtUtils.getLoggedInUserId(session).isPresent()){
            return "redirect:/";
        }
        List<Map<String, Object>> combinedInfo = new ArrayList<>();

        int userId = jwtUtils.getLoggedInUserId(session).get();
        Set<UserStampBoards> userStamps = userRepo.findById((long) userId).get().getUserStampBoards();

        for(UserStampBoards u:userStamps){
            combinedInfo.add(
                    new HashMap<>() {{
                        put("UserStampBoard", u);
                        put("Shop", shopsRepo.
                                findByStampboardId(u.getStampBoard().getStampBoardId()));
                    }}
            );
        }

        List<Shops> favouriteShops = new ArrayList<>();

        for(Shops s:shopsRepo.findAll()){
            UserFavouriteDTO ufDTO = new UserFavouriteDTO(new UserFavouriteForm(s.getShopId()), userId);
            if(toggleFavourite.alreadyInDb(ufDTO)){
                for(Map<String, Object> m:combinedInfo){
                    Shops shop = (Shops) m.get("Shop");
                    if(shop.getShopId() == s.getShopId()){
                        continue;
                    }
                }
                favouriteShops.add(s);

            }
        }

        model.addAttribute("loggedInUser", userRepo.getById((long)userId));
        model.addAttribute("favouriteShops", favouriteShops);
        model.addAttribute("activeShops",combinedInfo);

        return "allUserShops.html";


    }
}
