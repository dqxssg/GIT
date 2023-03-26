package com.example.myapplication9.util;

import java.util.Objects;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    public static String URL = "http://10.212.3.111:8080/mobileA/";

    public void sendResUtil(String url, String msg, String method, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = null;
        if (Objects.equals(method, "POST") || Objects.equals(method, "PUT")) {
            body = RequestBody.create(MediaType.parse("application"), msg);
        }
        Request request = new Request.Builder()
                .method(method.toUpperCase(), body)
                .addHeader("content-type", "application/json")
                .url(URL + url)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
