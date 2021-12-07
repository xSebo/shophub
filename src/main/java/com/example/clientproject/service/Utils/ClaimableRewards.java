package com.example.clientproject.service.Utils;

import com.example.clientproject.data.rewards.Rewards;
import com.example.clientproject.data.userStampBoards.UserStampBoards;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClaimableRewards {

    public int totalClaimableRewards(UserStampBoards u){
        int count = 0;
        for(Rewards r:u.getStampBoard().getRewards()){
            if(u.getUserStampPosition() >= r.getRewardStampLocation()){
                count++;
            }
        }
        return count;
    }

    public int nextReward(UserStampBoards u){
        List<Rewards> r = u.getStampBoard().getRewards();
        if (r.size() != 0) {
            for (int i = 1; i < u.getStampBoard().getRewards().size() - 1; i++) {
                if ((r.get(i).getRewardStampLocation() >= u.getUserStampPosition()) &&
                        (r.get(i - 1).getRewardStampLocation() <= u.getUserStampPosition())) {

                    return r.get(i).getRewardStampLocation();
                }
            }
        }
        return 0;
    }

}
