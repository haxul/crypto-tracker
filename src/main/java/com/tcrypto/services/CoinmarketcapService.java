package com.tcrypto.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcrypto.dto.response.Coinmarketcap.create.CoinCreateDtoResponse;
import com.tcrypto.dto.response.Coinmarketcap.create.MarketCapCoin;
import kong.unirest.Unirest;
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

    private String coinMarketcaprequest(String url) {
        return Unirest.get( BASE_URL + url)
                .header("Content-Type", "application/json")
                .header("X-CMC_PRO_API_KEY", token)
                .header("Accept", "*/*")
                .header("Connection", "keep-alive")
                .asString().getBody();
    }

    public int findPriceByCoinmarketcapId(int id) {
        String url = "v1/cryptocurrency/quotes/latest?id=" + id;
        String data = coinMarketcaprequest(url);
        Pattern pattern = Pattern.compile("(?<=\"price\":\\s)[\\d\\.]+");
        Matcher matcher = pattern.matcher(data);
        return matcher.find() ? (int) Double.parseDouble((matcher.group())) : 0;
    }

    public MarketCapCoin findCoinInCoinmarketcap(String symbol) throws IOException {
        String url = "v1/cryptocurrency/map?symbol=" + symbol;
        String response = coinMarketcaprequest(url);
        ObjectMapper mapper = new ObjectMapper();
        CoinCreateDtoResponse coinResponseData = mapper.readValue(response, CoinCreateDtoResponse.class);
        return coinResponseData.getData().size() != 0 ? coinResponseData.getData().get(0) : null;
    }
}
