package com.example.myapplication4.ui;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication4.R;
import com.example.myapplication4.adapter.Adapter_F_A_LV;
import com.example.myapplication4.adapter.Adapter_HDGL_LV;
import com.example.myapplication4.bean.S_F_A_LV;
import com.example.myapplication4.bean.S_HDGL_LV;
import com.example.myapplication4.util.App;
import com.example.myapplication4.util.Httputil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HDGL extends AppCompatActivity {
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<S_HDGL_LV> s_hdgl_lvs = new ArrayList<>();
    private Adapter_HDGL_LV adapter_hdgl_lv;
    private TextView back;
    private TextView header;
    private Banner banner;
    private ListView listview;
    private LinearLayout zyhd;
    private LinearLayout wdhd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hdgl);
        initView();
        header.setText("活动管理");
        back.setOnClickListener(v -> {
            finish();
        });
        //轮播图
        LVT();
        //listview
        LV();
        //跳转
        TZ();
    }

    private void LVT() {
        new Httputil()
                .sendResultToken("/prod-api/api/volunteer-service/ad-banner/list", "", "GET", new Callback() {
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
                                img.add(App.url + jsonArray.getJSONObject(i).getString("imgUrl"));
                            }
                            runOnUiThread(() -> {
                                banner.setAdapter(new BannerImageAdapter<String>(img) {
                                    @Override
                                    public void onBindView(BannerImageHolder bannerImageHolder, String s, int i, int i1) {
                                        Glide.with(bannerImageHolder.imageView)
                                                .load(s)
                                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                                .into(bannerImageHolder.imageView);
                                        bannerImageHolder.imageView.setOutlineProvider(new ViewOutlineProvider() {
                                            @Override
                                            public void getOutline(View view, Outline outline) {
                                                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 20);
                                            }
                                        });
                                        bannerImageHolder.imageView.setClipToOutline(true);
                                    }
                                });
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void LV() {
        new Httputil()
                .sendResultToken("/prod-api/api/volunteer-service/news/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_hdgl_lvs = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_HDGL_LV>>() {
                            }.getType());
                            s_hdgl_lvs.sort(new Comparator<S_HDGL_LV>() {
                                @Override
                                public int compare(S_HDGL_LV o1, S_HDGL_LV o2) {
                                    return o2.getCreateTime().compareTo(o1.getCreateTime());
                                }
                            });
                            adapter_hdgl_lv = new Adapter_HDGL_LV(HDGL.this, s_hdgl_lvs);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_hdgl_lv);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void TZ() {
        zyhd.setOnClickListener(v -> {
            startActivity(new Intent(HDGL.this, ZYHD.class));
        });
        wdhd.setOnClickListener(v -> {
            startActivity(new Intent(HDGL.this, WDHD.class));
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        banner = (Banner) findViewById(R.id.banner);
        listview = (ListView) findViewById(R.id.listview);
        zyhd = (LinearLayout) findViewById(R.id.zyhd);
        wdhd = (LinearLayout) findViewById(R.id.wdhd);
    }
}
