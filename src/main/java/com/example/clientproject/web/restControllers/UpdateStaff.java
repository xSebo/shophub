package com.example.clientproject.web.restControllers;

import com.example.clientproject.data.adminTypes.AdminTypes;
import com.example.clientproject.data.adminTypes.AdminTypesRepo;
import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.userPermissions.UserPermissions;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.data.users.UsersRepo;
import com.example.clientproject.exceptions.ForbiddenErrorException;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.services.LinkUserShop;
import com.example.clientproject.services.UserLinked;
import com.example.clientproject.web.forms.UpdateStaffForm;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UpdateStaff {

    private LinkUserShop linkUser;
    private UsersRepo userRepo;
    private UserPermissionsRepo userPermRepo;
    private JWTUtils jwtUtils;
    private UserLinked userLinked;

    public UpdateStaff(LinkUserShop aLink, UsersRepo ur, UserPermissionsRepo upr,
                       JWTUtils jwt, UserLinked linked){
        linkUser = aLink;
        userRepo = ur;
        userPermRepo = upr;
        jwtUtils = jwt;
        userLinked = linked;
    }

    @PostMapping("/updateStaff")
    public String addStaff(UpdateStaffForm usf, HttpSession session){
        if((!userLinked.isLinked(jwtUtils.getLoggedInUserId(session).get(), usf.getShopId())) &&
                (!userLinked.isAdmin(jwtUtils.getLoggedInUserId(session).get(), usf.getShopId()))){
            return "USER NOT AUTHENTICATED";
        }
        int userId;
        try{
            userId = (int) userRepo.findByUserEmail(usf.getEmail()).get().getUserId();
        }catch(Exception e){
            e.printStackTrace();
            throw new ForbiddenErrorException("BAD EMAIL");
        }

        if(userLinked.isLinked(userId,usf.getShopId())){
            List<UserPermissions> userPerms = userPermRepo.findByUserId(userId);
            UserPermissions userPerm = userPermRepo.findById(1L).get();
            for(UserPermissions u:userPerms){
                if(u.getShop().getShopId()== usf.getShopId()){
                    userPerm = u;
                }
            }
            userPermRepo.delete(userPerm);
            return "USER REMOVED";
        }else{
            linkUser.linkUserShop(usf.getShopId(),userId, 1L, session);
            return "OK";
        }
    }
}
