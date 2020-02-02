package com.tcrypto.controllers;

import com.tcrypto.dto.request.UserSignupDto;
import com.tcrypto.dto.response.RegistrationDtoResponse;
import com.tcrypto.models.AccessToken;
import com.tcrypto.models.User;
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
    public ResponseEntity<RegistrationDtoResponse> signUp(@Valid @RequestBody UserSignupDto userSignupDto, HttpServletRequest request) throws IOException {
        User user = userService.register(userSignupDto, request);
        AccessToken accessToken = user.getAccessToken();
        RegistrationDtoResponse response = new RegistrationDtoResponse(user.getId(), accessToken.getToken(), accessToken.getRefreshToken().getToken());
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
