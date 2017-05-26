package com.example.root.myvolleydemo.net;

import android.content.Context;
import android.content.pm.PackageManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.example.root.myvolleydemo.util.Constants;

import java.util.HashMap;
import java.util.Map;

public class ApiRequest<T> extends JsonRequest<T> implements Constants.HeaderKeys, Constants.AppConstants {

    //    private final ObjectMapper mMapper = new ObjectMapper();
    private Class<T> mClazz;
    private Map<String, String> mHeaders;
    private Response.Listener<T> mListener;
    private Context mContext;


    private ApiRequest(Builder builder) {
        super(builder.mMethod, builder.mUrl, builder.mRequestBody, builder.mListener, builder.mErrorListener);
        mClazz = builder.mClazz;
        mHeaders = builder.mHeaders;
        mListener = builder.mListener;
        mContext = builder.mContext;
        setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        try {
            mHeaders.put(Constants.HeaderKeys.API_VERSION_CODE, String.valueOf(mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode));
        } catch (PackageManager.NameNotFoundException e) {
            mHeaders.put(Constants.HeaderKeys.API_VERSION_CODE, "1");
        }
        mHeaders.put(X_CLIENT, PLATFORM);
        mHeaders.put(CONTENT_TYPE, CONTENT_FORMAT);
        mHeaders.put(CACHE_CONTROL, NO_CACHE);
        /*if (!LoginUtil.isUserLoggedIn(mContext) &&
                !TextUtils.isEmpty(PreferenceManager.getInstance(mContext).getGuestId()))
            mHeaders.put(X_SESSION, PreferenceManager.getInstance(mContext).getGuestId());*/
        return mHeaders;
    }

    @Override
    public String getBodyContentType() {
        return CONTENT_FORMAT;
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    public void deliverError(VolleyError error) {
        /*if (error.networkResponse != null)
            DialogUtil.showDebugToast(mContext, error.networkResponse.statusCode + error.getMessage());
        if (OAuthManager.getInstance().handleServiceResponse(mContext, error.networkResponse,
                this))*/
        super.deliverError(error);
    }


    public static class Builder<T> {
        private final Class<T> mClazz;
        private Map<String, String> mHeaders = new HashMap<>();
        private final Response.Listener<T> mListener;
        private final Context mContext;
        private final String mUrl;
        private final Response.ErrorListener mErrorListener;
        private final int mMethod;
        private String mRequestBody;


        public Builder(Context ctx, int method, Class<T> clazz, String url, Response.Listener<T> listener, Response.ErrorListener errorListener) {
            mContext = ctx;
            mMethod = method;
            mClazz = clazz;
            mUrl = url;
            mListener = listener;
            mErrorListener = errorListener;
        }

        public Builder setHeaders(Map<String, String> headers) {
            mHeaders = headers;
            return this;
        }

        public Builder setRequestBody(String requestBody) {
            mRequestBody = requestBody;
            return this;
        }

        public ApiRequest<T> build() {
            return new ApiRequest<>(this);
        }


    }

}