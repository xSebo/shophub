package com.example.clientproject.data.socials;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Socials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Social_Id", nullable = false)
    private Integer socialId;

    @Id
    @Column(name = "Shop_Id", nullable = false)
    private Integer shopId;

    @Column(name = "Social_Platform", length = 45)
    private String socialPlatform;

    @Column(name = "Social_Name", length = 45)
    private String socialName;


    
    public Socials(int aShopId, String aSocialPlatform, String aSocialName){
        this.shopId = aShopId;
        this.socialPlatform = aSocialPlatform;
        this.socialName = aSocialName;
    }

}
