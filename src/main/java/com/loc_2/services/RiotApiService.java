package com.loc_2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loc_2.daos.SummonerRepository;
import com.loc_2.daos.UserRepository;
import com.loc_2.dtos.RecentGamesDto;
import com.loc_2.dtos.SummonerDto;
import com.loc_2.entities.RawStatsSummary;
import com.loc_2.entities.Summoner;
import com.loc_2.entities.User;
import com.loc_2.exceptions.ApiKeyNotFoundException;
import com.loc_2.exceptions.RiotLimitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.util.Map;

/**
 * Created by Rafal on 2016-05-12.
 */
@Service
public class RiotApiService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SummonerRepository summonerRepository;

    private RestWrapper restWrapper = new RestWrapper();

    public void setApiKey(String username, String api_key) {
        userRepository.setApiKey(username, api_key);
    }

    public RawStatsSummary generateMySummaryStats(String username) throws ApiKeyNotFoundException, RiotLimitException, HttpClientErrorException {
        User user = userRepository.findByUsername(username);
        return generateSummaryStats(checkSummonerId(user.getSummoner(), username), username);
    }

    public RawStatsSummary getMyStats(String username) {
        User user = userRepository.findByUsername(username);
        RawStatsSummary statsSummary = user.getSummoner().getStatsSum();
        if (statsSummary == null)
            return generateMySummaryStats(username);
        return statsSummary;
    }

    public RawStatsSummary getSummonerStats(String summonerName, String username) {
        Summoner summoner = summonerRepository.findByUsername(summonerName);
        if (summoner == null)
            summoner = new Summoner(summonerName);
        summoner = checkSummonerId(summoner, username);
        return generateSummaryStats(summoner, username);
    }

    public SummonerDto getLastRefresh(String username) {
        Summoner summoner = userRepository.findByUsername(username).getSummoner();
        SummonerDto summonerDto = new SummonerDto();
        summonerDto.setLastRefresh(summoner.getLastRefresh());
        return summonerDto;
    }


    private String getApiKey(User user) throws ApiKeyNotFoundException {
        String apikey = user.getApikey();
        if (apikey == null) {
            throw new ApiKeyNotFoundException();
        }
        return apikey;
    }

    private Summoner checkSummonerId(Summoner summoner, String username) throws ApiKeyNotFoundException, RiotLimitException, HttpClientErrorException {
        User user = userRepository.findByUsername(username);
        Long id = summoner.getId();
        if (id == null) {
            Map<String, SummonerDto> summonerDto = restWrapper.getForObject("https://eune.api.pvp.net/api/lol/eune/v1.4/summoner/by-name/" + summoner.getName() + "?api_key=" + getApiKey(user), Map.class);
            ObjectMapper objectMapper = new ObjectMapper();
            summonerDto = objectMapper.convertValue(summonerDto, new TypeReference<Map<String, SummonerDto>>() {
            });
            summoner.setId(summonerDto.get(summoner.getName().replaceAll("\\s+", "").toLowerCase()).getId());
            summonerRepository.save(summoner);
        }
        return summoner;
    }

    private RawStatsSummary generateSummaryStats(Summoner summoner, String username) throws ApiKeyNotFoundException, RiotLimitException, HttpClientErrorException {
        RawStatsSummary rawStatsSummary = new RawStatsSummary();
        getRecentGames(summoner.getId(), username).getGames().forEach(gameDto -> rawStatsSummary.addGameStats(gameDto.getStats()));
        summoner.setStatsSum(rawStatsSummary);
        summoner.setLastRefresh(new Date());
        summonerRepository.save(summoner);
        return rawStatsSummary;
    }

    private RecentGamesDto getRecentGames(Long summonerId, String username) throws ApiKeyNotFoundException, RiotLimitException, HttpClientErrorException {
        User user = userRepository.findByUsername(username);
        return restWrapper.getForObject("https://eune.api.pvp.net/api/lol/eune/v1.3/game/by-summoner/" + summonerId + "/recent?api_key=" + getApiKey(user), RecentGamesDto.class);
    }
}
