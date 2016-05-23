package com.loc_2.controllers;

import com.loc_2.entities.User;
import com.loc_2.exceptions.AccountExistsException;
import com.loc_2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> createAccount(@RequestBody User user) throws AccountExistsException {
        User createdAccount = userService.createAccount(user);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }
}
