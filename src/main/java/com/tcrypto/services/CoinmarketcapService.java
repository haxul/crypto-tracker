package com.tcrypto.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcrypto.dto.response.DaData.DaDataDtoResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CoinmarketcapService {

    @Value("${coinmarketcap.access.token}")
    private String token;
    private final String BASE_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?";
    public String request() {
        return Unirest.get( BASE_URL + "id=2")
                .header("Content-Type", "application/json")
                .header("X-CMC_PRO_API_KEY", token)
                .header("Accept", "*/*")
                .header("Connection", "keep-alive")
                .asString().getBody();
    }
}
