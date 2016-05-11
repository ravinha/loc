package com.loc_2.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

/**
 * Created by Rafal on 2016-05-11.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecentGamesDto {
    private Set<GameDto> games;
    private Long summonerId;

    public Set<GameDto> getGames() {
        return games;
    }

    public void setGames(Set<GameDto> games) {
        this.games = games;
    }

    public Long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(Long summonerId) {
        this.summonerId = summonerId;
    }
}
