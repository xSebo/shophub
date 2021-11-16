package com.example.clientproject.data.userPermissions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserPermissionsRepo extends JpaRepository<UserPermissions, Long> {
    List<UserPermissions> findAll();

    @Query("select u from UserPermissions u where u.user.userId = ?1")
    List<UserPermissions> findByUserId(long userId);

    @Query("select u from UserPermissions u where u.shop.shopID = ?1")
    List<UserPermissions> findByShopID(long shopId);

    @Query("select u from UserPermissions u where u.adminType.adminTypeID = ?1")
    List<UserPermissions> findByAdminTypeId(long adminTypeId);
}
