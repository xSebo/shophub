package com.example.clientproject.data.shops;

import com.example.clientproject.data.converters.TinyIntToBoolean;
import com.example.clientproject.data.rewards.Rewards;
import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    private int shopId;
    private String shopName;
    private String shopDescription;
    private String shopWebsite;
    private int shopEarnings;
    private String shopImage;
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
                 String image, String countries, boolean active) {
        this.shopName = name;
        this.shopDescription = description;
        this.shopWebsite = website;
        this.shopEarnings = earnings;
        this.shopImage = image;
        this.shopCountries = countries;
        this.shopActive = active;
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
}
