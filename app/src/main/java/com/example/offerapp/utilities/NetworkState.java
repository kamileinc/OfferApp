package com.example.offerapp.utilities;

import static com.example.offerapp.utilities.Constants.INVALID_SIGNATURE_MESSAGE;

public class NetworkState {
    private final Status status;
    private final String message;

    public static final NetworkState SUCCESS;
    public static final NetworkState LOADING;
    public static final NetworkState INVALID_SIGNATURE;

    public NetworkState(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    static {
        SUCCESS = new NetworkState(Status.SUCCESS,null);
        LOADING = new NetworkState(Status.LOADING,null);
        INVALID_SIGNATURE = new NetworkState(Status.INVALID_SIGNATURE, INVALID_SIGNATURE_MESSAGE);
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public enum Status{
        LOADING,
        SUCCESS,
        FAILED,
        INVALID_SIGNATURE
    }
}
