package com.tali.websocketsample.models;

import com.google.gson.annotations.SerializedName;

public class DetailsDto extends BaseModel{
    @SerializedName("s")
    String symbol;
    @SerializedName("P")
    Float priceChange;
    @SerializedName("c")
    String lastPrice;

    public DetailsDto() {
    }

    public DetailsDto(String symbol, Float priceChange, String lastPrice) {
        this.symbol = symbol;
        this.priceChange = priceChange;
        this.lastPrice = lastPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Float getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(Float priceChange) {
        this.priceChange = priceChange;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }
}
