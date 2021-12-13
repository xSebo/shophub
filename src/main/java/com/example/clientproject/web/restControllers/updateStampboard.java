package com.example.clientproject.web.restControllers;

import com.example.clientproject.data.userPermissions.UserPermissions;
import com.example.clientproject.data.userPermissions.UserPermissionsRepo;
import com.example.clientproject.exceptions.ForbiddenErrorException;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.service.searches.UsersSearch;
import com.example.clientproject.services.StampboardUpdater;
import com.example.clientproject.web.forms.UpdateStampboardForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class updateStampboard {
    UserPermissionsRepo userPermRepo;
    JWTUtils jwtUtils;
    StampboardUpdater stampboardUpdater;

    public updateStampboard(UserPermissionsRepo upr, JWTUtils jwt, StampboardUpdater su){
        userPermRepo = upr;
        jwtUtils = jwt;
        stampboardUpdater = su;
    }

    @PostMapping("/stampboard/update")
    public String updateStampboard(UpdateStampboardForm usf, HttpSession session){

        List<UserPermissions> allLinks = userPermRepo.
                findByUserId(jwtUtils.getLoggedInUserId(session).get());
        boolean isLinked = false;
        for (UserPermissions u:allLinks){
            if(u.getShop().getShopId() == usf.getShopId()){
                isLinked = true;
                break;
            }
        }
        if(!isLinked){
            throw new ForbiddenErrorException("You do not have admin access to this shop");
        }

        Map<String,Object> rewardsMap;
        try{
            ObjectMapper mapper = new ObjectMapper();
            rewardsMap = mapper.readValue(usf.getRewardMapping(), Map.class);
        }catch (JsonProcessingException e){
            throw new ForbiddenErrorException("Invalid JSON");
        }

        if(!usf.getColour().matches("[#][a-f0-9]{6}")){
            throw new ForbiddenErrorException("Invalid Colour value");
        }

        stampboardUpdater.updateColour(usf.getShopId(), usf.getColour(), session);
        stampboardUpdater.updateStampboardSize(usf.getShopId(), usf.getStampboardSize(), session);
        stampboardUpdater.updateRewards(usf.getShopId(), rewardsMap, session);
        if(usf.getIconFilePath().length() > 0){
            stampboardUpdater.updateIconURL(usf.getShopId(), usf.getIconFilePath(), session);
        }
        return "OK";
    }
}
