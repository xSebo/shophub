package com.example.clientproject.services;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UpdateKeyInfo {

    ShopsRepo shopsRepo;
    LoggingService loggingService;

    public UpdateKeyInfo(ShopsRepo shopsRepo, LoggingService loggingService) {
        this.shopsRepo = shopsRepo;
        this.loggingService = loggingService;
    }

    public void updateInfo(KeyInfoDTO kiDTO, HttpSession session){
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
        // Log the change
        loggingService.logEvent(
                "Shop Updated",
                session,
                "Shop updated with Shop Id: " + kiDTO.getShopId() +
                        " in UpdateKeyInfo.updateInfo()"
        );
    }

}
