package com.tcrypto.controllers;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/binance")
public class Binance {

    @GetMapping("/{token}")
    public ResponseEntity<String> findToken(@PathVariable String token, HttpServletRequest request) {
        request.getHeader("Authorization");
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
