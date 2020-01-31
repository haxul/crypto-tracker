package com.tcrypto.services;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DaDataService {

    private final String BASE_URL = "https://suggestions.dadata.ru";

    public HttpResponse<JsonNode> defineCountry(String ip) throws IOException {
        HttpResponse<JsonNode> response = Unirest.get("https://suggestions.dadata.ru/suggestions/api/4_1/rs/iplocate/address?ip=46.226.227.20")
                .header("Accept", "application/json")
                .header("Authorization", "Token a844bb6b7bf427604f31b7606204371787a0fd75")
                .header("Cache-Control", "no-cache")
                .header("Host", "suggestions.dadata.ru")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Cookie", "__ddg1=9F5OOlnGBK9ZAUvUWQnT")
                .header("Connection", "keep-alive")
                .header("cache-control", "no-cache")
                .asJson();
        return response;
    }
}
