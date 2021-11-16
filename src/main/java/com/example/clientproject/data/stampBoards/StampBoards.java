package com.example.clientproject.data.stampBoards;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
