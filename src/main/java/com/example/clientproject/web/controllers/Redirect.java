package com.example.clientproject.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Redirect {

    /**
     * Redirects user to new url, workaround for models not loading properly on redirect to same
     * controller.
     * @param url, the page to redirect to (e.g. /businessRegister), submitted like:
     *             /redirect?url=<page>
     * @return Redirection.
     */
    @GetMapping("/redirect")
    public String redirect(@RequestParam(name="url") String url){
        try{
            return "redirect:/"+url;
        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/";
        }
    }

}
