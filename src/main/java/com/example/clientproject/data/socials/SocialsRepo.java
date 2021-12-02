package com.example.clientproject.data.socials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocialsRepo extends JpaRepository<Socials, Long> {

    @Query("select s from Socials s where s.shop.shopId = ?1")
    public List<Socials> findByShopId(long shopId);
}
