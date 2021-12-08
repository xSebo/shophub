package com.example.clientproject.services;

import com.example.clientproject.data.userStampBoards.UserStampBoards;
import com.example.clientproject.data.userStampBoards.UserStampBoardsRepo;
import com.example.clientproject.data.users.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserStampBoardService {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    UserStampBoardsRepo userRepo;

    /**
     * Select user stamp position by userId and stampBoardId
     * @param userID, stampBoardID - the id of the user and stampBoard to search by
     * @return - an int of user stamp position
     */

    public int getUserStampPos(int userID, int stampBoardID){
        String query = "SELECT User_Stamp_Position FROM user_stamp_boards WHERE User_Id = " + userID + " AND Stamp_Board_Id = " + stampBoardID + ";";
        try{
            List<Map<String, Object>> rs = jdbc.queryForList(query);

            System.out.println((int) rs.get(0).get("User_Stamp_Position"));
            return (int) rs.get(0).get("User_Stamp_Position");
        }catch (Exception e){
            return 0;
        }

    }

    public void changeUserStampPosition(int userID, int incrementValue, int currentUserStampPos){
        int newStampPos = currentUserStampPos + incrementValue;
        String query = "UPDATE user_stamp_boards SET User_Stamp_Position = " + newStampPos + " WHERE User_Id = " + userID + ";";
        jdbc.execute(query);
    }
}
