package com.example.offerapp.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@JsonPropertyOrder({"title", "comment", "offerId", "dynamicPayoutEnabled", "teaser", "requiredActions", "detailedActionsText", "link","offerTypes", "payout","instructionSteps","timeToPayout","thumbnail","storeId","mobileAppPrimaryCategory"})
public class Offers {
    @Expose
    @SerializedName("mobile_app_primary_category")
    @JsonProperty("mobile_app_primary_category")
    private String mobileAppPrimaryCategory;
    @Expose
    @SerializedName("store_id")
    @JsonProperty("store_id")
    private String storeId;
    @Expose
    @SerializedName("thumbnail")
    private Thumbnail thumbnail;
    @Expose
    @SerializedName("time_to_payout")
    @JsonProperty("time_to_payout")
    private TimeToPayout timeToPayout;
    @Expose
    @SerializedName("instruction_steps")
    @JsonProperty("instruction_steps")
    private List<String> instructionSteps;
    @Expose
    @SerializedName("payout")
    private int payout;
    @Expose
    @SerializedName("offer_types")
    @JsonProperty("offer_types")
    private List<OfferTypes> offerTypes;
    @Expose
    @SerializedName("link")
    private String link;
    @Expose
    @SerializedName("detailed_actions_text")
    @JsonProperty("detailed_actions_text")
    private String detailedActionsText;
    @Expose
    @SerializedName("required_actions")
    @JsonProperty("required_actions")
    private String requiredActions;
    @Expose
    @SerializedName("teaser")
    private String teaser;
    @Expose
    @SerializedName("dynamic_payout_enabled")
    @JsonProperty("dynamic_payout_enabled")
    private boolean dynamicPayoutEnabled;
    @Expose
    @SerializedName("offer_id")
    @JsonProperty("offer_id")
    private int offerId;
    @Expose
    @SerializedName("comment")
    private String comment;
    @Expose
    @SerializedName("title")
    private String title;

    public String getMobileAppPrimaryCategory() {
        return mobileAppPrimaryCategory;
    }

    public void setMobileAppPrimaryCategory(String mobileAppPrimaryCategory) {
        this.mobileAppPrimaryCategory = mobileAppPrimaryCategory;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public TimeToPayout getTimeToPayout() {
        return timeToPayout;
    }

    public void setTimeToPayout(TimeToPayout timeToPayout) {
        this.timeToPayout = timeToPayout;
    }

    public List<String> getInstructionSteps() {
        return instructionSteps;
    }

    public void setInstructionSteps(List<String> instructionSteps) {
        this.instructionSteps = instructionSteps;
    }

    public int getPayout() {
        return payout;
    }

    public void setPayout(int payout) {
        this.payout = payout;
    }

    public List<OfferTypes> getOfferTypes() {
        return offerTypes;
    }

    public void setOfferTypes(List<OfferTypes> offerTypes) {
        this.offerTypes = offerTypes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDetailedActionsText() {
        return detailedActionsText;
    }

    public void setDetailedActionsText(String detailedActionsText) {
        this.detailedActionsText = detailedActionsText;
    }

    public String getRequiredActions() {
        return requiredActions;
    }

    public void setRequiredActions(String requiredActions) {
        this.requiredActions = requiredActions;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public boolean isDynamicPayoutEnabled() {
        return dynamicPayoutEnabled;
    }

    public void setDynamicPayoutEnabled(boolean dynamicPayoutEnabled) {
        this.dynamicPayoutEnabled = dynamicPayoutEnabled;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
