package com.example.clientproject.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessRegisterForm {
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

    public void setBusinessTags(String tempTags){
        System.out.println(tempTags);
        this.businessTags = new ArrayList(Arrays.asList(tempTags.split(",")));
    }

}
