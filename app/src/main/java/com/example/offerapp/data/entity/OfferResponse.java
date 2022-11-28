package com.example.offerapp.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@JsonPropertyOrder({"code", "message", "count", "pages", "information", "offers"})
public class OfferResponse  {
    @JsonProperty("offers")
    @Expose
    @SerializedName("offers")
    private List<Offers> offers;
    @JsonProperty("information")
    @Expose
    @SerializedName("information")
    private Information information;
    @JsonProperty("pages")
    @Expose
    @SerializedName("pages")
    private int pages;
    @JsonProperty("count")
    @Expose
    @SerializedName("count")
    private int count;
    @JsonProperty("message")
    @Expose
    @SerializedName("message")
    private String message;
    @JsonProperty("code")
    @Expose
    @SerializedName("code")
    private String code;

    public List<Offers> getOffers() {
        return offers;
    }

    public void setOffers(List<Offers> offers) {
        this.offers = offers;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
