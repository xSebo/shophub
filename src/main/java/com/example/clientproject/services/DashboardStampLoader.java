package com.example.clientproject.services;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.userStampBoards.UserStampBoards;
import com.example.clientproject.data.users.UsersRepo;
import com.example.clientproject.web.forms.UserFavouriteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Http2;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.*;

@Service
public class DashboardStampLoader {
    UsersRepo userRepo;
    ShopsRepo shopsRepo;
    UserFavouriteToggle toggleFavourite;

    public DashboardStampLoader(UsersRepo userRepo, ShopsRepo shopsRepo, UserFavouriteToggle toggleFavourite) {
        this.userRepo = userRepo;
        this.shopsRepo = shopsRepo;
        this.toggleFavourite = toggleFavourite;
    }

    public Map<String, Object> getData(int userId) throws Exception {
        List<Map<String, Object>> combinedInfo = new ArrayList<>();

        Set<UserStampBoards> userStamps = userRepo.findById((long) userId).get().getUserStampBoards();

        for(UserStampBoards u:userStamps){
            if(shopsRepo.findByStampboardId(u.getStampBoard().getStampBoardId()).isShopActive()) {
                combinedInfo.add(
                        new HashMap<>() {{
                            put("UserStampBoard", u);
                            put("Shop", shopsRepo.
                                    findByStampboardId(u.getStampBoard().getStampBoardId()));
                        }}
                );
            }
        }

        List<Shops> favouriteShops = new ArrayList<>();

        boolean alreadyInPuchased = false;

        for(Shops s:shopsRepo.findAll()){
            if(s.isShopActive()) {
                UserFavouriteDTO ufDTO = new UserFavouriteDTO(new UserFavouriteForm(s.getShopId()), userId);
                if (toggleFavourite.alreadyInDb(ufDTO)) {
                    for (Map<String, Object> m : combinedInfo) {
                        Shops shop = (Shops) m.get("Shop");
                        if (shop.getShopId() == s.getShopId()) {
                            alreadyInPuchased = true;
                        }
                    }
                    if (!alreadyInPuchased) {
                        favouriteShops.add(s);
                        System.out.println(s.isShopActive());
                    }
                    alreadyInPuchased = false;

                }
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("purchased", combinedInfo);
        map.put("favourited",favouriteShops);


        return map;
    }
}
