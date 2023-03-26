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
import com.example.myapplication3.R;
import com.example.myapplication3.adapter.A_GridView;
import com.example.myapplication3.adapter.A_ListView;
import com.example.myapplication3.bean.ShuJv_A_GridView;
import com.example.myapplication3.bean.ShuJv_A_ListView;
import com.example.myapplication3.ui.A_LBT;
import com.example.myapplication3.ui.A_RMZT;
import com.example.myapplication3.ui.A_SS;
import com.example.myapplication3.ui.CWYY;
import com.example.myapplication3.ui.MainActivity;
import com.example.myapplication3.ui.WLCX;
import com.example.myapplication3.ui.ZGZ;
import com.example.myapplication3.ui.ZHBS;
import com.example.myapplication3.util.App;
import com.example.myapplication3.util.HttpUtil;
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

public class A extends Fragment {
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<ShuJv_A_ListView> shuJv_a_listViews = new ArrayList<>();
    private A_ListView a_listView;
    private ArrayList<ShuJv_A_GridView> shuJv_a_gridViews = new ArrayList<>();
    private A_GridView a_gridView;
    private TextView header;
    private EditText sr;
    private TextView ss;
    private Banner banner;
    private GridView gridview;
    private ImageView img1;
    private TextView text1;
    private ImageView img2;
    private TextView text2;
    private TabLayout tablayout;
    private ListView listview;
    private LinearLayout line1;
    private LinearLayout line2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.a, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        header.setText("主页");
        //搜索
        SS();
        //轮播图
        LBT();
        //十个服务快
        SGFWK();
        //热门主题
        RMZT();
        //横向滑块
        HXHK();
        //新闻列表
        XWLB("9");
    }

    private void XWLB(String id) {
        new HttpUtil()
                .sendResltToken("/prod-api/press/press/list?type=" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        System.out.println("123" + s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_a_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_A_ListView>>() {
                            }.getType());
                            a_listView = new A_ListView(getActivity(), shuJv_a_listViews);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listview.setAdapter(a_listView);
                                    ListAdapter listAdapter = listview.getAdapter();
                                    if (listAdapter == null) {
                                        return;
                                    }
                                    int h = 0;
                                    for (int i = 0; i < shuJv_a_listViews.size(); i++) {
                                        View item = listAdapter.getView(i, null, listview);
                                        item.measure(1, 1);
                                        h += item.getMeasuredHeight();
                                    }
                                    ViewGroup.LayoutParams params = listview.getLayoutParams();
                                    params.height = h + (listview.getDividerHeight() * (listAdapter.getCount() - 1));
                                    listview.setLayoutParams(params);
                                }
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

    }

    private void HXHK() {
        new HttpUtil()
                .sendResltToken("/prod-api/press/category/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        try {
                                            tablayout.addTab(tablayout.newTab().setText(jsonArray.getJSONObject(i).getString("name")));
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }
                                        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                            @Override
                                            public void onTabSelected(TabLayout.Tab tab) {
                                                switch (tab.getText().toString()) {
                                                    case "今日要闻":
                                                        XWLB("9");
                                                        break;
                                                    case "专题聚焦":
                                                        XWLB("17");
                                                        break;
                                                    case "政策解读":
                                                        XWLB("19");
                                                        break;
                                                    case "经济发展":
                                                        XWLB("20");
                                                        break;
                                                    case "文化旅游":
                                                        XWLB("21");
                                                        break;
                                                    case "科技创新":
                                                        XWLB("22");
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
                                }
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });


    }

    private void RMZT() {
        new HttpUtil()
                .sendResltToken("/prod-api/press/press/list?hot=Y", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("rows");
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        text1.setText(jsonArray.getJSONObject(0).getString("title"));
                                        Glide.with(getContext())
                                                .load(App.url + jsonArray.getJSONObject(0).getString("cover"))
                                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                                .into(img1);
                                        line1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(getActivity(), A_RMZT.class);
                                                try {
                                                    intent.putExtra("img", App.url + jsonArray.getJSONObject(0).getString("cover"));
                                                } catch (JSONException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                        });
                                        text2.setText(jsonArray.getJSONObject(1).getString("title"));
                                        Glide.with(getContext())
                                                .load(App.url + jsonArray.getJSONObject(1).getString("cover"))
                                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                                .into(img2);
                                        line2.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(getActivity(), A_RMZT.class);
                                                try {
                                                    intent.putExtra("img", App.url + jsonArray.getJSONObject(1).getString("cover"));
                                                } catch (JSONException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
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
                });


    }

    private void SGFWK() {
        new HttpUtil()
                .sendResltToken("/prod-api/api/service/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_a_gridViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_A_GridView>>() {
                            }.getType());
                            shuJv_a_gridViews.sort(new Comparator<ShuJv_A_GridView>() {
                                @Override
                                public int compare(ShuJv_A_GridView o1, ShuJv_A_GridView o2) {
                                    return o1.getId() - o2.getId();
                                }
                            });
                            a_gridView = new A_GridView(getActivity(), shuJv_a_gridViews);
                            getActivity().runOnUiThread(() -> {
                                gridview.setAdapter(a_gridView);
                                gridview.setOnItemClickListener((parent, view, position, id) -> {
                                    switch (position) {
                                        case 9:
                                            MainActivity.TZ_B();
                                            break;
                                        case 1:
                                            startActivity(new Intent(getActivity(), ZHBS.class));
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
                                params.height = h + (gridview.getMeasuredHeight() * (listAdapter.getCount() - 1));
                                gridview.setLayoutParams(params);
                            });
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
                                            bannerImageHolder.imageView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(getActivity(), A_LBT.class);
                                                    intent.putExtra("img", App.url + img.get(i));
                                                    startActivity(intent);
                                                }
                                            });
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
        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), A_SS.class);
                intent.putExtra("name", sr.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void initView() {
        header = (TextView) getView().findViewById(R.id.header);
        sr = (EditText) getView().findViewById(R.id.sr);
        ss = (TextView) getView().findViewById(R.id.ss);
        banner = (Banner) getView().findViewById(R.id.banner);
        gridview = (GridView) getView().findViewById(R.id.gridview);
        img1 = (ImageView) getView().findViewById(R.id.img1);
        text1 = (TextView) getView().findViewById(R.id.text1);
        img2 = (ImageView) getView().findViewById(R.id.img2);
        text2 = (TextView) getView().findViewById(R.id.text2);
        tablayout = (TabLayout) getView().findViewById(R.id.tablayout);
        listview = (ListView) getView().findViewById(R.id.listview);
        line1 = (LinearLayout) getView().findViewById(R.id.line1);
        line2 = (LinearLayout) getView().findViewById(R.id.line2);
    }
}
