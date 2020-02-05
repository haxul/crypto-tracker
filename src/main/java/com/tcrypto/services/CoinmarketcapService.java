package com.tcrypto.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcrypto.dao.CoinDao;
import com.tcrypto.dto.response.Coinmarketcap.create.CoinCreateDtoResponse;
import com.tcrypto.dto.response.Coinmarketcap.create.MarketCapCoin;
import com.tcrypto.models.Coin;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CoinmarketcapService {

    @Value("${coinmarketcap.access.token}")
    private String token;
    private final String BASE_URL = "https://pro-api.coinmarketcap.com/";

    private final CoinDao coinDao;

    @Autowired
    public CoinmarketcapService(CoinDao coinDao) {
        this.coinDao = coinDao;
    }


    private String coinMarketcaprequest(final String url) {
        return Unirest.get( BASE_URL + url)
                .header("Content-Type", "application/json")
                .header("X-CMC_PRO_API_KEY", token)
                .header("Accept", "*/*")
                .header("Connection", "keep-alive")
                .asString().getBody();
    }

    public int findPriceByCoinmarketcapId(final int id) {
        String url = "v1/cryptocurrency/quotes/latest?id=" + id;
        String data = coinMarketcaprequest(url);
        Pattern pattern = Pattern.compile("(?<=\"price\":\\s)[\\d\\.]+");
        Matcher matcher = pattern.matcher(data);
        return matcher.find() ? (int) Double.parseDouble((matcher.group())) : 0;
    }

    public MarketCapCoin findCoinInCoinmarketcap(final String symbol) throws IOException {
        String url = "v1/cryptocurrency/map?symbol=" + symbol;
        String response = coinMarketcaprequest(url);
        ObjectMapper mapper = new ObjectMapper();
        CoinCreateDtoResponse coinResponseData = mapper.readValue(response, CoinCreateDtoResponse.class);
        return coinResponseData.getData().size() != 0 ? coinResponseData.getData().get(0) : null;
    }

    public Coin createCoin(final MarketCapCoin marketCapCoin) {
        String symbol = marketCapCoin.getSymbol();
        String name = marketCapCoin.getName();
        int marketcapId = marketCapCoin.getId();
        Coin coin = new Coin(symbol, name, marketcapId);
        coinDao.save(coin);
        return coin;
    }

    public void updateCoinPrice(Coin coin) {
        int price = findPriceByCoinmarketcapId(coin.getCoinmarketcapId());
        if (price != 0) {
            coin.setPrice(price);
            coinDao.save(coin);
        }
    }
}
