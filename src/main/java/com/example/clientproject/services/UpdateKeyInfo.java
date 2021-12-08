package com.example.clientproject.services;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateKeyInfo {

    @Autowired
    ShopsRepo shopsRepo;

    public void updateInfo(KeyInfoDTO kiDTO){
        Shops shop = shopsRepo.getById((long) kiDTO.getShopId());

        shop.setShopName(kiDTO.getShopName());
        shop.setShopDescription(kiDTO.getShopDescription());

        if(!kiDTO.getBannerName().equalsIgnoreCase("")){
            shop.setShopBanner(kiDTO.getBannerName());
        }
        if(!kiDTO.getLogoName().equalsIgnoreCase("")){
            shop.setShopImage(kiDTO.getLogoName());
        }

        shopsRepo.save(shop);
    }

}
