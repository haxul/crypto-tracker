package com.tcrypto.controllers;

import com.tcrypto.annotaitions.isCorrectSymbol;
import com.tcrypto.dao.CoinDao;
import com.tcrypto.dto.request.CoinCreateDto;
import com.tcrypto.dto.response.Coinmarketcap.create.MarketCapCoin;
import com.tcrypto.exceptions.CoinIsExistAlready;
import com.tcrypto.exceptions.CoinIsNoTFoundInCoinmarketCap;
import com.tcrypto.exceptions.CoinIsNotRegistered;
import com.tcrypto.models.Coin;
import com.tcrypto.services.CoinmarketcapService;
import com.tcrypto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.IOException;

@RestController
@RequestMapping("/coin")
@Validated
public class Coinmarketcap {

    private final CoinmarketcapService coinmarketcapService;
    private final CoinDao coinDao;

    public Coinmarketcap(CoinmarketcapService coinmarketcapService, CoinDao coinDao) {
        this.coinmarketcapService = coinmarketcapService;
        this.coinDao = coinDao;
    }

    @GetMapping("/{coinSymbol}/price")
    public ResponseEntity<String> findCoinPrice(@PathVariable @Size(max = 10) @Pattern(regexp = "^\\w+$") String coinSymbol, HttpServletRequest request) {
//      userService.checkAccessToken(request);
        Coin coin = coinDao.findCoinBySymbol(coinSymbol);
        if (coin == null) throw new CoinIsNotRegistered();
        int price = coinmarketcapService.findPriceByCoinmarketcapId(coin.getCoinmarketcapId());
        return new ResponseEntity<>("Current price is " + price, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Coin> createCoin(@Valid @RequestBody CoinCreateDto coinCreateDto) throws IOException {
        // userService.checkAccessToken(request);
        MarketCapCoin marketCapCoin = coinmarketcapService.findCoinInCoinmarketcap(coinCreateDto.getSymbol());
        if (marketCapCoin == null) throw new CoinIsNoTFoundInCoinmarketCap();
        if (coinDao.findCoinByCoinmarketcapId(marketCapCoin.getId()) != null) throw new CoinIsExistAlready();
        Coin coin = coinmarketcapService.createCoin(marketCapCoin);
        int coinCurrentPrice = coinmarketcapService.findPriceByCoinmarketcapId(coin.getCoinmarketcapId());
        coin.setPrice(coinCurrentPrice);
        coinDao.save(coin);
        return new ResponseEntity<>(coin, HttpStatus.OK);
    }
}
