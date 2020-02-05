package com.tcrypto.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    private int coinmarketcapId;

    @Column
    @NotNull
    @Size(max = 20)
    private String symbol;

    @Column
    @NotNull
    @Size(max = 200)
    private String name;

    @Column
    private int price;

    public Coin() {
    }

    public Coin(String symbol, String name, int coinmarketcapId) {
        this.name = name;
        this.symbol = symbol;
        this.coinmarketcapId = coinmarketcapId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
