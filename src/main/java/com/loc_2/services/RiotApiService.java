package com.loc_2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loc_2.daos.UserRepository;
import com.loc_2.dtos.RecentGamesDto;
import com.loc_2.dtos.SummonerDto;
import com.loc_2.entities.RawStatsSummary;
import com.loc_2.entities.User;
import com.loc_2.exceptions.ApiKeyNotFoundException;
import com.loc_2.exceptions.RiotLimitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

/**
 * Created by Rafal on 2016-05-12.
 */
@Service
public class RiotApiService {

    @Autowired
    private UserRepository userRepository;
    private RestWrapper restWrapper = new RestWrapper();

    public void setApiKey(String username, String api_key) {
        userRepository.setApiKey(username, api_key);
    }

    public Long getSummonerId(User user) throws ApiKeyNotFoundException, RiotLimitException, HttpClientErrorException {
        Long id = user.getSummonerId();
        if (id == null) {
            Map<String, SummonerDto> summoner = restWrapper.getForObject("https://eune.api.pvp.net/api/lol/eune/v1.4/summoner/by-name/" + user.getUsername() + "?api_key=" + getApiKey(user), Map.class);
            ObjectMapper objectMapper = new ObjectMapper();
            summoner = objectMapper.convertValue(summoner, new TypeReference<Map<String, SummonerDto>>() {
            });
            user.setSummonerId(summoner.get(user.getUsername().replaceAll("\\s+","").toLowerCase()).getId());
            userRepository.save(user);
        }
        return user.getSummonerId();
    }

    public RawStatsSummary generateSummaryStats(String username) throws ApiKeyNotFoundException, RiotLimitException, HttpClientErrorException {
        RawStatsSummary rawStatsSummary = new RawStatsSummary();
        getRecentGames(username).getGames().forEach(gameDto -> rawStatsSummary.addGameStats(gameDto.getStats()));
        User user = userRepository.findByUsername(username);
        user.setStatsSum(rawStatsSummary);
        userRepository.save(user);
        return rawStatsSummary;
    }


    private String getApiKey(User user) throws ApiKeyNotFoundException {
        String apikey = user.getApikey();
        if (apikey == null) {
            throw new ApiKeyNotFoundException();
        }
        return apikey;
    }

    private RecentGamesDto getRecentGames(String username) throws ApiKeyNotFoundException, RiotLimitException, HttpClientErrorException {
        User user = userRepository.findByUsername(username);
        return restWrapper.getForObject("https://eune.api.pvp.net/api/lol/eune/v1.3/game/by-summoner/" + getSummonerId(user) + "/recent?api_key=" + getApiKey(user), RecentGamesDto.class);
    }

    public RawStatsSummary getSummaryStats(String username) {
        User user = userRepository.findByUsername(username);
        RawStatsSummary statsSummary = user.getStatsSum();
        if (statsSummary == null)
            return generateSummaryStats(username);
        return statsSummary;
    }
}
