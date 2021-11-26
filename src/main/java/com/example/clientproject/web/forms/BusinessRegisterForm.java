package com.example.clientproject.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessRegisterForm {

    @NotEmpty(message="Url Cannot be Empty")
    @Pattern(regexp = "http(s)?:\\/\\/(www[.])?([a-zA-Z0-9$_.+!*'(),]|[-])*[.].+", message = "Your url must fit a valid format")
    String business_register_url;

    @NotEmpty(message="Name Cannot be Empty")
    String business_register_name;

    @NotEmpty(message="Description Cannot be Empty")
    String business_register_desc;

    @NotEmpty(message="Category Cannot be Empty")
    String businessCategory;

    @NotEmpty(message="Tags Cannot be Empty")
    ArrayList<String> businessTags;
    
    String instagram;
    String facebook;
    String twitter;
    String tiktok;

    int earnings;

    public void setBusinessTags(String tempTags){
        this.businessTags = new ArrayList(Arrays.asList(tempTags.split(",")));
    }
}
