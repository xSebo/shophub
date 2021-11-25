package com.example.clientproject.services;

import com.example.clientproject.web.forms.UserFavouriteForm;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserFavouriteDTO {
    long userId;
    long shopId;

    public UserFavouriteDTO(UserFavouriteForm urf){
        this(
                urf.getUserId(),
                urf.getShopId()
        );
    }
}
