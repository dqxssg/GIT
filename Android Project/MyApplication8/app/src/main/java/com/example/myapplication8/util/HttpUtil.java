package com.example.myapplication8.util;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    public static String URL = "http://10.212.3.111:8080/mobileA/";

    public void sendResuilt(String url, String msg, String method, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = null;
        if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {
            body = RequestBody.create(MediaType.parse("application/json"), msg);
        }
        Request request = new Request.Builder()
                .method(method.toUpperCase(), body)
                .addHeader("content-type", "application/json")
                .url(URL + url)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
