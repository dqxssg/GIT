package com.example.myapplication8.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication8.R;
import com.example.myapplication8.adapter.Adapter_A_GV;
import com.example.myapplication8.adapter.Adapter_A_LV;
import com.example.myapplication8.bean.S_A_GV;
import com.example.myapplication8.bean.S_A_LV;
import com.example.myapplication8.bean.S_A_LV_QT;
import com.example.myapplication8.ui.A_SS;
import com.example.myapplication8.ui.A_XQ;
import com.example.myapplication8.ui.B_FW;
import com.example.myapplication8.ui.MainActivity;
import com.example.myapplication8.util.HttpUtil;
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
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class A extends Fragment {
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<S_A_LV_QT> s_a_lv_qtArrayList1 = new ArrayList<>();
    private ArrayList<S_A_LV_QT> s_a_lv_qtArrayList2 = new ArrayList<>();
    private ArrayList<S_A_LV> s_a_lvArrayList1 = new ArrayList<>();
    private ArrayList<S_A_LV> s_a_lvArrayList2 = new ArrayList<>();
    private Adapter_A_LV adapter_a_lv;
    private ArrayList<S_A_GV> s_a_gvArrayList = new ArrayList<>();
    private Adapter_A_GV adapter_a_gv;
    private TextView header;
    private EditText ss;
    private Banner banner;
    private GridView gridview;
    private LinearLayout line0;
    private ImageView image0;
    private TextView text0;
    private LinearLayout line1;
    private ImageView image1;
    private TextView text1;
    private TabLayout tablayout;
    private ListView listview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.a, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        header.setText("智慧城市");
        //搜索
        SS();
        //banner
        BA();
        //gridview
        GV();
        //热门主题
        RMZT();
        //tablayout
        TL();
        //listview
        LV("时政");
    }

    private void SS() {
        ss.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Intent intent = new Intent(getActivity(), A_SS.class);
                intent.putExtra("name", ss.getText().toString());
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    private void BA() {
        new HttpUtil()
                .sendResuilt("getImages", "", "GET", new Callback() {
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
                                img.add(jsonArray.getJSONObject(i).getString("path"));
                            }
                            getActivity().runOnUiThread(() -> {
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
                                            try {
                                                Intent intent = new Intent(getActivity(), A_XQ.class);
                                                intent.putExtra("name", "轮播图");
                                                intent.putExtra("image", jsonArray.getJSONObject(i).getString("path"));
                                                startActivity(intent);
                                            } catch (JSONException e) {
                                                throw new RuntimeException(e);
                                            }
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

    private void GV() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("serviceType", "智慧服务");
            new HttpUtil()
                    .sendResuilt("getServiceByType", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                s_a_gvArrayList = new Gson().fromJson(jsonObject1.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_A_GV>>() {
                                }.getType());
                                s_a_gvArrayList.add(new S_A_GV().setServiceName("更多服务"));
                                adapter_a_gv = new Adapter_A_GV(getActivity(), s_a_gvArrayList);
                                getActivity().runOnUiThread(() -> {
                                    gridview.setAdapter(adapter_a_gv);
                                    gridview.setOnItemClickListener((parent, view, position, id) -> {
                                        switch (position) {
                                            case 9:
                                                MainActivity.TZ_B();
                                                break;
                                            default:
                                                Intent intent = new Intent(getActivity(), B_FW.class);
                                                intent.putExtra("name", s_a_gvArrayList.get(position).getServiceName());
                                                startActivity(intent);
                                                break;
                                        }
                                    });
                                    ListAdapter listAdapter = gridview.getAdapter();
                                    if (listAdapter == null) {
                                        return;
                                    }
                                    int h = 0;
                                    for (int i = 0; i < 2; i++) {
                                        View item = listAdapter.getView(i, null, gridview);
                                        item.measure(1, 1);
                                        h += item.getMeasuredHeight();
                                    }
                                    ViewGroup.LayoutParams params = gridview.getLayoutParams();
                                    params.height = h + gridview.getMeasuredHeight() * (listAdapter.getCount() - 1);
                                    gridview.setLayoutParams(params);
                                });
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void RMZT() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("keys", "新闻");
            new HttpUtil()
                    .sendResuilt("getNewsByKeys", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            JSONObject jsonObject1;
                            try {
                                jsonObject1 = new JSONObject(s);
                                JSONArray jsonArray = jsonObject1.getJSONArray("ROWS_DETAIL");
                                getActivity().runOnUiThread(() -> {
                                    try {
                                        Glide.with(getActivity())
                                                .load(jsonArray.getJSONObject(0).getString("picture"))
                                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                                .into(image0);
                                        Glide.with(getActivity())
                                                .load(jsonArray.getJSONObject(1).getString("picture"))
                                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                                .into(image1);
                                        text0.setText(jsonArray.getJSONObject(0).getString("title"));
                                        text1.setText(jsonArray.getJSONObject(1).getString("title"));
                                        line0.setOnClickListener(v -> {
                                            try {
                                                Intent intent = new Intent(getActivity(), A_XQ.class);
                                                intent.putExtra("name", "热门主题");
                                                intent.putExtra("image", jsonArray.getJSONObject(0).getString("picture"));
                                                startActivity(intent);
                                            } catch (JSONException e) {
                                                throw new RuntimeException(e);
                                            }
                                        });
                                        line1.setOnClickListener(v -> {
                                            try {
                                                Intent intent = new Intent(getActivity(), A_XQ.class);
                                                intent.putExtra("name", "热门主题");
                                                intent.putExtra("image", jsonArray.getJSONObject(1).getString("picture"));
                                                startActivity(intent);
                                            } catch (JSONException e) {
                                                throw new RuntimeException(e);
                                            }
                                        });
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                            } catch (
                                    JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        } catch (
                JSONException e) {
            throw new RuntimeException(e);
        }

    }

    private void TL() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("subject", "电影");
            new HttpUtil()
                    .sendResuilt("getAllNewsType", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                JSONArray jsonArray = jsonObject1.getJSONArray("ROWS_DETAIL");
                                getActivity().runOnUiThread(() -> {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        try {
                                            tablayout.addTab(tablayout.newTab().setText(jsonArray.getJSONObject(i).getString("newstype")));
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                    tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                        @Override
                                        public void onTabSelected(TabLayout.Tab tab) {
                                            switch (tab.getText().toString()) {
                                                case "时政":
                                                    LV("时政");
                                                    break;
                                                case "疫情":
                                                    LV("疫情");
                                                    break;
                                                case "娱乐":
                                                    LV("娱乐");
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
                                });
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void LV(String name) {
        s_a_lvArrayList1.clear();
        s_a_lvArrayList2.clear();
        s_a_lv_qtArrayList1.clear();
        s_a_lv_qtArrayList2.clear();
        new HttpUtil()
                .sendResuilt("getNEWsList", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_a_lvArrayList1 = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_A_LV>>() {
                            }.getType());
                            for (S_A_LV s_a_lv : s_a_lvArrayList1) {
                                if (Objects.equals(s_a_lv.getNewsType(), name)) {
                                    s_a_lvArrayList2.add(s_a_lv);
                                }
                            }
                            for (S_A_LV s_a_lv : s_a_lvArrayList2) {
                                try {
                                    jsonObject.put("newsid", s_a_lv.getNewsid());
                                    new HttpUtil()
                                            .sendResuilt("getNewsInfoById", jsonObject.toString(), "POST", new Callback() {
                                                @Override
                                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                                }

                                                @Override
                                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                    String s = response.body().string();
                                                    try {
                                                        JSONObject jsonObject1 = new JSONObject(s);
                                                        s_a_lv_qtArrayList1 = new Gson().fromJson(jsonObject1.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_A_LV_QT>>() {
                                                        }.getType());
                                                        for (S_A_LV_QT s_a_lv_qt : s_a_lv_qtArrayList1) {
                                                            s_a_lv_qtArrayList2.add(s_a_lv_qt);
                                                        }
                                                        if (s_a_lvArrayList2.get(s_a_lvArrayList2.size() - 1).getNewsid() == s_a_lv.getNewsid()) {
                                                            adapter_a_lv = new Adapter_A_LV(getActivity(), s_a_lvArrayList2, s_a_lv_qtArrayList2);
                                                            getActivity().runOnUiThread(() -> {
                                                                listview.setAdapter(adapter_a_lv);
                                                                ListAdapter listAdapter = listview.getAdapter();
                                                                if (listAdapter == null) {
                                                                    return;
                                                                }
                                                                int h = 0;
                                                                for (int i = 0; i < s_a_lvArrayList2.size(); i++) {
                                                                    View item = listAdapter.getView(i, null, listview);
                                                                    item.measure(1, 1);
                                                                    h += item.getMeasuredHeight();
                                                                }
                                                                ViewGroup.LayoutParams params = listview.getLayoutParams();
                                                                params.height = h + listview.getDividerHeight() * (listAdapter.getCount() - 1);
                                                                listview.setLayoutParams(params);
                                                            });
                                                        }
                                                    } catch (JSONException e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                }
                                            });
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void initView() {
        header = (TextView) getView().findViewById(R.id.header);
        ss = (EditText) getView().findViewById(R.id.ss);
        banner = (Banner) getView().findViewById(R.id.banner);
        gridview = (GridView) getView().findViewById(R.id.gridview);
        line0 = (LinearLayout) getView().findViewById(R.id.line0);
        image0 = (ImageView) getView().findViewById(R.id.image0);
        text0 = (TextView) getView().findViewById(R.id.text0);
        line1 = (LinearLayout) getView().findViewById(R.id.line1);
        image1 = (ImageView) getView().findViewById(R.id.image1);
        text1 = (TextView) getView().findViewById(R.id.text1);
        tablayout = (TabLayout) getView().findViewById(R.id.tablayout);
        listview = (ListView) getView().findViewById(R.id.listview);
    }
}
