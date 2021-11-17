package com.example.clientproject.data.shops;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for the "Shops" Entity
 * Extends the JpaRepository library with the types of "Shops" and "Long"
 */
public interface ShopsRepo extends JpaRepository<Shops, Long> {
    /**
     * FindAll method
     * @return list of Shops found
     */
    List<Shops> findAll();

    /**
     * Save method
     * @param shops - the object to save
     * @return - the object
     */
    Shops save(Shops shops);

    /**
     * Search by the name of the shop
     * @param shopName - the name to search by
     * @return - an option containing the object if one is found
     */
    @Query("select s from Shops s where s.shopName = ?1")
    Optional<Shops> findByShopName(String shopName);

    /**
     * Search for shops using a free license
     * @return - a list of the objects found
     */
    @Query("select s from Shops s where s.shopEarnings > 0 and s.shopEarnings < 10000")
    List<Shops> findByFreeLicense();

    /**
     * Search for shops using a standard license
     * @return - a list of the objects found
     */
    @Query("select s from Shops s where s.shopEarnings > 10000 and s.shopEarnings < 40000")
    List<Shops> findByStandardLicense();

    /**
     * Search for shops using a premium license
     * @return - a list of the objects found
     */
    @Query("select s from Shops s where s.shopEarnings > 40000")
    List<Shops> findByPremiumLicense();

    /**
     * Search for active shops
     * @return - a list containing the shops found
     */
    @Query("select s from Shops s where s.shopActive = true")
    List<Shops> findActiveShops();

    /**
     * Search for inactive shops
     * @return - a list containing the objects found
     */
    @Query("select s from Shops s where s.shopActive = false")
    List<Shops> findInactiveShops();
}
