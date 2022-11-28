package com.example.offerapp.viewmodels;

import static com.example.offerapp.utilities.Constants.AD_ID;
import static com.example.offerapp.utilities.Constants.AD_ID_KEY;
import static com.example.offerapp.utilities.Constants.AND_SIGN;
import static com.example.offerapp.utilities.Constants.APPLICATION_ID;
import static com.example.offerapp.utilities.Constants.APPLICATION_ID_KEY;
import static com.example.offerapp.utilities.Constants.EQUAL_SIGN;
import static com.example.offerapp.utilities.Constants.IP;
import static com.example.offerapp.utilities.Constants.IP_KEY;
import static com.example.offerapp.utilities.Constants.LIMITED_TRACKING;
import static com.example.offerapp.utilities.Constants.LIMITED_TRACKING_KEY;
import static com.example.offerapp.utilities.Constants.LOCALE;
import static com.example.offerapp.utilities.Constants.LOCALE_KEY;
import static com.example.offerapp.utilities.Constants.PAGE_KEY;
import static com.example.offerapp.utilities.Constants.PUB;
import static com.example.offerapp.utilities.Constants.PUB_KEY;
import static com.example.offerapp.utilities.Constants.SIGNATURE_KEY;
import static com.example.offerapp.utilities.Constants.TIMESTAMP_KEY;
import static com.example.offerapp.utilities.Constants.TOKEN;
import static com.example.offerapp.utilities.Constants.USER_ID;
import static com.example.offerapp.utilities.Constants.USER_ID_KEY;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.example.offerapp.data.entity.OfferDto;
import com.example.offerapp.data.entity.OfferResponse;
import com.example.offerapp.data.network.OfferRepository;
import com.example.offerapp.paging.OfferPagingSource;
import com.example.offerapp.utilities.NetworkState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;
import retrofit2.Response;

public class OfferViewModel extends ViewModel {

    public Flowable<PagingData<OfferDto>> offerPagingDataFlowable;

    private LiveData<NetworkState> networkStateLiveData;

    static String securityToken;

    @ViewModelInject
    public OfferViewModel(OfferRepository offerRepository, @Assisted SavedStateHandle savedStateHandle) {

        int appId = savedStateHandle.get("appId");
        String userId = savedStateHandle.get("userId");
        securityToken = savedStateHandle.get("securityToken");
        initPaging(offerRepository, appId, userId, securityToken);
    }

    private void initPaging(OfferRepository offerRepository, int appId, String userId, String securityToken) {
        OfferPagingSource offerPagingSource = new OfferPagingSource(offerRepository, appId, userId, securityToken);
        networkStateLiveData =  offerPagingSource.getMutableLiveData();
        Pager<Integer, OfferDto> pager = new Pager(
                new PagingConfig(30,
                        30,
                        false,
                        30,
                        30 * 100),
                () -> offerPagingSource);

        offerPagingDataFlowable = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(offerPagingDataFlowable, coroutineScope);
    }

    public LiveData<NetworkState> getNetworkStateLiveData() {
        return networkStateLiveData;
    }

    public static String getConcatedString(int page, String timestamp) {
        LinkedHashMap<String, String> map = getQueryParametersMap(page, timestamp);

        StringBuilder concatedString = new StringBuilder();
        for (Map.Entry<String,String> entry : map.entrySet()) {
            if (entry.getKey() != null) {
                concatedString.append(entry.getKey()).append(EQUAL_SIGN)
                        .append(entry.getValue()).append(AND_SIGN);
            } else {
                concatedString.append(entry.getValue());
            }
        }

        return concatedString.toString();
    }

    public static LinkedHashMap<String, String> getQueryParametersMap(int page, String timestamp) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put(APPLICATION_ID_KEY, APPLICATION_ID);
        map.put(AD_ID_KEY, AD_ID);
        map.put(LIMITED_TRACKING_KEY, LIMITED_TRACKING);
        map.put(IP_KEY, IP);
        map.put(LOCALE_KEY, LOCALE);
        map.put(PAGE_KEY, String.valueOf(page));
        map.put(PUB_KEY, PUB);
        map.put(TIMESTAMP_KEY, timestamp);
        map.put(USER_ID_KEY, USER_ID);
        map.put(null, TOKEN);

        return map;
    }

    public static String getSHA1String(String string) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(string.getBytes(StandardCharsets.UTF_8));
            return String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean checkSignature(Response<OfferResponse> response) throws JsonProcessingException {
        String signature = response.headers().get(SIGNATURE_KEY);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(response.body());
        String signatureToCheck = OfferViewModel.getSHA1String(jsonString + securityToken);

        return Objects.equals(signature, signatureToCheck);
    }
}
