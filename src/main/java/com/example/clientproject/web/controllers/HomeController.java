package com.example.clientproject.web.controllers;

import com.example.clientproject.data.tags.TagsRepo;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.service.dtos.TagsDTO;
import com.example.clientproject.service.searches.TagSearch;
import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.tags.TagsRepo;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.service.searches.TagSearch;
import com.example.clientproject.services.DashboardStampLoader;
import com.example.clientproject.services.RecommendationGenerator;
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
    private TagSearch tagsSearch;
    private TagsRepo tagsRepo;
    private DashboardStampLoader stampLoader;
    private RecommendationGenerator recommendationGenerator;

    @Autowired
    public HomeController(ShopsRepo ashopsRepo,
                          UserFavouriteToggle uft, TagSearch aTagsSearch,
                          TagsRepo aTagsRepo, JWTUtils jwt,
                          DashboardStampLoader aStampLoader,
                          RecommendationGenerator rg) {
        shopsRepo = ashopsRepo;
        toggleFavourite = uft;
        this.tagsSearch = aTagsSearch;
        this.tagsRepo = aTagsRepo;
        jwtUtils = jwt;
        stampLoader = aStampLoader;
        recommendationGenerator = rg;
    }

    @GetMapping({"/", "dashboard"})
    public String index(Model model, HttpSession session) throws Exception{
        //if (!loggedIn) {
            //model.addAttribute("loggedIn", loggedIn);
            //return "redirect:/login";
        //}

        Optional<Users> user = jwtUtils.getLoggedInUserRow(session);
        if(user.isPresent()){
//            System.out.println(user.get().getFavouriteTags());
            if(user.get().getFavouriteTags().size() == 0){
                model.addAttribute("selectCategories", true);
            }
        }else{
            return "redirect:/login";
        }

        int userId = jwtUtils.getLoggedInUserId(session).get();

        //System.out.println(shopsRepo.findAll());
        List<Shops> allShops = shopsRepo.findActiveShops();

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


        List<TagsDTO> Tags = tagsSearch.findAll();
        //System.out.println(Tags);

        model.addAttribute("allTags", Tags);


        model.addAttribute("favouriteShops", stampLoader.getData(userId).get("favourited"));
        model.addAttribute("activeShops",stampLoader.getData(userId).get("purchased"));
        model.addAttribute("loggedInUser", user.get());
        model.addAttribute("normalShops", recommendationGenerator.getRecommendations(session, normalShops));
        model.addAttribute("tags", new String[]{"Coffee", "Vegan", "Sustainable"});
        return "index";
    }
}
