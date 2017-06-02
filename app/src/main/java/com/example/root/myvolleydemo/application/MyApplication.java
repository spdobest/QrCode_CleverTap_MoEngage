package com.example.root.myvolleydemo.application;

import android.app.Application;

import com.example.root.myvolleydemo.util.CleverTapManager;
import com.example.root.myvolleydemo.util.MoEngageManager;

/**
 * Created by root on 5/24/17.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        CleverTapManager.getInstance(getApplicationContext());
       // MoEngageManager.getInstance(getApplicationContext());
    }
}
