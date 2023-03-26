package com.example.myapplication5.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication5.R;
import com.example.myapplication5.adapter.Adapter_B_LV;
import com.example.myapplication5.bean.S_B_LV;
import com.example.myapplication5.ui.B_FL;
import com.example.myapplication5.ui.B_SS;
import com.example.myapplication5.ui.B_XQ;
import com.example.myapplication5.util.App;
import com.example.myapplication5.util.HttpUtil;
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

public class B extends Fragment {
    private String[] xx = {"新时尚", "党员在行动", "分类达人", "社区动态"};
    private ArrayList<S_B_LV> s_b_lvArrayList = new ArrayList<>();
    private Adapter_B_LV adapter_b_lv;
    private ArrayList<String> img = new ArrayList<>();
    private TextView header;
    private Banner banner;
    private TextView ss;
    private TextView fl;
    private ListView listview;
    private TextView back;
    private TabLayout tablayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.b, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        header.setText("垃圾分类");
        //banner
        BR();
        //分类和搜索
        FLSS();
        //新闻
        XW("7");
        for (int i = 0; i < xx.length; i++) {
            tablayout.addTab(tablayout.newTab().setText(xx[i]));
        }
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getText().toString()) {
                    case "新时尚":
                        XW("7");
                        break;
                    case "党员在行动":
                        XW("8");
                        break;
                    case "分类达人":
                        XW("9");
                        break;
                    case "社区动态":
                        XW("10");
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

    private void XW(String id) {
        s_b_lvArrayList.clear();
        new HttpUtil()
                .sendResurltToken("/prod-api/api/garbage-classification/news/list?type=" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_b_lvArrayList = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_B_LV>>() {
                            }.getType());
                            s_b_lvArrayList.sort(new Comparator<S_B_LV>() {
                                @Override
                                public int compare(S_B_LV o1, S_B_LV o2) {
                                    return o2.getCreateTime().compareTo(o1.getCreateTime());
                                }
                            });
                            adapter_b_lv = new Adapter_B_LV(getActivity(), s_b_lvArrayList);
                            getActivity().runOnUiThread(() -> {
                                listview.setAdapter(adapter_b_lv);
                                listview.setOnItemClickListener((parent, view, position, id1) -> {
                                    Intent intent = new Intent(getActivity(), B_XQ.class);
                                    intent.putExtra("id", s_b_lvArrayList.get(position).getId());
                                    startActivity(intent);

                                });
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < s_b_lvArrayList.size(); i++) {
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

    private void FLSS() {
        fl.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), B_FL.class));
        });
        ss.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), B_SS.class));
        });
    }

    private void BR() {
        new HttpUtil()
                .sendResurltToken("/prod-api/api/garbage-classification/ad-banner/list", "", "GET", new Callback() {
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
                            getActivity().runOnUiThread(() -> {
                                banner.setAdapter(new BannerImageAdapter<String>(img) {
                                    @Override
                                    public void onBindView(BannerImageHolder bannerImageHolder, String s, int i, int i1) {
                                        Glide.with(bannerImageHolder.imageView)
                                                .load(s)
                                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
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

    private void initView() {
        header = (TextView) getView().findViewById(R.id.header);
        banner = (Banner) getView().findViewById(R.id.banner);
        ss = (TextView) getView().findViewById(R.id.ss);
        fl = (TextView) getView().findViewById(R.id.fl);
        listview = (ListView) getView().findViewById(R.id.listview);
        back = (TextView) getView().findViewById(R.id.back);
        tablayout = (TabLayout) getView().findViewById(R.id.tablayout);
    }
}
