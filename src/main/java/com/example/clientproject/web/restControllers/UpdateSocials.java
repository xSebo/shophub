package com.example.clientproject.web.restControllers;

import com.example.clientproject.data.userPermissions.UserPermissions;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import com.example.clientproject.exceptions.ForbiddenErrorException;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.services.UserSocialDTO;
import com.example.clientproject.services.UserSocialSave;
import com.example.clientproject.web.forms.UpdateSocialsForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class UpdateSocials {

    private UserSocialSave save;
    private UserPermissionsRepo userPermRepo;
    private JWTUtils jwtUtils;

    public UpdateSocials(UserSocialSave socialSave, UserPermissionsRepo upr,
                         JWTUtils jwt){
        save = socialSave;
        userPermRepo = upr;
        jwtUtils = jwt;
    }

    @PostMapping("/updateSocials")
    public String updateSocials(UpdateSocialsForm usf, HttpSession session){
        UserSocialDTO userSocialDTO = new UserSocialDTO(usf);

        List<UserPermissions> allLinks = userPermRepo.
                findByUserId(jwtUtils.getLoggedInUserId(session).get());
        boolean isLinked = false;
        for(UserPermissions u:allLinks){
            if(u.getShop().getShopId() == usf.getShopId()){
                isLinked = true;
                break;
            }
        }

        if(!isLinked){
            throw new ForbiddenErrorException("User not authenticated");
        }
        save.updateSocials(userSocialDTO, session);
        return "OK";
    }
}
