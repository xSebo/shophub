package com.example.clientproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetStampBoardIdFromRewardId {
    JdbcTemplate jdbc;

    public GetStampBoardIdFromRewardId(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * @param rewardId the rewardId of the stampBoardId you want to retrieve
     */
    public int getStampBoardId(Integer rewardId){
        String query = "SELECT Stamp_Board_Id FROM rewards WHERE Reward_Id = " + rewardId + ";";
        try{
            List<Map<String, Object>> rs = jdbc.queryForList(query);

            System.out.println((int) rs.get(0).get("Stamp_Board_Id"));
            return (int) rs.get(0).get("Stamp_Board_Id");
        }catch (Exception e){
            return 0;
        }
    }
}
