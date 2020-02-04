package com.tcrypto.controllers;

import com.tcrypto.services.CoinmarketcapService;
import com.tcrypto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/coinmarketcap")
public class Coinmarketcap {

    private final UserService userService;
    private final CoinmarketcapService coinmarketcapService;

    public Coinmarketcap(UserService userService, CoinmarketcapService coinmarketcapService) {
        this.coinmarketcapService = coinmarketcapService;
        this.userService = userService;
    }

    @GetMapping("/{token}")
    public ResponseEntity<String> findToken(@PathVariable String token, HttpServletRequest request) {
//        userService.checkAccessToken(request);
        String data = coinmarketcapService.request();
        Pattern pattern = Pattern.compile("(?<=\"price\":\\s)[\\d\\.]+");
        Matcher matcher = pattern.matcher(data);
        String result = matcher.find() ? matcher.group() : null;
        return result == null ?
                new ResponseEntity<>("not found", HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>("litecoin is " + result, HttpStatus.OK);
    }
}
