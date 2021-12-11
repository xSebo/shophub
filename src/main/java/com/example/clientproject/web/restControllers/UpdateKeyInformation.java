package com.example.clientproject.web.restControllers;

import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.services.KeyInfoDTO;
import com.example.clientproject.services.UpdateKeyInfo;
import com.example.clientproject.web.forms.UpdateKeyInfoForm;
import org.hibernate.sql.Update;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UpdateKeyInformation {

    private JWTUtils jwtUtils;
    private UpdateKeyInfo updateInfo;

    public UpdateKeyInformation(JWTUtils utils, UpdateKeyInfo update){
        jwtUtils = utils;
        updateInfo = update;
    }


    @PostMapping("/updateKeyInformation")
    public String updateInfo(HttpSession session, UpdateKeyInfoForm keyInformationForm){
        if(!jwtUtils.getLoggedInUserId(session).isPresent()){
            return "BAD SESSION";
        }
        try{
            updateInfo.updateInfo(new KeyInfoDTO(keyInformationForm), session);
            return "OK";
        }catch (Exception e){
            e.printStackTrace();
            return "ERROR";
        }


    }
}
