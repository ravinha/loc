package com.loc_2.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Rafal on 2016-05-27.
 */
@Document(collection = "comparisons")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comparison {
    @Id
    private String id;
    @DBRef
    private User comparer;
    @DBRef
    private Summoner comparee;
    private RawStatsSummary comparersStats;
    private RawStatsSummary compareeStats;
    private Date date;
    private boolean isViewed = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getComparer() {
        return comparer;
    }

    public void setComparer(User comparer) {
        this.comparer = comparer;
    }

    public Summoner getComparee() {
        return comparee;
    }

    public void setComparee(Summoner comparee) {
        this.comparee = comparee;
    }

    public RawStatsSummary getComparersStats() {
        return comparersStats;
    }

    public void setComparersStats(RawStatsSummary comparersStats) {
        this.comparersStats = comparersStats;
    }

    public RawStatsSummary getCompareeStats() {
        return compareeStats;
    }

    public void setCompareeStats(RawStatsSummary compareeStats) {
        this.compareeStats = compareeStats;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isViewed() {
        return isViewed;
    }

    public void setViewed(boolean viewed) {
        isViewed = viewed;
    }
}
