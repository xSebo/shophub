package com.example.clientproject.web.restControllers;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.exceptions.ForbiddenErrorException;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.services.RecommendationGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class Recommendations {
    public JWTUtils jwtUtils;
    public RecommendationGenerator recommendationGenerator;

    public Recommendations(JWTUtils jwt, RecommendationGenerator r){
        jwtUtils = jwt;
        recommendationGenerator = r;
    }

    @GetMapping("/recommend")
    public String getRecommendationsForUser(HttpSession session){

        try {
            List<Shops> recommendations = recommendationGenerator.getRecommendations(session);
            return recommendations.toString();
        }catch (Exception e){
            e.printStackTrace();
            return "User Not Logged In";
        }
    }
}
