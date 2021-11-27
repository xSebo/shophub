package com.example.clientproject.web.controllers;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.services.UserFavouriteDTO;
import com.example.clientproject.services.UserFavouriteToggle;
import com.example.clientproject.web.forms.UserFavouriteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.clientproject.web.controllers.signUpAndIn.SignInController.loggedIn;

@Controller
public class HomeController {

    private ShopsRepo shopsRepo;
    private UserFavouriteToggle toggleFavourite;
    private JWTUtils jwtUtils;

    @Autowired
    public HomeController(ShopsRepo ashopsRepo, UserFavouriteToggle uft, JWTUtils jwt) {
        shopsRepo = ashopsRepo;
        toggleFavourite = uft;
        jwtUtils = jwt;
    }

    @GetMapping({"/", "dashboard"})
    public String index(Model model, HttpSession session, HttpServletResponse httpResponse) throws Exception{
        Optional<Users> user = jwtUtils.getLoggedInUserRow(session);
        if(user.isPresent()){
            Users loggedInUser = user.get();
        }else{
            return "redirect:/login";
        }

        List<Shops> allShops = shopsRepo.findAll();

        List<Shops> favouriteShops = new ArrayList();
        List<Shops> normalShops = new ArrayList();

        for(Shops s : allShops){
            UserFavouriteForm uff = new UserFavouriteForm(s.getShopId());
            if(toggleFavourite.alreadyInDb(new UserFavouriteDTO(uff, jwtUtils.getLoggedInUserId(session).get()))){
                favouriteShops.add(s);
            }else{
                normalShops.add(s);
            }
        }


        model.addAttribute("normalShops", normalShops);
        model.addAttribute("favouriteShops", favouriteShops);
        model.addAttribute("tags", new String[]{"Coffee", "Vegan", "Sustainable"});
        return "index";
    }
}
