package com.tcrypto.controllers;

import com.tcrypto.dao.CoinDao;
import com.tcrypto.dao.UserDao;
import com.tcrypto.models.Coin;
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
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @PostMapping("/coin/{symbol}")
    public ResponseEntity<String> addCoin(HttpServletRequest request,
                                          @PathVariable @Size(max = 10) @Pattern(regexp = "^\\w+$") String symbol) {
        User user = userService.checkAccessToken(request);
        userService.toggleCoin(user, symbol, CoinOptions.ADD);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/coin/{symbol}")
    public ResponseEntity<String> removeCoin(HttpServletRequest request,
                                             @PathVariable @Size(max = 10) @Pattern(regexp = "^\\w+$") String symbol) {
        User user = userService.checkAccessToken(request);
        userService.toggleCoin(user, symbol, CoinOptions.REMOVE);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/coin")
    public ResponseEntity<Set<Coin>> getUserCoins(HttpServletRequest request,
                                                  @RequestParam long skip,
                                                  @RequestParam long limit) {
        User user = userService.checkAccessToken(request);
        Set<Coin> coins = user.getCoins()
                .stream()
                .sorted(Comparator.comparingInt(Coin::getId))
                .skip(skip)
                .limit(limit)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return new ResponseEntity<>(coins, HttpStatus.OK);

    }
}
