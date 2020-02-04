package com.tcrypto.dto.request;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CoinCreateDto {

    @NotNull
    private int coinmarketcapId;

    @NotNull
    @Size(max = 20)
    private String symbol;

    @NotNull
    @Size(max = 200)
    private String name;

    public int getCoinmarketcapId() {
        return coinmarketcapId;
    }

    public void setCoinmarketcapId(int coinmarketcapId) {
        this.coinmarketcapId = coinmarketcapId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
