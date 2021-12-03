package com.example.clientproject.services;

import com.example.clientproject.web.forms.BusinessRegisterForm;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.ArrayList;

@Value
@AllArgsConstructor
public class BusinessRegisterDTO {
    String business_register_url;
    String business_register_name;
    String business_register_desc;
    String businessCategory;
    ArrayList<String> businessTags;
    String instagram;
    String facebook;
    String twitter;
    String tiktok;
    int earnings;

    public BusinessRegisterDTO(BusinessRegisterForm brf){
        this(
                brf.getBusiness_register_url(),
                brf.getBusiness_register_name(),
                brf.getBusiness_register_desc(),
                brf.getBusinessCategory(),
                brf.getBusinessTags(),
                brf.getInstagram(),
                brf.getFacebook(),
                brf.getTwitter(),
                brf.getTiktok(),
                brf.getEarnings()
        );
    }
}
