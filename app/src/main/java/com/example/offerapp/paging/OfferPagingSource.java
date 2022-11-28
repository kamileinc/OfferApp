package com.example.offerapp.paging;

import static com.example.offerapp.utilities.Constants.HTTP_OK;
import static com.example.offerapp.viewmodels.OfferViewModel.checkSignature;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagingSource;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.example.offerapp.data.entity.OfferDto;
import com.example.offerapp.data.entity.OfferResponse;
import com.example.offerapp.data.network.OfferRepository;
import com.example.offerapp.utilities.NetworkState;
import com.example.offerapp.viewmodels.OfferViewModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OfferPagingSource extends RxPagingSource<Integer, OfferDto> {
    int appId;
    String userId;
    String token;
    private final MutableLiveData<NetworkState> networkState;
    OfferRepository offerRepository;

    public OfferPagingSource(OfferRepository offerRepository, int appId, String userId, String token) {
        this.offerRepository = offerRepository;
        this.appId = appId;
        this.userId = userId;
        this.token = token;

        networkState = new MutableLiveData<>();
    }

    @NotNull
    @Override
    public Single<LoadResult<Integer, OfferDto>> loadSingle(@NotNull PagingSource.LoadParams<Integer> loadParams) {
        networkState.postValue(NetworkState.LOADING);
        int page = loadParams.getKey() != null ? loadParams.getKey() : 1;
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String hash = OfferViewModel.getSHA1String(OfferViewModel.getConcatedString(page, timestamp));
        return offerRepository.getOffers(appId, page, timestamp, userId, hash)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response-> {
                            if (Objects.equals(response.body().getCode(), HTTP_OK)) {
                                if (!checkSignature(response)) {
                                    networkState.postValue(NetworkState.INVALID_SIGNATURE);
                                    throw new Exception();
                                }
                            }
                        }
                )
                .map(retrofit2.Response::body)
                .map(OfferResponse::getOffers)
                .map(offers -> {
                    ModelMapper modelMapper = new ModelMapper();
                    List<OfferDto> offerDtos = offers
                            .stream()
                            .map(offer -> modelMapper.map(offer, OfferDto.class))
                            .collect(Collectors.toList());
                    return toLoadResult(offerDtos, page);
                }
                )
                .doOnError(e -> {
                    networkState.postValue(
                            new NetworkState(NetworkState.Status.FAILED, e.getMessage()));
                })
                .onErrorReturn(LoadResult.Error::new);
    }

    private LoadResult<Integer, OfferDto> toLoadResult(List<OfferDto> offers, int page) {
        networkState.postValue(NetworkState.SUCCESS);
        return new LoadResult.Page(offers, page == 1 ? null : page - 1, page + 1);
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NotNull PagingState<Integer, OfferDto> pagingState) {
        return null;
    }

    public MutableLiveData<NetworkState> getMutableLiveData() {
        return networkState;
    }
}
