package com.example.clientproject.data.shops;

import com.example.clientproject.data.categories.Categories;
import com.example.clientproject.data.converters.TinyIntToBoolean;
import com.example.clientproject.data.rewards.Rewards;
import com.example.clientproject.data.socials.Socials;
import com.example.clientproject.data.stampBoards.StampBoards;
import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.userStampBoards.UserStampBoards;
import com.example.clientproject.data.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Shops Entity
 * Contains Getters, Setters, and constructors thanks to Lombok
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="shops")
public class Shops {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long shopId;
    private String shopName;
    private String shopDescription;
    private String shopWebsite;
    private int shopEarnings;
    private String shopImage;
    private String shopBanner;
    private String shopCountries;

    @Convert(converter = TinyIntToBoolean.class)
    private boolean shopActive;

    /**
     * Constructor which does not require an ID
     * @param name - name of the shop
     * @param website - shop website
     * @param earnings - shop earnings
     * @param image - shop image
     * @param countries - shop countries
     * @param active - shop active status
     */
    public Shops(String name, String website, String description, int earnings,
                 String image, String banner, String countries, boolean active, StampBoards stampBoard,
                 Categories categories) {
        this.shopName = name;
        this.shopDescription = description;
        this.shopWebsite = website;
        this.shopEarnings = earnings;
        this.shopImage = image;
        this.shopBanner = banner;
        this.shopCountries = countries;
        this.shopActive = active;
        this.stampBoard = stampBoard;
        this.category = categories;
    }

    @ManyToMany(mappedBy="favouriteShops")
    private List<Users> favouriteUsers;

    @ManyToMany
    @JoinTable(
            name="Shop_Tag_Links",
            joinColumns = @JoinColumn(name="Shop_Id"),
            inverseJoinColumns = @JoinColumn(name="Tag_Id")
    )
    private List<Tags> shopTags;

    @OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="Stamp_Board_Id")
    private StampBoards stampBoard;

    @ManyToOne
    @JoinColumn(name="Category_Id")
    private Categories category;

    public String toString(){
        return this.getShopName();
    }


}
