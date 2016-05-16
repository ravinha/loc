package com.loc_2.entities;

import com.loc_2.dtos.RawStatsDto;

/**
 * Created by Rafal on 2016-05-12.
 */
public class RawStatsSummary {

    private int assists = 0;
    private int gold = 0;
    private int goldEarned = 0;
    private int goldSpent = 0;
    private int minionsKilled = 0;
    private int turretsKilled = 0;

    public RawStatsSummary() {
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

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

    public void addGameStats(RawStatsDto rawStatsDto){
        this.assists += rawStatsDto.getAssists();
        this.gold += rawStatsDto.getGold();
        this.goldEarned += rawStatsDto.getGoldEarned();
        this.goldSpent += rawStatsDto.getGoldSpent();
        this.minionsKilled += rawStatsDto.getMinionsKilled();
        this.turretsKilled += rawStatsDto.getTurretsKilled();
    }


}
