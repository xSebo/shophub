package com.example.clientproject.services;

import com.example.clientproject.web.forms.UpdateKeyInfoForm;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class KeyInfoDTO {
    int shopId;
    String shopName;
    String shopDescription;
    String bannerName;
    String logoName;

    public KeyInfoDTO(UpdateKeyInfoForm form){
        this(
          form.getShopId(),
          form.getShopName(),
          form.getShopDescription(),
          form.getBannerName(),
          form.getLogoName()
        );
    }
}
