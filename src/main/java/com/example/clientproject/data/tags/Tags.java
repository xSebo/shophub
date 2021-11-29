package com.example.clientproject.data.tags;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Tags Entity
 * Contains Getters, Setters, and constructors thanks to Lombok
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tagId;
    private String tagName;

    /**
     *
     */
    public Tags(String tagName){
        this.tagName = tagName;
    }

    @ManyToMany(mappedBy="shopTags")
    private List<Shops> relatedShops;

    @ManyToMany(mappedBy="favouriteTags")
    private List<Users> relatedUsers;

}
