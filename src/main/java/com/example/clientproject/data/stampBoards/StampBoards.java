package com.example.clientproject.data.stampBoards;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
