package com.example.clientproject.data.users;

import com.example.clientproject.data.logs.Logs;
import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.userStampBoards.UserStampBoards;
import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.twoFactorMethods.TwoFactorMethods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Users Entity
 * Contains Getters, Setters, and constructors thanks to Lombok
 */
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

    /**
     * Constructor
     * @param firstName - user first name
     * @param surname - user surname
     * @param email - user email
     * @param password - user password
     * @param profilePicture - user profile picture
     * @param resetCode - user password reset code
     * @param expiry - user password reset code expiry date time
     * @param aTwoFactorMethod - user two-factor authentication method
     */
    public Users(String firstName, String surname, String email,
                 String password, String profilePicture, String resetCode,
                 String expiry, TwoFactorMethods aTwoFactorMethod) {
        this.userFirstName = firstName;
        this.userLastName = surname;
        this.userEmail = email;
        this.userPassword = password;
        this.userProfilePicture = profilePicture;
        this.userResetCode = resetCode;
        this.userResetCodeExpiry = expiry;
        this.twoFactorMethod = aTwoFactorMethod;
    }

    public Users(String firstName, String surname, String email,
                 String password, String profilePicture,
                 TwoFactorMethods aTwoFactorMethod) {
        this.userFirstName = firstName;
        this.userLastName = surname;
        this.userEmail = email;
        this.userPassword = password;
        this.userProfilePicture = profilePicture;
        this.twoFactorMethod = aTwoFactorMethod;
    }

    @ManyToOne
    @JoinColumn(name="Two_Factor_Method_Id", nullable=false)
    private TwoFactorMethods twoFactorMethod;

    @ManyToMany
    @JoinTable(
            name="User_Shop_Links",
            joinColumns = @JoinColumn(name="User_Id"),
            inverseJoinColumns = @JoinColumn(name="Shop_Id")
    )
    private List<Shops> favouriteShops;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="User_Id", nullable = false)
    private Set<UserStampBoards> userStampBoards;

    @ManyToMany
    @JoinTable(
            name="User_Favourite_Tags",
            joinColumns = @JoinColumn(name="User_Id"),
            inverseJoinColumns = @JoinColumn(name="Tag_Id")
    )
    private List<Tags> favouriteTags;

    public String toString(){
        return "User:" + this.getUserFirstName();
    }


}
