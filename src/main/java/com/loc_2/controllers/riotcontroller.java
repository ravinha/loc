package com.loc_2.controllers;

import com.loc_2.dtos.RecentGamesDto;
import com.loc_2.dtos.ApiKeyDto;
import com.loc_2.entities.RawStatsSummary;
import com.loc_2.services.RiotApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.security.Principal;

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
        RawStatsSummary rawStatsSummary = riotService.generateSummaryStats(username);
        if (rawStatsSummary != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/getstats", method = RequestMethod.GET)
    public ResponseEntity<RawStatsSummary> getSummaryStats(Principal principal) {
        String username = principal.getName();
        RawStatsSummary rawStatsSummary = riotService.getSummaryStats(username);
        if (rawStatsSummary != null) {
            return new ResponseEntity<>(rawStatsSummary, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/setapikey", method = RequestMethod.POST)
    public ResponseEntity<RecentGamesDto> setApiKey(Principal principal, @RequestBody ApiKeyDto apikey) {
        String username = principal.getName();
        riotService.setApiKey(username, apikey.getKey());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
