package com.example.clientproject.data.users;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.twoFactorMethods.TwoFactorMethods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long userId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private String userProfilePicture;
    private String userResetCode;
    private String userResetCodeExpiry;

    @ManyToOne
    @JoinColumn(name="Two_Factor_Method_Id", nullable=false)
    private TwoFactorMethods twoFactorMethod;

    @ManyToMany
    @JoinTable(
            name="UserShopLinks",
            joinColumns = @JoinColumn(name="User_Id"),
            inverseJoinColumns = @JoinColumn(name="Shop_Id")
    )
    private List<Shops> favouriteShops;
}
