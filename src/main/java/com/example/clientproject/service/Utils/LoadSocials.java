package com.example.clientproject.service.Utils;

import com.example.clientproject.data.socials.Socials;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class LoadSocials {

    public String getSocial(List<Socials> socialList, String socialPlatform){
        for(Socials s:socialList){
            if(s.getSocialPlatform().equalsIgnoreCase(socialPlatform)){
                return s.getSocialName();
            }
        }return "ERROR";
    }
    public String removeHttps(String url) throws Exception{
        URI uri = new URI(url);
        //System.out.println(uri.getHost());
        String host = uri.getHost();
        return host;
    }

    /**
     * Method for accessing the login page
     * @param socialList, socialPlatform - Model object which contains all of a shops socials, and socialPatform is used to search/filter
     * @return - retuns true/false based on whether the shop has a social media for that platform
     */

    public boolean doesShopHaveSocialPlatform(List<Socials> socialList, String socialPlatform){
        for(Socials s:socialList){
            if(s.getSocialPlatform().equalsIgnoreCase(socialPlatform)){
                return true;
            }
        }return false;
    }
}
