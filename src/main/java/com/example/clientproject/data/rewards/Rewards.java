package com.example.clientproject.data.rewards;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.stampBoards.StampBoards;
import com.example.clientproject.data.twoFactorMethods.TwoFactorMethods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Rewards Entity
 * Contains Getters, Setters, and constructors thanks to Lombok
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rewards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rewardId;
    private String rewardName;
    private int rewardStampLocation;

    public Rewards(String aRewardName, int rewardLocation){
        rewardName = aRewardName;
        rewardStampLocation = rewardLocation;
    }

    @ManyToOne
    @JoinColumn(name="Stamp_Board_Id", nullable=false)
    private StampBoards stampBoards;
}
