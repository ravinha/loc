package com.loc_2.controllers;

import com.loc_2.daos.SummonerRepository;
import com.loc_2.daos.UserRepository;
import com.loc_2.entities.User;
import com.loc_2.exceptions.AccountExistsException;
import com.loc_2.services.RiotApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Rafal on 2016-05-10.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SummonerRepository summonerRepository;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> createAccount(@RequestBody User user) throws AccountExistsException {
        if(userRepository.findByUsername(user.getUsername())!=null)
            throw new AccountExistsException();
        user.setRole("user");
        User createdAccount = userRepository.save(user);
        if(createdAccount.getSummoner()==null){
            summonerRepository.save(user.getSummoner());
        }
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }
}
