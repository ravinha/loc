package com.loc_2.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Rafal on 2016-05-21.
 */
@Document(collection = "summoners")
public class Summoner {
    @Id
    private String name;
    private Long id;
    private RawStatsSummary statsSum;
    private Date lastRefresh;

    public Summoner() {
    }

    public Summoner(String name) {
        this.name = name;
    }

    public RawStatsSummary getStatsSum() {
        return statsSum;
    }

    public void setStatsSum(RawStatsSummary statsSum) {
        this.statsSum = statsSum;
    }

    public Date getLastRefresh() {
        return lastRefresh;
    }

    public void setLastRefresh(Date lastRefresh) {
        this.lastRefresh = lastRefresh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Summoner{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", statsSum=" + statsSum +
                ", lastRefresh=" + lastRefresh +
                '}';
    }
}
