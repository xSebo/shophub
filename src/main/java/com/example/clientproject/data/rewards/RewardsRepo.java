package com.example.clientproject.data.rewards;

import com.example.clientproject.data.stampBoards.StampBoards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for the "Rewards" Entity
 * Extends the JpaRepository library with the types of "Rewards" and "Long"
 */
public interface RewardsRepo extends JpaRepository<StampBoards, Long> {
    /**
     * Save method
     * @param rewards - the new object to save
     * @return - the object
     */
    Rewards save(Rewards rewards);

    /**
     * Find a Reward by the name
     * @param rewardId Id of reward to find
     * @return - Optional object containing the Reward found, if it's present
     */
    @Query("select r from Rewards r where r.rewardId = ?1")
    Optional<Rewards> findByRewardId(long rewardId);


    /**
     * Find a Reward by the name
     * @param rewardId Id of reward to find
     * @return - Optional Integer containing the reward, if it's present
     */
    @Query("select r.rewardStampLocation from Rewards r where r.rewardId = ?1")
    Optional<Integer> getRewardValueFromId(long rewardId);

}
