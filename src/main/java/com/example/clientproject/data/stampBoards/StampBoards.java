package com.example.clientproject.data.stampBoards;

import com.example.clientproject.data.rewards.Rewards;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * StampBoards Entity
 * Contains Getters, Setters, and constructors thanks to Lombok
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StampBoards {
    @Id
    @GeneratedValue
    private long stampBoardId;
    private int stampBoardSize;
    private String stampBoardColour;
    private String stampBoardIcon;

    @OneToMany
    @JoinColumn(name="Stamp_Board_Id")
    private Set<Rewards> rewards;
}
