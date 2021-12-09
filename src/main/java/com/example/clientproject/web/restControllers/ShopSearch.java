package com.example.clientproject.web.restControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShopSearch {
    @GetMapping("/shop/search")
    public String searchShops(@RequestParam(value = "q", required = false) String query,
                              @RequestParam(value = "p", required = false) Integer page,
                              @RequestParam(value = "t", required = false) List<Integer> tags){
        System.out.println(tags);
        System.out.println(query);
        System.out.println(page);

        return "here";

    }
}
