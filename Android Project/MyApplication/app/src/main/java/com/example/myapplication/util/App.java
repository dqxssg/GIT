package com.example.myapplication.util;

import android.app.Application;
import android.content.SharedPreferences;

public class App extends Application {
    public static String url = "http://124.93.196.45:10001";
    public static SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();
        sp = getSharedPreferences(HttpUtil.AUTHORITY, MODE_PRIVATE);
    }
}
