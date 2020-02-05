package com.tcrypto.dto.response.Coinmarketcap.create;

public class MarketCapCoin {

    private int id;
    private String name;
    private String symbol;
    private String slug;
    private float is_active;
    private float rank;
    private String first_historical_data;
    private String last_historical_data;
    private String platform = null;


    // Getter Methods

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSlug() {
        return slug;
    }

    public float getIs_active() {
        return is_active;
    }

    public float getRank() {
        return rank;
    }

    public String getFirst_historical_data() {
        return first_historical_data;
    }

    public String getLast_historical_data() {
        return last_historical_data;
    }

    public String getPlatform() {
        return platform;
    }

    // Setter Methods

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setIs_active(float is_active) {
        this.is_active = is_active;
    }

    public void setRank(float rank) {
        this.rank = rank;
    }

    public void setFirst_historical_data(String first_historical_data) {
        this.first_historical_data = first_historical_data;
    }

    public void setLast_historical_data(String last_historical_data) {
        this.last_historical_data = last_historical_data;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}