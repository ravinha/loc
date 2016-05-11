package com.loc_2.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Rafal on 2016-05-11.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RawStatsDto {
    private int gold;
    private int goldEarned;
    private int goldSpent;

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGoldEarned() {
        return goldEarned;
    }

    public void setGoldEarned(int goldEarned) {
        this.goldEarned = goldEarned;
    }

    public int getGoldSpent() {
        return goldSpent;
    }

    public void setGoldSpent(int goldSpent) {
        this.goldSpent = goldSpent;
    }
}
