package com.example.root.myvolleydemo.net;

public interface VolleyResponseListener<T> {

    void onSuccessResponse(T response);
    void onResponseError(String message);
}