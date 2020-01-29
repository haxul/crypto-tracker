package com.tcrypto.controllers;

import com.tcrypto.dao.UserDao;
import com.tcrypto.dto.request.UserSignupDto;
import com.tcrypto.models.User;
import com.tcrypto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/signup")
public class Registration {

    private UserService userService;

    public Registration(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> signUp(@Valid @RequestBody UserSignupDto userSignupDto) {
        User user = userService.createUser(userSignupDto);
        String message = "The User " + user.getId() + " is successfully created";
        user = null;
        user.getId();
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }
}
