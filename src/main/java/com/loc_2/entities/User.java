package com.loc_2.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Rafal on 2016-03-20.
 */
@Document(collection="users")
public class User {

    @Id
    private String username;
    private String apikey;
    private Long summonerId;
    private String password;
    private String role;
    private RawStatsSummary statsSum;

    public User() {
    }

    public User(String username,
                String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public RawStatsSummary getStatsSum() {
        return statsSum;
    }

    public void setStatsSum(RawStatsSummary statsSum) {
        this.statsSum = statsSum;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public Long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(Long summonerId) {
        this.summonerId = summonerId;
    }
}
