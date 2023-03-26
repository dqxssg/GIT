package com.example.myapplication2.ui;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication2.R;
import com.example.myapplication2.adapter.Adapter_LV_GridView;
import com.example.myapplication2.adapter.Adapter_LV_ListView1;
import com.example.myapplication2.adapter.Adapter_LV_ListView2;
import com.example.myapplication2.bean.ShuJv_LV_GridView;
import com.example.myapplication2.bean.ShuJv_LV_ListView1;
import com.example.myapplication2.bean.ShuJv_LV_ListView2;
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

public class LS extends AppCompatActivity {
    private ArrayList<String> image = new ArrayList<>();
    private ArrayList<ShuJv_LV_ListView1> shuJv_lv_listView1s = new ArrayList<>();
    private Adapter_LV_ListView1 adapter_lv_listView1;
    private ArrayList<ShuJv_LV_ListView2> shuJv_lv_listView2s = new ArrayList<>();
    private Adapter_LV_ListView2 adapter_lv_listView2;
    private ArrayList<ShuJv_LV_GridView> shuJv_lv_gridViews = new ArrayList<>();
    private Adapter_LV_GridView adapter_lv_gridView;
    private TextView back;
    private TextView header;
    private EditText ss;
    private Banner banner;
    private GridView gridview;
    private ListView listview1;
    private ListView listview2;
    private TextView ckgd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ls);
        initView();
        header.setText("律师咨询");
        back.setOnClickListener(v -> {
            finish();
        });
        ckgd.setOnClickListener(v -> {
            Intent intent = new Intent(LS.this, SS_LS.class);
            intent.putExtra("name", "");
            intent.putExtra("id", 0);
            startActivity(intent);
        });
        //搜索
        ss();
        //轮播图
        LBT();
        //gridview
        GV();
        //listview1
        LV1();
        //listview2
        LV2();
    }

    private void LV2() {
        new HttpUtil()
                .sendResultToken("/prod-api/api/lawyer-consultation/lawyer/list-top10", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_lv_listView2s = new Gson().fromJson(jsonObject.getJSONArray("data").toString(), new TypeToken<List<ShuJv_LV_ListView2>>() {
                            }.getType());
                            adapter_lv_listView2 = new Adapter_LV_ListView2(LS.this, shuJv_lv_listView2s);
                            runOnUiThread(() -> {
                                listview2.setAdapter(adapter_lv_listView2);
                                adapter_lv_listView2.setOnItemListener(new Adapter_LV_ListView2.onItemListener() {
                                    @Override
                                    public void onClick(int i) {
                                        Intent intent = new Intent(LS.this, LSXQ.class);
                                        intent.putExtra("id", shuJv_lv_listView2s.get(i).getId());
                                        startActivity(intent);
                                    }
                                });
                                ListAdapter listAdapter = listview2.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < shuJv_lv_listView2s.size(); i++) {
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

    private void LV1() {
        new HttpUtil()
                .sendResultToken("/prod-api/api/lawyer-consultation/legal-advice/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_lv_listView1s = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_LV_ListView1>>() {
                            }.getType());
                            adapter_lv_listView1 = new Adapter_LV_ListView1(LS.this, shuJv_lv_listView1s);
                            runOnUiThread(() -> {
                                listview1.setAdapter(adapter_lv_listView1);
                                listview1.setOnItemClickListener((parent, view, position, id) -> {
                                    startActivity(new Intent(LS.this,LSZX.class));
                                });
                                ListAdapter listAdapter = listview1.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < shuJv_lv_listView1s.size(); i++) {
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
                            shuJv_lv_gridViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_LV_GridView>>() {
                            }.getType());
                            shuJv_lv_gridViews.sort(new Comparator<ShuJv_LV_GridView>() {
                                @Override
                                public int compare(ShuJv_LV_GridView o1, ShuJv_LV_GridView o2) {
                                    return o1.getId() - o2.getId();
                                }
                            });
                            adapter_lv_gridView = new Adapter_LV_GridView(LS.this, shuJv_lv_gridViews);
                            runOnUiThread(() -> {
                                gridview.setAdapter(adapter_lv_gridView);
                                gridview.setOnItemClickListener((parent, view, position, id) -> {
                                    startActivity(new Intent(LS.this, FLZCLB.class));
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

    private void LBT() {
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
                        image.add(App.url + jsonArray.getJSONObject(i).getString("imgUrl"));
                    }
                    runOnUiThread(() -> {
                        banner.setAdapter(new BannerImageAdapter<String>(image) {
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

    private void ss() {
        ss.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Intent intent = new Intent(LS.this, SS_LS.class);
                intent.putExtra("name", ss.getText().toString());
                intent.putExtra("id", 0);
                startActivity(intent);
                return true;
            }
            return false;
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
        ckgd = (TextView) findViewById(R.id.ckgd);
    }
}
