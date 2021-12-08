package com.example.clientproject.web.restControllers;

import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.service.dtos.UsersDTO;
import com.example.clientproject.service.searches.UsersSearch;
import com.example.clientproject.services.UserFavouriteDTO;
import com.example.clientproject.web.forms.UserFavouriteForm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;

@RestController
public class EmailValidator {
    public UsersSearch usersSearch;

    public EmailValidator(UsersSearch usersSearch1){
        usersSearch = usersSearch1;
    }

    @GetMapping("/emailInUse")
    public boolean emailInUse(@RequestParam(value = "email") String email) throws Exception{
        Optional<UsersDTO> users = usersSearch.findByEmail(email.toLowerCase());
        return users.isPresent();
    }
}
