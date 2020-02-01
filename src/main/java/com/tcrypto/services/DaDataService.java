package com.tcrypto.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcrypto.dto.response.DaData.DaDataDtoResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DaDataService {

    private final String BASE_URL = "https://suggestions.dadata.ru";

    @Value("${dadata.access.token}")
    private String token;

    private String createRequest(String ip) throws IOException {
        return Unirest.get( BASE_URL + "/suggestions/api/4_1/rs/iplocate/address?ip=" + ip)
                .header("Accept", "application/json")
                .header("Authorization", "Token " + token)
                .header("Cache-Control", "no-cache")
                .header("Host", "suggestions.dadata.ru")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Cookie", "__ddg1=9F5OOlnGBK9ZAUvUWQnT")
                .header("Connection", "keep-alive")
                .header("cache-control", "no-cache")
                .asString().getBody();
    }

    public String defineCountry(String ip) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        DaDataDtoResponse response = mapper.readValue(createRequest(ip), DaDataDtoResponse.class);
        return response.getLocation() != null ? response.getLocation().getData().getCountry_iso_code() : "unknown";
    }
}
