package com.loc_2.controllers;

import com.loc_2.dtos.ApiKeyDto;
import com.loc_2.dtos.SummonerDto;
import com.loc_2.entities.Comparison;
import com.loc_2.entities.RawStatsSummary;
import com.loc_2.services.RiotApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

/**
 * Created by Rafal on 2016-05-11.
 */
@Controller
@RequestMapping("/riot")
public class RiotController {

    @Autowired
    private RiotApiService riotService;

    @RequestMapping(value = "/refreshstats", method = RequestMethod.POST)
    public ResponseEntity<RawStatsSummary> refreshStats(Principal principal) {
        String username = principal.getName();
        RawStatsSummary rawStatsSummary = riotService.generateMySummaryStats(username);
        if (rawStatsSummary != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/getstats", method = RequestMethod.GET)
    public ResponseEntity<RawStatsSummary> getSummaryStats(Principal principal) {
        String username = principal.getName();
        RawStatsSummary rawStatsSummary = riotService.getMyStats(username);
        if (rawStatsSummary != null) {
            return new ResponseEntity<>(rawStatsSummary, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/getstats/{name}", method = RequestMethod.GET)
    public ResponseEntity<RawStatsSummary> getSummonerStats(Principal principal, @PathVariable("name") String summonerName) {
        String username = principal.getName();
        RawStatsSummary rawStatsSummary = riotService.getSummonerStats(summonerName, username);
        if (rawStatsSummary != null) {
            return new ResponseEntity<>(rawStatsSummary, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/setapikey", method = RequestMethod.POST)
    public ResponseEntity setApiKey(Principal principal, @RequestBody ApiKeyDto apikey) {
        String username = principal.getName();
        riotService.setApiKey(username, apikey.getKey());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/isapikeyset", method = RequestMethod.GET)
    public ResponseEntity isApiKeySet(Principal principal) {
        String username = principal.getName();
        riotService.isApiKeySet(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/getlastrefresh", method = RequestMethod.GET)
    public ResponseEntity<SummonerDto> getLastRefresh(Principal principal) {
        String username = principal.getName();
        return new ResponseEntity<>(riotService.getLastRefresh(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/compare/{summonerName}", method = RequestMethod.GET)
    public ResponseEntity<Comparison> compareMyself(Principal principal, @PathVariable("summonerName") String summonerName) {
        String username = principal.getName();
        Comparison comparison = riotService.compare(summonerName, username);
        if (comparison != null) {
            return new ResponseEntity<>(comparison, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getcompared", method = RequestMethod.GET)
    public ResponseEntity<List<Comparison>> getComparedToMe(Principal principal) {
        String username = principal.getName();
        List<Comparison> comparisons = riotService.getComparedToMe(username);
        return new ResponseEntity<>(comparisons, HttpStatus.OK);
    }

    @RequestMapping(value = "/setviewed", method = RequestMethod.POST)
    public ResponseEntity setViewed(Principal principal) {
        String username = principal.getName();
        riotService.viewComparisons(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
