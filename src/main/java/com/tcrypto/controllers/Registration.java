package com.tcrypto.controllers;

import com.tcrypto.dto.request.UserSignupDto;
import com.tcrypto.models.User;
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

    @PostMapping
    public ResponseEntity<UserSignupDto> signUp(@Valid @RequestBody UserSignupDto userSignupDto) {
        return new ResponseEntity<UserSignupDto>(userSignupDto,HttpStatus.CREATED);
    }
}
