package com.example.offerapp.data.network;

import static com.example.offerapp.utilities.Constants.AD_ID;
import static com.example.offerapp.utilities.Constants.IP;
import static com.example.offerapp.utilities.Constants.LIMITED_TRACKING;
import static com.example.offerapp.utilities.Constants.LOCALE;
import static com.example.offerapp.utilities.Constants.PUB;

import com.example.offerapp.data.entity.OfferResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;

public class OfferRepository {

    private final OfferApiService offerApiService;

    @Inject
    public OfferRepository(OfferApiService offerApiService) {
        this.offerApiService = offerApiService;
    }

    public Single<Response<OfferResponse>> getOffers(
            int appId, int page, String timestamp, String userId, String hash) {
        return offerApiService.getOffers(appId, AD_ID, LIMITED_TRACKING, IP, LOCALE, page,
                PUB, timestamp, userId, hash);
    }
}
