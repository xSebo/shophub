package com.example.clientproject.data.userStampBoards;

import com.example.clientproject.data.stampBoards.StampBoards;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserStampBoards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userStampBoardId;
    private int userStampBoardPosition;

    @ManyToOne
    @JoinColumn(name="Stamp_Board_Id", nullable=false)
    private StampBoards stampBoard;
}
