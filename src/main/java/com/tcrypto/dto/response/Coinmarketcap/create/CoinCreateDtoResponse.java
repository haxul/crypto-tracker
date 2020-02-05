package com.tcrypto.dto.response.Coinmarketcap.create;

import java.util.ArrayList;

public class CoinCreateDtoResponse {
    Status status;
    ArrayList<MarketCapCoin> data = new ArrayList<>();


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status statusObject) {
        this.status = statusObject;
    }

    public ArrayList<MarketCapCoin> getData() {
        return data;
    }

    public void setData(ArrayList<MarketCapCoin> data) {
        this.data = data;
    }
}
