package com.example.myapplication3.fragment;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
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
import com.example.myapplication3.R;
import com.example.myapplication3.adapter.ZGZ_A_GridView;
import com.example.myapplication3.adapter.ZGZ_A_ListView;
import com.example.myapplication3.bean.ShuJv_ZGZ_A_GridView;
import com.example.myapplication3.bean.ShuJv_ZGZ_A_ListView;
import com.example.myapplication3.ui.A_LBT;
import com.example.myapplication3.ui.TDJL;
import com.example.myapplication3.util.App;
import com.example.myapplication3.util.HttpUtil;
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
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<ShuJv_ZGZ_A_ListView> shuJv_zgz_a_listViews = new ArrayList<>();
    private ZGZ_A_ListView zgz_a_listView;
    private ArrayList<ShuJv_ZGZ_A_GridView> shuJv_zgz_a_gridViews = new ArrayList<>();
    private ZGZ_A_GridView zgz_a_gridView;

    private EditText sr;
    private TextView ss;
    private Banner banner;
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
        //搜索
        SS();
        //轮播图
        LBT();
        //GridView
        GV();
        //ListView
        LV("");
    }


    private void LV(String name) {
        shuJv_zgz_a_listViews.clear();
        new HttpUtil()
                .sendResltToken("/prod-api/api/job/post/list?name=" + name, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_zgz_a_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_ZGZ_A_ListView>>() {
                            }.getType());
                            zgz_a_listView = new ZGZ_A_ListView(getActivity(), shuJv_zgz_a_listViews);
                            getActivity().runOnUiThread(() -> {
                                listview.setAdapter(zgz_a_listView);
                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(getActivity(), TDJL.class);
                                    intent.putExtra("id", shuJv_zgz_a_listViews.get(position).getId());
                                    intent.putExtra("companyId", shuJv_zgz_a_listViews.get(position).getCompanyId());
                                    intent.putExtra("name", shuJv_zgz_a_listViews.get(position).getName());
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
                                params.height = h + (listview.getDividerHeight() * (listAdapter.getCount() - 1));
                                listview.setLayoutParams(params);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void GV() {
        new HttpUtil()
                .sendResltToken("/prod-api/api/job/profession/list", "", "GET", new Callback() {
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
                            getActivity().runOnUiThread((() -> {
                                gridview.setAdapter(zgz_a_gridView);
                                gridview.setOnItemClickListener((parent, view, position, id) -> {
                                    LV(shuJv_zgz_a_gridViews.get(position).getProfessionName());
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
                                params.height = h + (gridview.getMeasuredHeight() * (listAdapter.getCount() - 1));
                                gridview.setLayoutParams(params);
                            }));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void LBT() {
        new HttpUtil()
                .sendResltToken("/prod-api/api/rotation/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        System.out.println("123" + s);
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
                                            bannerImageHolder.imageView.setOutlineProvider(new ViewOutlineProvider() {
                                                @Override
                                                public void getOutline(View view, Outline outline) {
                                                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 20);
                                                }
                                            });
                                            bannerImageHolder.imageView.setClipToOutline(true);
                                        }
                                    });
                                }
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });

    }

    private void SS() {
        ss.setOnClickListener(v -> {
            LV(sr.getText().toString());
        });
    }

    private void initView() {
        sr = (EditText) getView().findViewById(R.id.sr);
        ss = (TextView) getView().findViewById(R.id.ss);
        banner = (Banner) getView().findViewById(R.id.banner);
        gridview = (GridView) getView().findViewById(R.id.gridview);
        listview = (ListView) getView().findViewById(R.id.listview);
    }
}
