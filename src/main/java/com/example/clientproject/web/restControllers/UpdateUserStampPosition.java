package com.example.clientproject.web.restControllers;

import com.example.clientproject.data.rewards.Rewards;
import com.example.clientproject.data.rewards.RewardsRepo;
import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.stampBoards.StampBoards;
import com.example.clientproject.data.userPermissions.UserPermissions;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import com.example.clientproject.data.userStampBoards.UserStampBoards;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.data.users.UsersRepo;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.services.GetStampBoardIdFromRewardId;
import com.example.clientproject.services.StampboardUpdater;
import com.example.clientproject.services.UserStampBoardService;
import com.example.clientproject.web.forms.UpdateStampboardForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.*;

@RestController
public class UpdateUserStampPosition {
    JWTUtils jwtUtils;
    UserStampBoardService userStampBoardService;
    UserPermissionsRepo userPermissionsRepo;
    RewardsRepo rewardsRepo;
    UsersRepo usersRepo;
    GetStampBoardIdFromRewardId getStampBoardIdFromRewardId;
    ShopsRepo shopsRepo;

    public UpdateUserStampPosition(JWTUtils jwt, UserStampBoardService usbs,
                                   UserPermissionsRepo upr, RewardsRepo rr,
                                   UsersRepo ur, GetStampBoardIdFromRewardId gsbifri,
                                   ShopsRepo sr){ //need to add service for changing stamp pos
        jwtUtils = jwt;
        userStampBoardService = usbs;
        userPermissionsRepo = upr;
        rewardsRepo = rr;
        usersRepo = ur;
        getStampBoardIdFromRewardId = gsbifri;
        shopsRepo = sr;
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
        int shopIdConverted = Integer.parseInt(shopId);
        List<UserPermissions> userShops = userPermissionsRepo.findByUserId(jwtUtils.getLoggedInUserId(session).get());
        for (UserPermissions u : userShops) { //loops through userPermissions and saves it to variable to be checked
            if (u.getShop().getShopId() == shopIdConverted) {
                shopPermissionLevel = u.getAdminType().getAdminTypeId();
                shopStampBoardId = u.getShop().getStampBoard().getStampBoardId();
                shopStampBoardSize = u.getShop().getStampBoard().getStampBoardSize();
            }
        }
        if(shopPermissionLevel > 1){//user has the correct level to add/subtract their own stampBoard place
            currentUserStampPos = userStampBoardService.getUserStampPos(jwtUtils.getLoggedInUserId(session).get(), (int) shopStampBoardId );
            Shops shop = shopsRepo.getById(Long.valueOf(shopId));
            StampBoards stampBoard = shop.getStampBoard();
            if(Objects.equals(direction, "subtract")){
                if(currentUserStampPos != 0){
                    userStampBoardService.changeUserStampPosition(jwtUtils.getLoggedInUserId(session).get(), -1, currentUserStampPos, (int) stampBoard.getStampBoardId(), session);
                }
            } else if(Objects.equals(direction, "add")){
                if(currentUserStampPos != shopStampBoardSize){
                    userStampBoardService.changeUserStampPosition(jwtUtils.getLoggedInUserId(session).get(), 1, currentUserStampPos, (int) stampBoard.getStampBoardId(), session);
                    currentUserStampPos = userStampBoardService.getUserStampPos(jwtUtils.getLoggedInUserId(session).get(), (int) shopStampBoardId );
                }
                if(currentUserStampPos == 0){
                    System.out.println("Attempting to create record for user");
                    userStampBoardService.createStampRecord(jwtUtils.getLoggedInUserId(session).get(), 1, (int) shopStampBoardId, session);
                }
            }
        }
    }

    @PostMapping("/reedeemReward")
    public String reedeemStamps(@RequestParam(name="rewardId", required = true) int rewardId, HttpSession session){
        Optional<Rewards> reward = rewardsRepo.findByRewardId(Long.valueOf(rewardId));
        int stampBoardId = getStampBoardIdFromRewardId.getStampBoardId(rewardId);
        Optional<Users> user = usersRepo.findById(Long.valueOf(jwtUtils.getLoggedInUserId(session).get()));
        Set<UserStampBoards> userStampBoards = user.get().getUserStampBoards();
        int userStampPos = 0;

        boolean userIsLinkedToStampBoard = false;
        for(UserStampBoards u : userStampBoards){
            if(stampBoardId == u.getStampBoard().getStampBoardId()){
                userStampPos = u.getUserStampPosition();
                userIsLinkedToStampBoard = true;
            }
        }
        if(userIsLinkedToStampBoard){
            if(userStampPos >= reward.get().getRewardStampLocation()){
                userStampBoardService.changeUserStampPosition(jwtUtils.getLoggedInUserId(session).get(), -reward.get().getRewardStampLocation(), userStampPos, stampBoardId, session);
                //credit to www.programiz.com for code generator
                String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//creates a string of all characters
                StringBuilder sb = new StringBuilder();
                Random random = new Random();
                for(int i = 0; i < 8; i++) {
                    int index = random.nextInt(alphabet.length());
                    char randomChar = alphabet.charAt(index);
                    sb.append(randomChar);
                }
                String code = sb.toString().toUpperCase();
                return code;
            }
        } else {
            System.out.println("User is not linked to stampboard they are trying to claim a reward from");
        }

        return "no";
    }
}
