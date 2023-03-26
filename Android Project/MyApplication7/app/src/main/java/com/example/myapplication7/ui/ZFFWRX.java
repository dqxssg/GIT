package com.example.myapplication7.ui;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication7.R;
import com.example.myapplication7.adapter.Adapter_ZFFWRX_LV;
import com.example.myapplication7.adapter.Adapter_ZFFWRX_RV;
import com.example.myapplication7.bean.S_ZFFWRX_LV;
import com.example.myapplication7.bean.S_ZFFWRX_RV;
import com.example.myapplication7.util.App;
import com.example.myapplication7.util.HttpUtil;
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

public class ZFFWRX extends AppCompatActivity {
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<S_ZFFWRX_LV> s_zffwrx_lvs = new ArrayList<>();
    private Adapter_ZFFWRX_LV adapter_zffwrx_lv;
    private ArrayList<S_ZFFWRX_RV> s_zffwrx_rvs = new ArrayList<>();
    private Adapter_ZFFWRX_RV adapter_zffwrx_rv;
    private TextView back;
    private TextView header;
    private Banner banner;
    private RecyclerView recyclerview;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zffwrx);
        initView();
        header.setText("政府服务热线");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil().sendResuiltToken("/prod-api/api/gov-service-hotline/ad-banner/list", "", "GET", new Callback() {
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
                                    Glide.with(bannerImageHolder.imageView).load(s).apply(RequestOptions.bitmapTransform(new RoundedCorners(30))).into(bannerImageHolder.imageView);
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
        new HttpUtil()
                .sendResuiltToken("/prod-api/api/gov-service-hotline/appeal/my-list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_zffwrx_lvs = new Gson().fromJson(jsonObject.getString("rows"), new TypeToken<List<S_ZFFWRX_LV>>() {
                            }.getType());
                            s_zffwrx_lvs.sort(new Comparator<S_ZFFWRX_LV>() {
                                @Override
                                public int compare(S_ZFFWRX_LV o1, S_ZFFWRX_LV o2) {
                                    return o2.getState().compareTo(o1.getState());
                                }
                            });
                            adapter_zffwrx_lv = new Adapter_ZFFWRX_LV(ZFFWRX.this, s_zffwrx_lvs);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_zffwrx_lv);
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < s_zffwrx_lvs.size(); i++) {
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
        new HttpUtil()
                .sendResuiltToken("/prod-api/api/gov-service-hotline/appeal-category/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_zffwrx_rvs = new Gson().fromJson(jsonObject.getString("rows"), new TypeToken<List<S_ZFFWRX_RV>>() {
                            }.getType());
                            adapter_zffwrx_rv = new Adapter_ZFFWRX_RV(getApplicationContext(), s_zffwrx_rvs);
                            runOnUiThread(() -> {
                                recyclerview.setAdapter(adapter_zffwrx_rv);
                                GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 2);
                                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                recyclerview.setLayoutManager(manager);
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
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        listview = (ListView) findViewById(R.id.listview);
    }
}
