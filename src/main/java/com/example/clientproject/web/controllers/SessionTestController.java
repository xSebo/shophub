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
import java.util.Optional;

@Controller
public class SessionTestController {

    private JWTUtils jwtUtils;

    public SessionTestController(JWTUtils ajwtUtils){
        jwtUtils = ajwtUtils;
    }

    @GetMapping("/sessionJWTTest")
    public String jwtTest(Model model, HttpSession session){
        Optional<Integer> user = jwtUtils.getLoggedInUserId(session);
        if(user.isPresent()){
            System.out.println(user.get());
        }else{
            System.out.println("No User");
        }

        System.out.println("Making jwt");
        String jwt = jwtUtils.makeUserJWT(6, session);
        System.out.println(jwt);

        user = jwtUtils.getLoggedInUserId(session);
        if(user.isPresent()){
            System.out.println(user.get());
        }else{
            System.out.println("No User");
        }

        Users loggedInUser = jwtUtils.getLoggedInUserRow(session).get();

        model.addAttribute("sessionData",loggedInUser);

        return "session-test";
    }

}




