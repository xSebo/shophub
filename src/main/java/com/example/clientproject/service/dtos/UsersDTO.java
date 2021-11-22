package com.example.clientproject.service.dtos;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.userStampBoards.UserStampBoards;
import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.twoFactorMethods.TwoFactorMethods;
import com.example.clientproject.data.users.Users;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.Set;

@Value
@AllArgsConstructor
public class UsersDTO {
    long userId;
    String userFirstName;
    String userLastName;
    String userEmail;
    String userPassword;
    String userProfilePicture;
    String userResetCode;
    String userResetCodeExpiry;
    TwoFactorMethods twoFactorMethod;
    List<Shops> favouriteShops;
    Set<UserStampBoards> userStampBoards;
    List<Tags> favouriteTags;

    public UsersDTO(Users user) {
        this(
                user.getUserId(),
                user.getUserFirstName(),
                user.getUserLastName(),
                user.getUserEmail(),
                user.getUserPassword(),
                user.getUserProfilePicture(),
                user.getUserResetCode(),
                user.getUserResetCodeExpiry(),
                user.getTwoFactorMethod(),
                user.getFavouriteShops(),
                user.getUserStampBoards(),
                user.getFavouriteTags());
    }
}
