package com.tcrypto.controllers;

import com.tcrypto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/binance")
public class Binance {

    private final UserService userService;

    public Binance(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{token}")
    public ResponseEntity<String> findToken(@PathVariable String token, HttpServletRequest request) {
        userService.checkAccessToken(request);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
