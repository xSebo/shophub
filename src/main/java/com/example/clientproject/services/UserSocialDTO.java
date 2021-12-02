package com.example.clientproject.services;

import com.example.clientproject.web.forms.UpdateSocialsForm;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserSocialDTO {
    int shopId;
    String instagram;
    String facebook;
    String twitter;
    String tiktok;
    String shopUrl;

    public UserSocialDTO(UpdateSocialsForm usf){
        this(
                usf.getShopId(),
                usf.getInstagram(),
                usf.getFacebook(),
                usf.getTwitter(),
                usf.getTiktok(),
                usf.getShopUrl()
        );
    }
}
