package com.example.myapplication3.ui;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication3.R;
import com.example.myapplication3.adapter.WLCX_GridView;
import com.example.myapplication3.adapter.WLCX_ListView;
import com.example.myapplication3.bean.ShuJv_WLCX_GridView;
import com.example.myapplication3.bean.ShuJv_WLCX_ListView;
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

public class WLCX extends AppCompatActivity {
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<ShuJv_WLCX_ListView> shuJv_wlcx_listViews = new ArrayList<>();
    private WLCX_ListView wlcx_listView;
    private ArrayList<ShuJv_WLCX_GridView> shuJv_wlcx_gridViews = new ArrayList<>();
    private WLCX_GridView wlcx_gridView;
    private ImageView back;
    private TextView header;
    private EditText sr;
    private TextView ss;
    private Banner banner;
    private GridView gridview;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wlcx);
        initView();
        header.setText("物流查询");
        back.setOnClickListener(v -> {
            finish();
        });
        //搜索
        SS();
        //轮播图
        LBT();
        //GridView
        GV();
        //列表
        LB();
    }

    private void GV() {
        new HttpUtil()
                .sendResltToken("/prod-api/api/logistics-inquiry/logistics_company/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_wlcx_gridViews = new Gson().fromJson(jsonObject.getJSONArray("data").toString(), new TypeToken<List<ShuJv_WLCX_GridView>>() {
                            }.getType());
                            wlcx_gridView = new WLCX_GridView(WLCX.this, shuJv_wlcx_gridViews);
                            runOnUiThread(() -> {
                                gridview.setAdapter(wlcx_gridView);
                                gridview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(WLCX.this, WLGSXQ.class);
                                    intent.putExtra("id", shuJv_wlcx_gridViews.get(position).getId());
                                    startActivity(intent);
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
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });

    }

    private void LB() {
        new HttpUtil()
                .sendResltToken("/prod-api/api/logistics-inquiry/logistics_company/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_wlcx_listViews = new Gson().fromJson(jsonObject.getJSONArray("data").toString(), new TypeToken<List<ShuJv_WLCX_ListView>>() {
                            }.getType());
                            wlcx_listView = new WLCX_ListView(WLCX.this, shuJv_wlcx_listViews);
                            runOnUiThread(() -> {
                                listview.setAdapter(wlcx_listView);
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < shuJv_wlcx_listViews.size(); i++) {
                                    View item = listAdapter.getView(i, null, listview);
                                    item.measure(1, 1);
                                    h += item.getMeasuredHeight();
                                }
                                ViewGroup.LayoutParams params = listview.getLayoutParams();
                                params.height = h + (listview.getMeasuredHeight() * (listAdapter.getCount() - 1));
                                listview.setLayoutParams(params);
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
                        System.out.println("123" + s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("rows");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                img.add(App.url + jsonArray.getJSONObject(i).getString("advImg"));
                            }
                            runOnUiThread(new Runnable() {
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
            new HttpUtil()
                    .sendResltToken("/prod-api/api/logistics-inquiry/logistics_info/" + sr.getText().toString(), "GET", "", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                if (jsonObject.getJSONArray("code").equals("200")) {
                                    Intent intent = new Intent(WLCX.this, YDXQ.class);
                                    intent.putExtra("name", sr.getText().toString());
                                    startActivity(intent);
                                } else {
                                    runOnUiThread(() -> {
                                        try {
                                            Toast.makeText(WLCX.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    });
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        sr = (EditText) findViewById(R.id.sr);
        ss = (TextView) findViewById(R.id.ss);
        banner = (Banner) findViewById(R.id.banner);
        gridview = (GridView) findViewById(R.id.gridview);
        listview = (ListView) findViewById(R.id.listview);
    }
}
