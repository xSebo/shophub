package com.example.clientproject.data.socials;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Socials{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Social_Id", nullable = false)
    private Integer socialId;

    @ManyToOne
    @JoinColumn(name="Shop_Id", nullable = false)
    private Shops shop;

    @Column(name = "Social_Platform", length = 45)
    private String socialPlatform;

    @Column(name = "Social_Name", length = 45)
    private String socialName;

    public Socials(Shops aShop, String aSocialPlatform, String aSocialName){
        shop = aShop;
        socialPlatform = aSocialPlatform;
        socialName = aSocialName;
    }

}
