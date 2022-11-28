package com.example.offerapp.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonPropertyOrder({"offerTypeId", "readable"})
public class OfferTypes {
    @Expose
    @SerializedName("readable")
    private String readable;
    @Expose
    @SerializedName("offer_type_id")
    @JsonProperty("offer_type_id")
    private int offerTypeId;

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }

    public int getOfferTypeId() {
        return offerTypeId;
    }

    public void setOfferTypeId(int offerTypeId) {
        this.offerTypeId = offerTypeId;
    }
}
