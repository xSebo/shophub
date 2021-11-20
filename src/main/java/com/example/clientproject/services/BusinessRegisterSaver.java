package com.example.clientproject.services;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessRegisterSaver {

    @Autowired
    ShopsRepo shopsRepo;

    public void save(BusinessRegisterDTO business){

        Shops shop = new Shops(business.getBusiness_register_name(),
                business.getBusiness_register_url(),
                business.getEarnings(),
                "shopPic.png",
                "UK United Kingdom",
                false);

        shopsRepo.save(shop);

        System.out.println(shopsRepo.findByShopName(business.getBusiness_register_name()));
    }

}
