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
}
