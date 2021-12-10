package com.example.clientproject.services;

import com.example.clientproject.data.userStampBoards.UserStampBoards;
import com.example.clientproject.data.userStampBoards.UserStampBoardsRepo;
import com.example.clientproject.data.users.UsersRepo;
import com.example.clientproject.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserStampBoardService {

    JdbcTemplate jdbc;
    UserStampBoardsRepo userRepo;
    LoggingService loggingService;

    public UserStampBoardService(JdbcTemplate jdbc, UserStampBoardsRepo userRepo, LoggingService loggingService) {
        this.jdbc = jdbc;
        this.userRepo = userRepo;
        this.loggingService = loggingService;
    }

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

    public void changeUserStampPosition(int userID, int incrementValue, int currentUserStampPos, int stampBoardId, HttpSession session){
        int newStampPos = currentUserStampPos + incrementValue;
        String query = "UPDATE user_stamp_boards SET User_Stamp_Position = " + newStampPos + " WHERE User_Id = " + userID + " AND Stamp_Board_Id = " + stampBoardId + ";";
        jdbc.execute(query);
        // Log the change
        loggingService.logEvent(
                "UserStampBoard Updated",
                session,
                "UserStampBoard updated for StampBoard Id: " + stampBoardId +
                        " where User Id: " + userID +
                        " where field: User_Stamp_Position and value: " + newStampPos +
                        " in UserStampBoardService.changeUserStampPosition()"
        );
    }

    public void createStampRecord(int userID, int stampPosition, int stampBoardId, HttpSession session){
        String query = "INSERT INTO user_stamp_boards (User_Id, Stamp_Board_Id, User_Stamp_Position) VALUES ("+userID+", "+stampBoardId+", "+ stampPosition +");";
        jdbc.execute(query);
        // Log the change
        loggingService.logEvent(
                "UserStampBoard Inserted",
                session,
                "UserStampBoard Inserted where User Id: " + userID +
                        " StampBoard Id: " + stampBoardId +
                        " and Stamp Position: " + stampPosition +
                        " in UserStampBoardService.createStampRecord()"
        );
    }
}
