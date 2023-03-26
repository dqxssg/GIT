package com.example.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.test.R;
import com.example.test.adapter.ZGZ_A_GridView;
import com.example.test.adapter.ZGZ_A_ListView;
import com.example.test.bean.ShuJv_ZGZ_A_GridView;
import com.example.test.bean.ShuJv_ZGZ_A_ListView;
import com.example.test.ui.ZPXQ;
import com.example.test.util.App;
import com.example.test.util.HttpUtil;
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

public class ZGZ_A extends Fragment {
    private ArrayList<ShuJv_ZGZ_A_ListView> shuJv_zgz_a_listViews = new ArrayList<>();
    private ZGZ_A_ListView zgz_a_listView;
    private ArrayList<ShuJv_ZGZ_A_GridView> shuJv_zgz_a_gridViews = new ArrayList<>();
    private ZGZ_A_GridView zgz_a_gridView;
    private ArrayList<String> img = new ArrayList<>();
    private Banner banner;
    private EditText sse;
    private TextView sst;
    private GridView gridview;
    private ListView listview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zgz_a, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        new HttpUtil()
                .sendResulToken("/prod-api/api/rotation/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("rows");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                img.add(App.url + jsonArray.getJSONObject(i).getString("advImg"));
                            }
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    banner.setAdapter(new BannerImageAdapter<String>(img) {
                                        @Override
                                        public void onBindView(BannerImageHolder bannerImageHolder, String s, int i, int i1) {
                                            Glide.with(bannerImageHolder.imageView)
                                                    .load(s)
                                                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                                    .into(bannerImageHolder.imageView);
                                        }
                                    });
                                }
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        new HttpUtil()
                .sendResulToken("/prod-api/api/job/profession/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_zgz_a_gridViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_ZGZ_A_GridView>>() {
                            }.getType());
                            zgz_a_gridView = new ZGZ_A_GridView(getActivity(), shuJv_zgz_a_gridViews);
                            getActivity().runOnUiThread(() -> {
                                gridview.setAdapter(zgz_a_gridView);
                                shuJv_zgz_a_listViews.clear();
                                gridview.setOnItemClickListener((adapterView, view, i, l) -> {
                                    new HttpUtil().sendResulToken("/prod-api/api/job/post/list?professionId=" + shuJv_zgz_a_gridViews.get(i).getId(), "", "GET", new Callback() {
                                        @Override
                                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                        }

                                        @Override
                                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                            try {
                                                JSONObject jsonObject = new JSONObject(response.body().string());
                                                shuJv_zgz_a_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_ZGZ_A_ListView>>() {
                                                }.getType());
                                                zgz_a_listView = new ZGZ_A_ListView(getActivity(), shuJv_zgz_a_listViews);
                                                getActivity().runOnUiThread(() -> {
                                                    listview.setAdapter(zgz_a_listView);
                                                    listview.setOnItemClickListener((adapterView, view, i, l) -> {
                                                        Intent intent = new Intent(getActivity(), ZPXQ.class);
                                                        intent.putExtra("id", shuJv_zgz_a_listViews.get(i).getId());
                                                        startActivity(intent);
                                                    });
                                                    ListAdapter listAdapter = listview.getAdapter();
                                                    if (listAdapter == null) {
                                                        return;
                                                    }
                                                    int h = 0;
                                                    for (int i = 0; i < shuJv_zgz_a_listViews.size(); i++) {
                                                        View item = listAdapter.getView(i, null, listview);
                                                        item.measure(1, 1);
                                                        h += item.getMeasuredHeight();
                                                    }
                                                    ViewGroup.LayoutParams params = listview.getLayoutParams();
                                                    params.height = h + listview.getDividerHeight() * (listAdapter.getCount() - 1);
                                                    listview.setLayoutParams(params);
                                                });
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                });
                                ListAdapter listAdapter = gridview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < 3; i++) {
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
        new HttpUtil().sendResulToken("/prod-api/api/job/post/list", "", "GET", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    shuJv_zgz_a_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_ZGZ_A_ListView>>() {
                    }.getType());
                    zgz_a_listView = new ZGZ_A_ListView(getActivity(), shuJv_zgz_a_listViews);
                    getActivity().runOnUiThread(() -> {
                        listview.setAdapter(zgz_a_listView);
                        listview.setOnItemClickListener((adapterView, view, i, l) -> {
                            Intent intent = new Intent(getActivity(), ZPXQ.class);
                            intent.putExtra("id", shuJv_zgz_a_listViews.get(i).getId());
                            intent.putExtra("companyid", shuJv_zgz_a_listViews.get(i).getCompanyId());
                            intent.putExtra("name", shuJv_zgz_a_listViews.get(i).getName());
                            intent.putExtra("companyname", shuJv_zgz_a_listViews.get(i).getCompanyName());
                            startActivity(intent);
                        });
                        ListAdapter listAdapter = listview.getAdapter();
                        if (listAdapter == null) {
                            return;
                        }
                        int h = 0;
                        for (int i = 0; i < shuJv_zgz_a_listViews.size(); i++) {
                            View item = listAdapter.getView(i, null, listview);
                            item.measure(1, 1);
                            h += item.getMeasuredHeight();
                        }
                        ViewGroup.LayoutParams params = listview.getLayoutParams();
                        params.height = h + listview.getDividerHeight() * (listAdapter.getCount() - 1);
                        listview.setLayoutParams(params);
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        sst.setOnClickListener(view -> {
            new HttpUtil().sendResulToken("/prod-api/api/job/profession/list?professionName=" + sse.getText().toString(), "", "GET", new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("rows");
                        shuJv_zgz_a_listViews.clear();
                        new HttpUtil().sendResulToken("/prod-api/api/job/post/list?professionId=" + jsonArray.getJSONObject(0).getInt("id"), "", "GET", new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body().string());
                                    shuJv_zgz_a_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_ZGZ_A_ListView>>() {
                                    }.getType());
                                    zgz_a_listView = new ZGZ_A_ListView(getActivity(), shuJv_zgz_a_listViews);
                                    getActivity().runOnUiThread(() -> {
                                        listview.setAdapter(zgz_a_listView);
                                        listview.setOnItemClickListener((adapterView, view, i, l) -> {
                                            Intent intent = new Intent(getActivity(), ZPXQ.class);
                                            intent.putExtra("id", shuJv_zgz_a_listViews.get(i).getId());
                                            startActivity(intent);
                                        });
                                        //计算高度
                                        ListAdapter listAdapter = listview.getAdapter();
                                        if (listAdapter == null) {
                                            return;
                                        }
                                        int h = 0;
                                        for (int i = 0; i < shuJv_zgz_a_listViews.size(); i++) {
                                            View item = listAdapter.getView(i, null, listview);
                                            item.measure(1, 1);
                                            h += item.getMeasuredHeight();
                                        }
                                        ViewGroup.LayoutParams params = listview.getLayoutParams();
                                        params.height = h + listview.getDividerHeight() * (listAdapter.getCount() - 1);
                                        listview.setLayoutParams(params);
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
    }

    private void initView() {
        banner = (Banner) getView().findViewById(R.id.banner);
        sse = (EditText) getView().findViewById(R.id.sse);
        sst = (TextView) getView().findViewById(R.id.sst);
        gridview = (GridView) getView().findViewById(R.id.gridview);
        listview = (ListView) getView().findViewById(R.id.listview);
    }
}
