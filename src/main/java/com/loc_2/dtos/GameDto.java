package com.loc_2.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Rafal on 2016-05-11.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameDto {
    private Long gameId;
    private RawStatsDto stats;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public RawStatsDto getStats() {
        return stats;
    }

    public void setStats(RawStatsDto stats) {
        this.stats = stats;
    }
}
