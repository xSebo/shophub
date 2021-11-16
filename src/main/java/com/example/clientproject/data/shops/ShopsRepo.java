package com.example.clientproject.data.shops;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShopsRepo extends JpaRepository<Shops, Long> {
    List<Shops> findAll();

    @Query("select s from Shops s where s.shopName = ?1")
    Optional<Shops> findByShopName(String shopName);

    @Query("select s from Shops s where s.shopEarnings > 0 and s.shopEarnings < 10000")
    List<Shops> findByFreeLicense();

    @Query("select s from Shops s where s.shopEarnings > 10000 and s.shopEarnings < 40000")
    List<Shops> findByStandardLicense();

    @Query("select s from Shops s where s.shopEarnings > 40000")
    List<Shops> findByPremiumLicense();

    @Query("select s from Shops s where s.shopActive = true")
    List<Shops> findActiveShops();

    @Query("select s from Shops s where s.shopActive = false")
    List<Shops> findInactiveShops();
}
