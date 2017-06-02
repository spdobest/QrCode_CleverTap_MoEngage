package com.example.root.myvolleydemo.callTracker;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.root.myvolleydemo.net.CustomVolleyPostRequestWithTextPlain;
import com.example.root.myvolleydemo.util.VolleyUtil;

import org.json.JSONObject;

/**
 * Created by root on 5/24/17.
 */

public class SendMobileNumberIntentService extends IntentService {

    private static final String TAG = "IntentService";
    public static final String KEY_PHONE_NUMBER = "phoneNumber";
    public static final String KEY_IS_APICALLED = "isAPicalled";

    String url = "http://api.androidhive.info/volley/person_object.json";

    String phoneNumber;

    boolean isAPiCalled   ;

    public SendMobileNumberIntentService() {
        super("Name for Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent.hasExtra(KEY_PHONE_NUMBER)) {

            phoneNumber = intent.getStringExtra(KEY_PHONE_NUMBER);
        //    isAPiCalled = intent.getBooleanExtra(KEY_IS_APICALLED,false);
            Log.i(TAG, "onHandleIntent: " + phoneNumber);
        }
        if (!TextUtils.isEmpty(phoneNumber)) {
            SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
            String userName = prefs.getString("userName", "");

            if (!isAPiCalled)
                sendMobileNumberWithUserId(userName, phoneNumber);

            Toast.makeText(this, "Number " + phoneNumber, Toast.LENGTH_SHORT).show();
        }
        //  callApi();
        // TODO: 5/24/17 call web service
    }

    private void callApi() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        Toast.makeText(SendMobileNumberIntentService.this, "Json Response " + response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });
        VolleyUtil.getInstance(this).addToRequestQueue(jsonObjReq);
// Adding request to request queue
    }

    private void sendMobileNumberWithUserId(final String userName, String mobilenUMBER) {

        String url = "http://sb.angelbackoffice.com:8088/SB_Reg.svc/Service_SignalR?userId=" + userName + "&mob=" + mobilenUMBER;
// http://sb.angelbackoffice.com:8088/SB_Reg.svc/Service_SignalR?userId=E58488&mob=8097479830
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", userName);
            jsonObject.put("mob", mobilenUMBER);

            Log.i(TAG, "sendMobileNumberWithUserId: " + jsonObject.toString());

            isAPiCalled = true;

            CustomVolleyPostRequestWithTextPlain jsonObjectRequest =
                    new CustomVolleyPostRequestWithTextPlain(
                            Request.Method.GET,
                            url,
                            jsonObject.toString(),
                            new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {


                                    Log.i(TAG, "onResponse: " + response);

                                    Toast.makeText(SendMobileNumberIntentService.this, "Response is " + response.toString(), Toast.LENGTH_SHORT).show();

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            jsonObjectRequest.setTag("send");
            VolleyUtil.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
