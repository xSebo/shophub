package com.example.clientproject.web.controllers;

import com.example.clientproject.data.users.Users;
import com.example.clientproject.service.Utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SessionTestController {
    @GetMapping("/session")
    public String getSessionPage(Model model, HttpSession session){

        try{
            Claims claims = JWTUtils.decodeJWT("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.-oiqAKFkf3Q_rKaMSfhYYAbslNHlMFEen1_0aUo706Q");
            System.out.println(claims);
        }catch (io.jsonwebtoken.MalformedJwtException e){
            System.out.println("malformed jwt");
        }catch (io.jsonwebtoken.SignatureException e){
            System.out.println("JWT was edited outside this scope");
        }


        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
        model.addAttribute("sessionData",messages);
        return "session-test";
    }

    @GetMapping("/sessionSave")
    public String setSessionData(Model model, HttpSession session){
        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
        if (messages == null) {
            messages = new ArrayList<>();
            session.setAttribute("MY_SESSION_MESSAGES", messages);
        }
        messages.add("test");
        session.setAttribute("MY_SESSION_MESSAGES", messages);

        model.addAttribute("sessionData",messages);
        return "session-test";
    }

    @GetMapping("/sessionDelete")
    public String deleteSessionData(Model model, HttpSession session){
        List<String> messages = new ArrayList<>();
        session.setAttribute("MY_SESSION_MESSAGES", messages);
        model.addAttribute("sessionData",messages);
        return "session-test";
    }

    @GetMapping("/sessionJWTTest")
    public String jwtTest(Model model, HttpSession session){
        String jwt = JWTUtils.makeUserJWT(5, session);

        System.out.println("jwt = \"" + jwt.toString() + "\"");

        Claims claims = null;
        try{
            claims = JWTUtils.decodeJWT(jwt);
        }catch (io.jsonwebtoken.MalformedJwtException e){
            System.out.println("malformed jwt");
        }catch (io.jsonwebtoken.SignatureException e){
            System.out.println("JWT was edited outside this scope");
        }
        System.out.println(claims);

        model.addAttribute("sessionData",claims);

        return "session-test";
    }
}




