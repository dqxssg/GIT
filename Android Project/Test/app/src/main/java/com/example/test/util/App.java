package com.example.test.util;

import android.app.Application;
import android.content.SharedPreferences;

public class App extends Application {
    public static String url = "http://124.93.196.45:10001";
    public static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences(HttpUtil.AUTHORITY, MODE_PRIVATE);
    }
}
