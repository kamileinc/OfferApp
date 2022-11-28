package com.example.offerapp.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonPropertyOrder({"lowRes", "hiRes"})
public class Thumbnail {
    @Expose
    @SerializedName("hires")
    @JsonProperty("hires")
    private String hiRes;
    @Expose
    @SerializedName("lowres")
    @JsonProperty("lowres")
    private String lowRes;

    public String getHiRes() {
        return hiRes;
    }

    public void setHiRes(String hiRes) {
        this.hiRes = hiRes;
    }

    public String getLowRes() {
        return lowRes;
    }

    public void setLowRes(String lowRes) {
        this.lowRes = lowRes;
    }
}
