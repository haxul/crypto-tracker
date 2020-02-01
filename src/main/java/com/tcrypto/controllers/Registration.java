package com.tcrypto.controllers;

import com.tcrypto.dto.request.UserSignupDto;
import com.tcrypto.dto.response.DaData.DaDataDtoResponse;
import com.tcrypto.models.User;
import com.tcrypto.services.DaDataService;
import com.tcrypto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/signup")
public class Registration {

    private final UserService userService;
    private final DaDataService daDataService;

    @Autowired
    public Registration(UserService userService, DaDataService daDataService) {
        this.userService = userService;
        this.daDataService = daDataService;
    }

    @PostMapping
    public ResponseEntity<String> signUp(@Valid @RequestBody UserSignupDto userSignupDto, HttpServletRequest request) throws IOException {
        User user = userService.register(userSignupDto, request);
        String message = "The User " + user.getId() + " is successfully created";
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }
}
