package com.example.offerapp.data.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonPropertyOrder({"amount", "readable"})
public class TimeToPayout {
    @Expose
    @SerializedName("readable")
    private String readable;
    @Expose
    @SerializedName("amount")
    private int amount;

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
