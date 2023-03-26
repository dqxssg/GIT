package com.example.myapplication2.util;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    public static String URL = "http://124.93.196.45:10001";
    public static String AUTHORITY = "Authorization";

    public void sendResultToken(String url, String msg, String method, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = null;
        if (method.equalsIgnoreCase("POST")) {
            body = RequestBody.create(MediaType.parse("application/json"), msg);
        }
        if (method.equalsIgnoreCase("PUT")) {
            body = RequestBody.create(MediaType.parse("application/json"), msg);
        }
        Request request = new Request.Builder()
                .method(method.toUpperCase(), body)
                .addHeader(AUTHORITY, App.sp.getString("token", ""))
                .addHeader("Content-Type", "application/json")
                .url(URL + url)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void sendResult(String url, String msg, String method, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = null;
        if (method.equalsIgnoreCase("POST")) {
            body = RequestBody.create(MediaType.parse("application/json"), msg);
        }
        if (method.equalsIgnoreCase("PUT")) {
            body = RequestBody.create(MediaType.parse("application/json"), msg);
        }
        Request request = new Request.Builder()
                .method(method.toUpperCase(), body)
                .addHeader("content-type", "application")
                .url(URL + url)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
