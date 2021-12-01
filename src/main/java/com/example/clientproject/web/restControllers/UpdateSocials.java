package com.example.clientproject.web.restControllers;

import com.example.clientproject.services.UserSocialDTO;
import com.example.clientproject.services.UserSocialSave;
import com.example.clientproject.web.forms.UpdateSocialsForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
public class UpdateSocials {

    private UserSocialSave save;

    public UpdateSocials(UserSocialSave socialSave){
        save = socialSave;
    }

    @PostMapping("/updateSocials")
    public String updateSocials(UpdateSocialsForm usf, HttpSession session){
        UserSocialDTO userSocialDTO = new UserSocialDTO(usf);
        save.updateSocials(userSocialDTO);
        return "OK";
    }
}
