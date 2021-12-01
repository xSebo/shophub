package com.example.clientproject.service.Utils;

import com.example.clientproject.data.rewards.Rewards;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ThymeMath {
    public Integer ceil(int a, int b) {
        return (int) (Math.ceil(a/b) + 1);
    }

    public List<Integer> reduceToLocs(List<Rewards> rewardsList){
        List<Integer> rewardLocs = new ArrayList<>();
        for(Rewards r : rewardsList){
            rewardLocs.add(r.getRewardStampLocation());
        }
        return rewardLocs;
    }
}
