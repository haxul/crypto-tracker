package com.tcrypto.controllers;

import com.tcrypto.dao.CoinDao;
import com.tcrypto.dto.request.CoinCreateDto;
import com.tcrypto.dto.response.Coinmarketcap.create.MarketCapCoin;
import com.tcrypto.models.Coin;
import com.tcrypto.services.CoinmarketcapService;
import com.tcrypto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/coin")
public class Coinmarketcap {

    private final CoinmarketcapService coinmarketcapService;
    public Coinmarketcap(CoinmarketcapService coinmarketcapService) {
        this.coinmarketcapService = coinmarketcapService;
    }

    @GetMapping("/{token}/price")
    public ResponseEntity<String> findCoinPrice(@PathVariable String token, HttpServletRequest request) {
//      userService.checkAccessToken(request);
        int price = coinmarketcapService.findPriceByCoinmarketcapId(2);
        return price == 0 ?
                new ResponseEntity<>("not found", HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>("litecoin is " + price, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MarketCapCoin> createCoin(@Valid @RequestBody CoinCreateDto coinCreateDto) throws IOException {
        // userService.checkAccessToken(request);
        MarketCapCoin marketCapCoin = coinmarketcapService.findCoinInCoinmarketcap(coinCreateDto.getSymbol());
        return new ResponseEntity<>(marketCapCoin ,HttpStatus.OK);
    }
}
