package com.example.myapplication.util;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    static String URL = "http://124.93.196.45:10001";
    static String AUTHORITY = "Authorization";

    public void sendResyltToken(String url, String msg, String method, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = null;
        if (method.equalsIgnoreCase("POST")) {
            body = RequestBody.create(MediaType.parse("application/json"), msg);
        } else if (method.equalsIgnoreCase("PUT")) {
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

    public void sendResylt(String url, String msg, String method, Callback callback) {
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
                .addHeader("Content_Type", "application/json")
                .url(URL + url)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
