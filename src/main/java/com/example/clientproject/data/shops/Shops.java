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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shops {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String shopID;
    private String shopName;
    private String shopWebsite;
    private int shopEarnings;
    private String shopCountries;

    @Convert(converter = TinyIntToBoolean.class)
    private boolean shopActive;

    @ManyToMany(mappedBy="favouriteShops")
    private List<Users> favouriteUsers;

    @ManyToMany
    @JoinTable(
            name="Shop_Tag_Links",
            joinColumns = @JoinColumn(name="Shop_Id"),
            inverseJoinColumns = @JoinColumn(name="Tag_Id")
    )
    private List<Tags> shopTags;

    @ManyToMany
    @JoinTable(
            name="Reward_Shop_Links",
            joinColumns=@JoinColumn(name="Shop_Id"),
            inverseJoinColumns = @JoinColumn(name="Reward_Id")
    )
    private List<Rewards> rewardsList;
}
