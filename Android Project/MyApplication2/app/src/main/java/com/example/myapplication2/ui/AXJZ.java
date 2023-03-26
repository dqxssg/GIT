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
import com.example.myapplication2.adapter.Adapter_AXJZ_GridView;
import com.example.myapplication2.adapter.Adapter_AXJZ_ListView;
import com.example.myapplication2.bean.ShuJv_AXJZ_GridView;
import com.example.myapplication2.bean.ShuJv_AXJZ_ListView;
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
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AXJZ extends AppCompatActivity {
    private ArrayList<String> image = new ArrayList<>();
    private ArrayList<ShuJv_AXJZ_ListView> shuJv_axjz_listViews = new ArrayList<>();
    private Adapter_AXJZ_ListView adapter_axjz_listView;
    private ArrayList<ShuJv_AXJZ_GridView> shuJv_axjz_gridViews = new ArrayList<>();
    private Adapter_AXJZ_GridView adapter_axjz_gridView;
    private TextView back;
    private TextView header;
    private EditText ss;
    private TextView cs;
    private Banner banner;
    private GridView gridview;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.axjz);
        initView();
        header.setText("爱心捐赠");
        back.setOnClickListener(v -> {
            finish();
        });
        //实时捐赠次数
        SSJZCS();
        //gridview
        GV();
        //listview
        LV();
        //搜索
        SS();
        //轮播图
        LBT();
    }

    private void LBT() {
        new HttpUtil().sendResultToken("/prod-api/api/public-welfare/ad-banner/list", "", "GET", new Callback() {
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

    private void SS() {
        ss.setOnEditorActionListener((v, actionId, event) -> {
            if (EditorInfo.IME_ACTION_SEARCH == actionId) {
                Intent intent = new Intent(AXJZ.this, GYLB.class);
                intent.putExtra("name", ss.getText().toString());
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    private void LV() {
        new HttpUtil()
                .sendResultToken("/prod-api/api/public-welfare/public-welfare-activity/recommend-list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_axjz_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_AXJZ_ListView>>() {
                            }.getType());
                            adapter_axjz_listView = new Adapter_AXJZ_ListView(AXJZ.this, shuJv_axjz_listViews);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_axjz_listView);
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < shuJv_axjz_listViews.size(); i++) {
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

    private void GV() {
        new HttpUtil()
                .sendResultToken("/prod-api/api/public-welfare/public-welfare-type/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_axjz_gridViews = new Gson().fromJson(jsonObject.getJSONArray("data").toString(), new TypeToken<List<ShuJv_AXJZ_GridView>>() {
                            }.getType());
                            adapter_axjz_gridView = new Adapter_AXJZ_GridView(AXJZ.this, shuJv_axjz_gridViews);
                            runOnUiThread(() -> {
                                gridview.setAdapter(adapter_axjz_gridView);
                                gridview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(AXJZ.this, GYLB.class);
                                    intent.putExtra("name", "");
                                    startActivity(intent);
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

    private void SSJZCS() {
        new HttpUtil()
                .sendResultToken("/prod-api/api/public-welfare/donate-record/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            runOnUiThread(() -> {
                                try {
                                    cs.setText(jsonObject.getString("total"));
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
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
        cs = (TextView) findViewById(R.id.cs);
        banner = (Banner) findViewById(R.id.banner);
        gridview = (GridView) findViewById(R.id.gridview);
        listview = (ListView) findViewById(R.id.listview);
    }
}
