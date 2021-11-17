package com.example.clientproject.data.rewards;

import com.example.clientproject.data.stampBoards.StampBoards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RewardsRepo extends JpaRepository<StampBoards, Long> {
    List<StampBoards> findAll();

    Rewards save(Rewards rewards);

    @Query("select r from Rewards r where r.rewardId = ?1")
    Optional<Rewards> findByRewardName(String rewardName);
}
