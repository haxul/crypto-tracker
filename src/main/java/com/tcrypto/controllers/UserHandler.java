package com.tcrypto.controllers;

import com.tcrypto.dao.CoinDao;
import com.tcrypto.dao.UserDao;
import com.tcrypto.models.User;
import com.tcrypto.services.CoinOptions;
import com.tcrypto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/user")
@Validated
public class UserHandler {

    private final UserService userService;
    private final CoinDao coinDao;
    private final UserDao userDao;

    @Autowired
    public UserHandler(UserService userService, CoinDao coinDao, UserDao userDao) {
        this.userService = userService;
        this.coinDao = coinDao;
        this.userDao = userDao;
    }

    @PostMapping("/token/{symbol}")
    public ResponseEntity<String> addCoin(HttpServletRequest request, @PathVariable @Size(max = 10) @Pattern(regexp = "^\\w+$") String symbol) {
        User user = userService.checkAccessToken(request);
        userService.toggleCoin(user, symbol, CoinOptions.ADD);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/token/{symbol}")
    public ResponseEntity<String> removeCoin(HttpServletRequest request, @PathVariable @Size(max = 10) @Pattern(regexp = "^\\w+$") String symbol) {
        User user = userService.checkAccessToken(request);
        userService.toggleCoin(user, symbol, CoinOptions.REMOVE);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
