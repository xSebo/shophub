package com.example.clientproject.web.restControllers;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
public class ShopSearch {
    @Autowired
    ShopsRepo shopsRepo;

    @GetMapping("/shop/search")
    public String searchShops(@RequestParam(value = "q", required = false) String query,
                              @RequestParam(value = "p", required = false) Integer page,
                              @RequestParam(value = "t", required = false) List<String> tags){
        final Integer itemsPerPage = 6;

        //Get all the active shops
        List<Shops> allShops = shopsRepo.findActiveShops();

        //Filter the shops using the query provided
        if(query != null){
            allShops = allShops
                    .stream()
                    .filter(s -> s.getShopName().toLowerCase(Locale.ROOT).strip().contains(query.toLowerCase(Locale.ROOT).strip()))
                    .collect(Collectors.toList());
        }

        //Filter using the tags provided

        //Paginate

        //Convert to required format

        return allShops.toString();

    }
}
