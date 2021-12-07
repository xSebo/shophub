package com.example.clientproject.data.userStampBoards;

import com.example.clientproject.data.stampBoards.StampBoards;
import com.example.clientproject.data.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * UserStampBoards Entity
 * Contains Getters, Setters, and constructors thanks to Lombok
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserStampBoards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userStampBoardId;
    private int userStampPosition;

    @ManyToOne
    @JoinColumn(name="Stamp_Board_Id", nullable=false)
    private StampBoards stampBoard;

    /*
    @ManyToOne
    @JoinColumn(name="User_Id", nullable=false)
    private Users user;

     */
}
