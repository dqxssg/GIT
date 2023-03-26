package com.example.myapplication9.ui;

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
import com.example.myapplication9.R;
import com.example.myapplication9.adapter.Adapter_HD_LV;
import com.example.myapplication9.bean.S_HD_LV;
import com.example.myapplication9.util.HttpUtil;
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

public class HD extends AppCompatActivity {
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<S_HD_LV> s_hd_lvArrayList = new ArrayList<>();
    private Adapter_HD_LV adapter_hd_lv;
    private TextView back;
    private TextView header;
    private Banner banner;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hd);
        initView();
        header.setText("活动");
        back.setOnClickListener(v -> {
            finish();
        });
        //banner
        BAN();
        //listview
        LV();
    }

    private void BAN() {
        new HttpUtil()
                .sendResUtil("getActionImages", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                img.add(jsonArray.getJSONObject(0).getString("image"));
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
                                        bannerImageHolder.imageView.setOnClickListener(v -> {
                                            Intent intent = new Intent(HD.this, A_XQ.class);
                                            intent.putExtra("img", s);
                                            intent.putExtra("name", "轮播图");
                                            startActivity(intent);
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

    private void LV() {
        new HttpUtil()
                .sendResUtil("getAllActions", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_hd_lvArrayList = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_HD_LV>>() {
                            }.getType());
                            adapter_hd_lv = new Adapter_HD_LV(HD.this, s_hd_lvArrayList);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_hd_lv);
                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent=new Intent(HD.this,HD_XQ.class);
                                    intent.putExtra("id",s_hd_lvArrayList.get(position).getId());
                                    startActivity(intent);
                                });
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < s_hd_lvArrayList.size(); i++) {
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

    @Override
    protected void onRestart() {
        super.onRestart();
        LV();
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        banner = (Banner) findViewById(R.id.baanner);
        listview = (ListView) findViewById(R.id.listview);
    }
}
