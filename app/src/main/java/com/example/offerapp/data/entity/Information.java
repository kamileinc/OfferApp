package com.example.offerapp.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonPropertyOrder({"appName", "appId", "placementId", "virtualCurrency", "virtualCurrencySaleEnabled", "country", "language", "supportUrl"})
public class Information{
    @Expose
    @SerializedName("support_url")
    @JsonProperty("support_url")
    private String supportUrl;
    @Expose
    @SerializedName("language")
    private String language;
    @Expose
    @SerializedName("country")
    private String country;
    @Expose
    @SerializedName("virtual_currency_sale_enabled")
    @JsonProperty("virtual_currency_sale_enabled")
    private boolean virtualCurrencySaleEnabled;
    @Expose
    @SerializedName("virtual_currency")
    @JsonProperty("virtual_currency")
    private String virtualCurrency;
    @Expose
    @SerializedName("placement_id")
    @JsonProperty("placement_id")
    private String placementId;
    @Expose
    @SerializedName("appid")
    @JsonProperty("appid")
    private int appId;
    @Expose
    @SerializedName("app_name")
    @JsonProperty("app_name")
    private String appName;

    public String getSupportUrl() {
        return supportUrl;
    }

    public void setSupportUrl(String supportUrl) {
        this.supportUrl = supportUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isVirtualCurrencySaleEnabled() {
        return virtualCurrencySaleEnabled;
    }

    public void setVirtualCurrencySaleEnabled(boolean virtualCurrencySaleEnabled) {
        this.virtualCurrencySaleEnabled = virtualCurrencySaleEnabled;
    }

    public String getVirtualCurrency() {
        return virtualCurrency;
    }

    public void setVirtualCurrency(String virtualCurrency) {
        this.virtualCurrency = virtualCurrency;
    }

    public String getPlacementId() {
        return placementId;
    }

    public void setPlacementId(String placementId) {
        this.placementId = placementId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
