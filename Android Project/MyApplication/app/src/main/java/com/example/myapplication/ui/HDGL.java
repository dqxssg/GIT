package com.example.myapplication.ui;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
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
import com.example.myapplication.R;
import com.example.myapplication.adapter.Adapter_HDGL_ListView;
import com.example.myapplication.bean.ShuJv_HDGL_ListView;
import com.example.myapplication.util.App;
import com.example.myapplication.util.HttpUtil;
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
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HDGL extends AppCompatActivity {
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<ShuJv_HDGL_ListView> shuJv_hdgl_listViews = new ArrayList<>();
    private Adapter_HDGL_ListView adapter_hdgl_listView;
    private ImageView back;
    private TextView header;
    private Banner banner;
    private LinearLayout zy;
    private LinearLayout wd;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hdgl);
        initView();
        header.setText("志愿服务");
        back.setOnClickListener(v -> {
            finish();
        });
        //轮播图
        LBT();
        //新闻列表
        XWLB();
        zy.setOnClickListener(v -> {
            startActivity(new Intent(HDGL.this, ZYHD.class));
        });
        wd.setOnClickListener(v -> {
            startActivity(new Intent(HDGL.this, WDHD.class));
        });

    }

    private void XWLB() {
        new HttpUtil()
                .sendResyltToken("/prod-api/api/volunteer-service/news/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_hdgl_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_HDGL_ListView>>() {
                            }.getType());
                            adapter_hdgl_listView = new Adapter_HDGL_ListView(HDGL.this, shuJv_hdgl_listViews);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_hdgl_listView);
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < shuJv_hdgl_listViews.size(); i++) {
                                    View item = listAdapter.getView(i, null, listview);
                                    item.measure(1, 1);
                                    h += item.getMeasuredHeight();
                                }
                                ViewGroup.LayoutParams params = listview.getLayoutParams();
                                params.height = h + (listview.getDividerHeight() * (listAdapter.getCount() - 1));
                                listview.setLayoutParams(params);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });

    }

    private void LBT() {
        new HttpUtil()
                .sendResyltToken("/prod-api/api/volunteer-service/ad-banner/list", "", "GET", new Callback() {
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
                                        runOnUiThread(() -> {
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
                                        });
                                    }
                                });
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        banner = (Banner) findViewById(R.id.banner);
        zy = (LinearLayout) findViewById(R.id.zy);
        wd = (LinearLayout) findViewById(R.id.wd);
        listview = (ListView) findViewById(R.id.listview);
    }
}
