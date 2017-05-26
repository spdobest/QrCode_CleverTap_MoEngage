package com.example.root.myvolleydemo.net;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.root.myvolleydemo.util.ObjectUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class CustomVolleyPostRequestWithTextPlain extends StringRequest   {

    private static final String TAG = CustomVolleyPostRequestWithTextPlain.class.getSimpleName();
    private String jsonBody;
    private String url;

    public CustomVolleyPostRequestWithTextPlain(int method,
                                                String url,
                                                String jsonBody,
                                                Response.Listener<String> listener,
                                                Response.ErrorListener errorListener) {

        super(method, url, listener, errorListener);
        this.jsonBody = jsonBody;
        this.url = url;
    }

    private String addLoggingParametersToRequest(String jsonBody, String url) {

        if (!TextUtils.isEmpty(jsonBody) && isValidJson(jsonBody) ) {

            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(jsonBody);
                jsonObject.put("osVersion", Build.VERSION.RELEASE);
                jsonObject.put("osType", "Android");
                jsonObject.put("deviceID", /*ApplicationUtils.getDeviceId()*/"ikdjaslkdaslkjdl");
            } catch (JSONException e) {
               e.printStackTrace();
            }

            if (ObjectUtils.isNotNull(jsonObject)) {
                return jsonObject.toString();
            } else {
                return jsonBody;
            }
        } else {
            return jsonBody;
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/plain");
        return headers;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {

        byte[] body = new byte[0];
        try {
            String newJsonBody = addLoggingParametersToRequest(jsonBody, url);
            Log.i(TAG, "CustomVolleyPostRequestWithTextPlain: calling : " + url + " " + newJsonBody);
            body = newJsonBody.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Unable to gets bytes from JSON", e.fillInStackTrace());
        }
        return body;
    }

    @Override
    public String getBodyContentType() {
        return "text/plain";
    }


    public static boolean isValidJson(String jsonString) {
        Gson gson = new Gson();
        try {
            gson.fromJson(jsonString, Object.class);
            return true;
        } catch (com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }
    public static boolean containsIgnoreCase(String str, String searchStr)     {
        if(str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }
}
