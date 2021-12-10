package com.example.clientproject.data.userPermissions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JPA Repository for the "UserPermissions" Entity
 * Extends the JpaRepository library with the types of "UserPermissions" and "Long"
 */
public interface UserPermissionsRepo extends JpaRepository<UserPermissions, Long> {
    /**
     * Save method
     * @param userPermissions - the object to save
     * @return - the object
     */
    UserPermissions save(UserPermissions userPermissions);

    /**
     * Search by the userId of the userPermission
     * @param userId - the id to search by
     * @return - an option containing the object if one is found
     */
    @Query("select u from UserPermissions u where u.user.userId = ?1")
    List<UserPermissions> findByUserId(long userId);

    /**
     * Search by the shopId of the userPermission
     * @param shopId - the name to search by
     * @return - an option containing the object if one is found
     */
    @Query("select u from UserPermissions u where u.shop.shopId = ?1")
    List<UserPermissions> findByShopID(long shopId);

    /**
     * Search by the adminTypeId of the UserPermission
     * @param adminTypeId - the id to search by
     * @return - an option containing the object if one is found
     */
    @Query("select u from UserPermissions u where u.adminType.adminTypeId = ?1")
    List<UserPermissions> findByAdminTypeId(long adminTypeId);

}
