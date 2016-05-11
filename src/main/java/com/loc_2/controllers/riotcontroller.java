package com.loc_2.controllers;

import com.loc_2.dtos.RecentGamesDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Rafal on 2016-05-11.
 */
@Controller
@RequestMapping("/riot")
public class RiotController {

    @RequestMapping(value = "/recentgames/{key}/{summonerId}", method = RequestMethod.GET)
    public ResponseEntity<RecentGamesDto> getGames(@PathVariable("key") String key, @PathVariable("summonerId") Long summonerId) {
        RestTemplate restTemplate = new RestTemplate();
        RecentGamesDto recentGamesDto;
        try {
            recentGamesDto = restTemplate.getForObject("https://eune.api.pvp.net/api/lol/eune/v1.3/game/by-summoner/" + summonerId + "/recent?api_key=" + key, RecentGamesDto.class);
            if (recentGamesDto != null) {
                return new ResponseEntity<>(recentGamesDto, HttpStatus.OK);
            }
        } catch (RestClientException rce) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
