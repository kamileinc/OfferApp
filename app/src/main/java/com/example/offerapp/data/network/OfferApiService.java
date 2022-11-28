package com.example.offerapp.data.network;

import static com.example.offerapp.utilities.Constants.AD_ID_KEY;
import static com.example.offerapp.utilities.Constants.APPLICATION_ID_KEY;
import static com.example.offerapp.utilities.Constants.HASH_KEY;
import static com.example.offerapp.utilities.Constants.IP_KEY;
import static com.example.offerapp.utilities.Constants.LIMITED_TRACKING_KEY;
import static com.example.offerapp.utilities.Constants.LOCALE_KEY;
import static com.example.offerapp.utilities.Constants.PAGE_KEY;
import static com.example.offerapp.utilities.Constants.PUB_KEY;
import static com.example.offerapp.utilities.Constants.TIMESTAMP_KEY;
import static com.example.offerapp.utilities.Constants.USER_ID_KEY;

import com.example.offerapp.data.entity.OfferResponse;
import com.example.offerapp.utilities.Constants;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OfferApiService {

    @GET(Constants.END_POINT)
    Single<Response<OfferResponse>> getOffers(
            @Query(APPLICATION_ID_KEY) int appId,
            @Query(AD_ID_KEY) String adId,
            @Query(LIMITED_TRACKING_KEY) String limitedTracking,
            @Query(IP_KEY) String ip,
            @Query(LOCALE_KEY) String locale,
            @Query(PAGE_KEY) int page,
            @Query(PUB_KEY) String pub,
            @Query(TIMESTAMP_KEY) String timestamp,
            @Query(USER_ID_KEY) String uId,
            @Query(HASH_KEY) String hashKey
    );
}
