package com.example.myapplication6.ui;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication6.R;
import com.example.myapplication6.adapter.Adapter_LJFL_LV;
import com.example.myapplication6.bean.S_LJFL_LV;
import com.example.myapplication6.util.App;
import com.example.myapplication6.util.HttpUtil;
import com.google.android.material.tabs.TabLayout;
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

public class LJFL extends AppCompatActivity {
    private ArrayList<String> img = new ArrayList<>();
    private String[] xx = {"新时尚", "党员在行动", "分类达人", "社区动态"};
    private ArrayList<S_LJFL_LV> s_ljfl_lvs = new ArrayList<>();
    private Adapter_LJFL_LV adapter_ljfl_lv;
    private TextView back;
    private TextView header;
    private Banner banner;
    private TextView ss;
    private TextView fl;
    private TabLayout tablayout;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ljfl);
        initView();
        header.setText("垃圾分类");
        back.setOnClickListener(v -> {
            finish();
        });
        //banner
        BR();
        //tz
        TZ();
        //tablayout
        TL();
        //listview
        LV("7");
    }

    private void BR() {
        new HttpUtil().sendResurltToken("/prod-api/api/garbage-classification/ad-banner/list", "", "GET", new Callback() {
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
                                Glide.with(bannerImageHolder.imageView).load(s).apply(RequestOptions.bitmapTransform(new RoundedCorners(30))).into(bannerImageHolder.imageView);
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

    private void TZ() {
        ss.setOnClickListener(v -> {
            startActivity(new Intent(LJFL.this,LJFL_SS.class));
        });
        fl.setOnClickListener(v -> {
            startActivity(new Intent(LJFL.this,LJFL_FL.class));
        });
    }

    private void TL() {
        for (int i = 0; i < xx.length; i++) {
            tablayout.addTab(tablayout.newTab().setText(xx[i]));
        }
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getText().toString()) {
                    case "新时尚":
                        LV("7");
                        break;
                    case "党员在行动":
                        LV("8");
                        break;
                    case "分类达人":
                        LV("9");
                        break;
                    case "社区动态":
                        LV("10");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void LV(String id) {
        new HttpUtil().sendResurltToken("/prod-api/api/garbage-classification/news/list?type=" + id, "", "GET", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                System.out.println(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    s_ljfl_lvs = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_LJFL_LV>>() {
                    }.getType());
                    s_ljfl_lvs.sort(new Comparator<S_LJFL_LV>() {
                        @Override
                        public int compare(S_LJFL_LV o1, S_LJFL_LV o2) {
                            return o2.getCreateTime().compareTo(o1.getCreateTime());
                        }
                    });
                    adapter_ljfl_lv = new Adapter_LJFL_LV(LJFL.this, s_ljfl_lvs);
                    runOnUiThread(() -> {
                        listview.setAdapter(adapter_ljfl_lv);
                        listview.setOnItemClickListener((parent, view, position, id1) -> {
                            Intent intent = new Intent(LJFL.this,LJFL_XWXQ.class);
                            intent.putExtra("id", s_ljfl_lvs.get(position).getId());
                            startActivity(intent);
                        });
                        ListAdapter listAdapter = listview.getAdapter();
                        if (listAdapter == null) {
                            return;
                        }
                        int h = 0;
                        for (int i = 0; i < s_ljfl_lvs.size(); i++) {
                            View item = listAdapter.getView(i, null, listview);
                            item.measure(1, 1);
                            h += item.getMeasuredHeight();
                        }
                        ViewGroup.LayoutParams params = listview.getLayoutParams();
                        params.height = h + listview.getDividerHeight() * (listAdapter.getCount() - 1);
                        listview.setLayoutParams(params);
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        banner = (Banner) findViewById(R.id.banner);
        ss = (TextView) findViewById(R.id.ss);
        fl = (TextView) findViewById(R.id.fl);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        listview = (ListView) findViewById(R.id.listview);
    }
}
