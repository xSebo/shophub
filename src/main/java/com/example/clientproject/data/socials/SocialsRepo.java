package com.example.clientproject.data.socials;

import com.example.clientproject.data.shops.Shops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SocialsRepo extends JpaRepository<Socials, Long> {

    /**
     * Search by stampBoardId
     * @param shopId - the id of the Shop to search by
     * @return - an optional containing the Socials found
     */
    @Query("select s from Socials s where s.shop.shopId = ?1")
    List<Socials> findByShopId(long shopId);

}
