package com.loc_2.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Rafal on 2016-05-11.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RawStatsDto {
    private int assists;
    private int gold;
    private int goldEarned;
    private int goldSpent;
    private int minionsKilled;
    private int turretsKilled;

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

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getMinionsKilled() {
        return minionsKilled;
    }

    public void setMinionsKilled(int minionsKilled) {
        this.minionsKilled = minionsKilled;
    }

    public int getTurretsKilled() {
        return turretsKilled;
    }

    public void setTurretsKilled(int turretsKilled) {
        this.turretsKilled = turretsKilled;
    }
}
