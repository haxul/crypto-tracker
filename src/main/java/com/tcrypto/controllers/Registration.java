package com.tcrypto.controllers;

import com.tcrypto.dao.AccessTokenDao;
import com.tcrypto.dto.request.UserSignupDto;
import com.tcrypto.dto.response.DaData.DaDataDtoResponse;
import com.tcrypto.models.AccessToken;
import com.tcrypto.models.User;
import com.tcrypto.services.AccessTokenService;
import com.tcrypto.services.DaDataService;
import com.tcrypto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/signup")
public class Registration {

    private final UserService userService;

    @Autowired
    public Registration(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> signUp(@Valid @RequestBody UserSignupDto userSignupDto, HttpServletRequest request) throws IOException {
        User user = userService.register(userSignupDto, request);
        String message = "The User " + user.getId() + " is successfully created";
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }
}
