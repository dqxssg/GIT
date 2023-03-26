package com.example.myapplication2.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication2.R;
import com.example.myapplication2.TopicAdapter;
import com.example.myapplication2.TopicBean;
import com.example.myapplication2.adapter.Ad_L_GV;
import com.example.myapplication2.adapter.Ad_L_LV1;
import com.example.myapplication2.adapter.Ad_L_LV2;
import com.example.myapplication2.adapter.Adapter_LV_ListView2;
import com.example.myapplication2.bean.SJ_L_GV;
import com.example.myapplication2.bean.SJ_L_LV1;
import com.example.myapplication2.bean.SJ_L_LV2;
import com.example.myapplication2.util.App;
import com.example.myapplication2.util.HttpUtil;
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

public class L extends AppCompatActivity {
    private ArrayList<TopicBean> mTopicData = new ArrayList<>();
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<SJ_L_LV1> sj_l_lv1s = new ArrayList<>();
    private Ad_L_LV1 ad_l_lv1;
    private ArrayList<SJ_L_LV2> sj_l_lv2s = new ArrayList<>();
    private Ad_L_LV2 ad_l_lv2;
    private ArrayList<SJ_L_GV> sj_l_gvs = new ArrayList<>();
    private Ad_L_GV ad_l_gv;
    private TextView back;
    private TextView header;
    private EditText ss;
    private Banner banner;
    private GridView gridview;
    private ListView listview1;
    private ListView listview2;
    private RecyclerView topicRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l);
        initView();
        header.setText("律师咨询");
        back.setOnClickListener(v -> {
            finish();
        });
        //搜索
        SS();
        //banner
        B();
        //gridview
//        GV();
        //listview1
        LV1();
        //listview2
        LV2();


        initTypeRecyclerView(2);
    }

    private void SS() {
        ss.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Intent intent = new Intent(L.this, L_SS.class);
                intent.putExtra("name", ss.getText().toString());
                intent.putExtra("id", "");
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    private void B() {
        new HttpUtil().sendResultToken("/prod-api/api/lawyer-consultation/ad-banner/list", "", "GET", new Callback() {
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

    private void GV() {
        new HttpUtil()
                .sendResultToken("/prod-api/api/lawyer-consultation/legal-expertise/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            sj_l_gvs = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<SJ_L_GV>>() {
                            }.getType());
                            sj_l_gvs.sort(new Comparator<SJ_L_GV>() {
                                @Override
                                public int compare(SJ_L_GV o1, SJ_L_GV o2) {
                                    return o2.getId() - o1.getId();
                                }
                            });
                            ad_l_gv = new Ad_L_GV(L.this, sj_l_gvs);
                            runOnUiThread(() -> {
                                gridview.setAdapter(ad_l_gv);
                                gridview.setOnItemClickListener((parent, view, position, id) -> {
                                    startActivity(new Intent(L.this, FZC.class));
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
    }

    private void LV1() {
        new HttpUtil().sendResultToken("/prod-api/api/lawyer-consultation/legal-advice/list", "", "GET", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    sj_l_lv1s = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<SJ_L_LV1>>() {
                    }.getType());
                    ad_l_lv1 = new Ad_L_LV1(L.this, sj_l_lv1s);
                    runOnUiThread(() -> {
                        listview1.setAdapter(ad_l_lv1);
                        listview1.setOnItemClickListener((parent, view, position, id) -> {
                            startActivity(new Intent(L.this, ZXLB.class));
                        });
                        ListAdapter listAdapter = listview1.getAdapter();
                        if (listAdapter == null) {
                            return;
                        }
                        int h = 0;
                        for (int i = 0; i < sj_l_lv1s.size(); i++) {
                            View item = listAdapter.getView(i, null, listview1);
                            item.measure(1, 1);
                            h += item.getMeasuredHeight();
                        }
                        ViewGroup.LayoutParams params = listview1.getLayoutParams();
                        params.height = h + listview1.getDividerHeight() * (listAdapter.getCount() - 1);
                        listview1.setLayoutParams(params);
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void LV2() {
        new HttpUtil().sendResultToken("/prod-api/api/lawyer-consultation/lawyer/list-top10", "", "GET", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    sj_l_lv2s = new Gson().fromJson(jsonObject.getJSONArray("data").toString(), new TypeToken<List<SJ_L_LV2>>() {
                    }.getType());
                    ad_l_lv2 = new Ad_L_LV2(L.this, sj_l_lv2s);
                    ad_l_lv2.setOnItemListener(new Adapter_LV_ListView2.onItemListener() {
                        @Override
                        public void onClick(int i) {
                            Intent intent = new Intent(L.this, LXQ.class);
                            intent.putExtra("id", sj_l_lv2s.get(i).getId());
                            startActivity(intent);
                        }
                    });
                    runOnUiThread(() -> {
                        listview2.setAdapter(ad_l_lv2);
                        ListAdapter listAdapter = listview2.getAdapter();
                        if (listAdapter == null) {
                            return;
                        }
                        int h = 0;
                        for (int i = 0; i < sj_l_lv2s.size(); i++) {
                            View item = listAdapter.getView(i, null, listview2);
                            item.measure(1, 1);
                            h += item.getMeasuredHeight();
                        }
                        ViewGroup.LayoutParams params = listview2.getLayoutParams();
                        params.height = h + listview2.getDividerHeight() * (listAdapter.getCount() - 1);
                        listview2.setLayoutParams(params);
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
        ss = (EditText) findViewById(R.id.ss);
        banner = (Banner) findViewById(R.id.banner);
        gridview = (GridView) findViewById(R.id.gridview);
        listview1 = (ListView) findViewById(R.id.listview1);
        listview2 = (ListView) findViewById(R.id.listview2);
        topicRecyclerView = (RecyclerView) findViewById(R.id.topicRecyclerView);
    }


    private void initTypeRecyclerView(int rowNum) {
        new HttpUtil()
                .sendResultToken("/prod-api/api/lawyer-consultation/legal-expertise/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            mTopicData = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<TopicBean>>() {
                            }.getType());
                            mTopicData.sort(new Comparator<TopicBean>() {
                                @Override
                                public int compare(TopicBean o1, TopicBean o2) {
                                    return o2.getId() - o1.getId();
                                }
                            });
                            runOnUiThread(() -> {
                                TopicAdapter topicAdapter = new TopicAdapter(getApplicationContext(), mTopicData);
                                topicRecyclerView.setAdapter(topicAdapter);
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), rowNum);
                                gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                topicRecyclerView.setLayoutManager(gridLayoutManager);
                                topicRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                    @Override
                                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                        super.onScrollStateChanged(recyclerView, newState);
                                    }

                                    @Override
                                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                        super.onScrolled(recyclerView, dx, dy);
                                    }
                                });
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }
}
