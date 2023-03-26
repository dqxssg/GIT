package com.example.zhcs.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.zhcs.R;
import com.example.zhcs.util.App;
import com.example.zhcs.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DT extends AppCompatActivity {
    private TextView header;
    private LinearLayout line;
    private ImageView img;
    private TextView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dt);
        initView();
        header.setText("地铁路线");
        back.setOnClickListener(view -> {
            finish();
        });
        //显示全部路线名称
        XSQBLXMC();
        //显示地铁地图
        XSDTDT();
    }

    //显示地铁地图
    private void XSDTDT() {
        new HttpUtil()
                .sendResultToken("/prod-api/api/metro/city", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            runOnUiThread(() -> {
                                try {
                                    Glide.with(DT.this)
                                            .load(App.url + jsonObject1.getString("imgUrl"))
                                            .into(img);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }

    //显示全部路线名称
    private void XSQBLXMC() {
        new HttpUtil()
                .sendResultToken("/prod-api/api/metro/line/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                View view = LayoutInflater.from(DT.this).inflate(R.layout.xl, null);
                                @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView name = view.findViewById(R.id.name);
                                // 随机颜色
                                Random random = new Random();
                                int ranColor = 0xff000000 | random.nextInt(0x00ffffff);
                                name.setTextColor(ranColor);
                                name.setText(jsonArray.getJSONObject(i).getString("lineName"));
                                view.setPadding(0, 0, 0, 0);
                                runOnUiThread(() -> {
                                    line.addView(view, LinearLayout.LayoutParams.WRAP_CONTENT);
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    private void initView() {
        header = findViewById(R.id.header);
        line = findViewById(R.id.line);
        img = findViewById(R.id.img);
        back = findViewById(R.id.back);
    }
}
