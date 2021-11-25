package com.example.clientproject.web.controllers;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.service.searches.UsersSearch;
import com.example.clientproject.services.BusinessRegisterSaver;
import com.example.clientproject.services.UserFavouriteDTO;
import com.example.clientproject.services.UserFavouriteToggle;
import com.example.clientproject.web.forms.UserFavouriteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

import static com.example.clientproject.web.controllers.SignInController.loggedIn;

@Controller
public class HomeController {

    private ShopsRepo shopsRepo;
    private UserFavouriteToggle toggleFavourite;

    @Autowired
    public HomeController(ShopsRepo ashopsRepo, UserFavouriteToggle uft) {
        shopsRepo = ashopsRepo;
        toggleFavourite = uft;
    }

    @GetMapping({"/", "dashboard"})
    public String index(Model model) throws Exception{
        loggedIn=true;
        if (!loggedIn) {
            model.addAttribute("loggedIn", loggedIn);
            return "redirect:/login";
        }
        //System.out.println(shopsRepo.findAll());
        List<Shops> allShops = shopsRepo.findAll();

        List<Shops> favouriteShops = new ArrayList();
        List<Shops> normalShops = new ArrayList();

        for(Shops s : allShops){
            UserFavouriteForm uff = new UserFavouriteForm(2,s.getShopId());
            if(toggleFavourite.alreadyInDb(new UserFavouriteDTO(uff))){
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
