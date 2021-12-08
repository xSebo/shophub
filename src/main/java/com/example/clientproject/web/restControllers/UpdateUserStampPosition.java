package com.example.clientproject.web.restControllers;

import com.example.clientproject.data.rewards.Rewards;
import com.example.clientproject.data.rewards.RewardsRepo;
import com.example.clientproject.data.stampBoards.StampBoards;
import com.example.clientproject.data.userPermissions.UserPermissions;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import com.example.clientproject.data.userStampBoards.UserStampBoards;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.data.users.UsersRepo;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.services.StampboardUpdater;
import com.example.clientproject.services.UserStampBoardService;
import com.example.clientproject.web.forms.UpdateStampboardForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
public class UpdateUserStampPosition {
    JWTUtils jwtUtils;
    UserStampBoardService userStampBoardService;
    UserPermissionsRepo userPermissionsRepo;
    RewardsRepo rewardsRepo;
    UsersRepo usersRepo;

    public UpdateUserStampPosition(JWTUtils jwt, UserStampBoardService usbs,
                                   UserPermissionsRepo upr, RewardsRepo rr,
                                   UsersRepo ur){ //need to add service for changing stamp pos
        jwtUtils = jwt;
        userStampBoardService = usbs;
        userPermissionsRepo = upr;
        rewardsRepo = rr;
        usersRepo = ur;
    }

    @PostMapping("/changeUserPos")
    public void updateUserPos(@RequestParam(name="direction", required = true) String direction,
                              @RequestParam(name="shopId", required = true) String shopId, HttpSession session) {
        //will firstly check that user has permission to do action
        //Optional<Users> user = jwtUtils.getLoggedInUserRow(session);
        long shopPermissionLevel = 0;
        int currentUserStampPos = 0;
        long shopStampBoardId = 0;
        int shopStampBoardSize = 0;
        int shopIdConverted = Integer.valueOf(shopId);
        List<UserPermissions> userShops = userPermissionsRepo.findByUserId(jwtUtils.getLoggedInUserId(session).get());
        for (UserPermissions u : userShops) { //loops through userPermissions and saves it to variable to be checked
            if (u.getShop().getShopId() == shopIdConverted) {
                shopPermissionLevel = u.getAdminType().getAdminTypeId();
                shopStampBoardId = u.getShop().getStampBoard().getStampBoardId();
                shopStampBoardSize = u.getShop().getStampBoard().getStampBoardSize();
            }
        }
        System.out.println(shopPermissionLevel);
        System.out.println(shopStampBoardId);
        System.out.println(shopStampBoardSize);
        if(shopPermissionLevel > 1){//user has the correct level to add/subtract their own stampBoard place
            currentUserStampPos = userStampBoardService.getUserStampPos(jwtUtils.getLoggedInUserId(session).get(), (int) shopStampBoardId );
            if(Objects.equals(direction, "subtract")){
                if(currentUserStampPos != 0){
                    userStampBoardService.changeUserStampPosition(jwtUtils.getLoggedInUserId(session).get(), -1, currentUserStampPos);
                }
            } else if(Objects.equals(direction, "add")){
                if(currentUserStampPos != shopStampBoardSize){
                    userStampBoardService.changeUserStampPosition(jwtUtils.getLoggedInUserId(session).get(), 1, currentUserStampPos);
                }
            }
        }
    }

    @PostMapping
    public void reedeemStamps(@RequestParam(name="rewardId", required = true) int rewardId, HttpSession session){
        Optional<Rewards> reward = rewardsRepo.findByRewardId(rewardId);
        Optional<Integer> stampBoardId = rewardsRepo.getStampBoardIdById(rewardId);
        Optional<Users> user = usersRepo.findById(Long.valueOf(jwtUtils.getLoggedInUserId(session).get()));
        Set<UserStampBoards> userStampBoards = user.get().getUserStampBoards();
        int userStampPos = 0;
        long userStampBoardIdInLink = 0;

        boolean userIsLinkedToStampBoard = false;
        for(UserStampBoards u : userStampBoards){
            if(stampBoardId.get() == u.getStampBoard().getStampBoardId()){
                userStampBoardIdInLink = u.getStampBoard().getStampBoardId();
                userStampPos = u.getUserStampPosition();
                userIsLinkedToStampBoard = true;
            }
        }
        if(userIsLinkedToStampBoard){
            if(userStampPos >= reward.get().getRewardStampLocation()){
                userStampBoardService.changeUserStampPosition(jwtUtils.getLoggedInUserId(session).get(), -1, userStampPos);
            }
        } else {
            System.out.println("User is not linked to stampboard you are trying to claim a reward from");
        }
    }
}
