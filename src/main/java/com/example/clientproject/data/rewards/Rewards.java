package com.example.clientproject.data.rewards;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.stampBoards.StampBoards;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name="Stamp_Board_Id", nullable=false)
    private StampBoards stampBoard;

    @ManyToMany(mappedBy = "rewardsList")
    private List<Shops> shopsList;
}
