package com.loc_2.services;

import com.loc_2.daos.SummonerRepository;
import com.loc_2.daos.UserRepository;
import com.loc_2.entities.User;
import com.loc_2.exceptions.AccountExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by Rafal on 2016-05-23.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SummonerRepository summonerRepository;

    public User createAccount(User user) throws AccountExistsException {
        if (userRepository.findByUsername(user.getUsername()) != null)
            throw new AccountExistsException();
        user.setRole("user");
        User createdAccount = userRepository.save(user);
        if (createdAccount.getSummoner() == null) {
            summonerRepository.save(user.getSummoner());
        }
        return createdAccount;
    }
}
