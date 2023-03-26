package com.example.zhcs.ui;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
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
import com.example.zhcs.R;
import com.example.zhcs.adapter.Adapter_WLCX_GV;
import com.example.zhcs.adapter.Adapter_WLCX_LV;
import com.example.zhcs.bean.S_WLCX_GV;
import com.example.zhcs.bean.S_WLCX_LV;
import com.example.zhcs.util.App;
import com.example.zhcs.util.HttpUtil;
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

public class WLCX extends AppCompatActivity {
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<S_WLCX_GV> shuJv_wlcx_gridViews = new ArrayList<>();
    private Adapter_WLCX_GV wlcx_gridView;
    private ArrayList<S_WLCX_LV> shuJv_wlcx_listViews = new ArrayList<>();
    private Adapter_WLCX_LV wlcx_listView;
    private TextView back;
    private TextView header;
    private TextView ss;
    private Banner banner;
    private GridView fridview;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wlcx);
        initView();
        header.setText("物流查询");
        back.setOnClickListener(view -> {
            finish();
        });
        //搜索
        WLCX_SS();
        //轮播图
        WLCX_LBT();
        //GridView
        GV();
        //ListView
        LV();

    }

    private void LV() {
        new HttpUtil()
                .sendResultToken("/prod-api/api/logistics-inquiry/logistics_company/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_wlcx_listViews = new Gson().fromJson(jsonObject.getString("data"), new TypeToken<List<S_WLCX_LV>>() {
                            }.getType());
                            shuJv_wlcx_listViews.sort(new Comparator<S_WLCX_LV>() {
                                @Override
                                public int compare(S_WLCX_LV o1, S_WLCX_LV o2) {
                                    return o1.getName().compareTo(o2.getName());
                                }
                            });
                            wlcx_listView = new Adapter_WLCX_LV(WLCX.this, shuJv_wlcx_listViews);
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
                                params.height = h + listview.getHeight() * (listAdapter.getCount()) - 1;
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
                .sendResultToken("/prod-api/api/logistics-inquiry/logistics_company/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_wlcx_gridViews = new Gson().fromJson(jsonObject.getJSONArray("data").toString(), new TypeToken<List<S_WLCX_GV>>() {
                            }.getType());
                            wlcx_gridView = new Adapter_WLCX_GV(WLCX.this, shuJv_wlcx_gridViews);
                            runOnUiThread(() -> {
                                fridview.setAdapter(wlcx_gridView);
                                fridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(WLCX.this, WLGSXQ.class);
                                        intent.putExtra("id", shuJv_wlcx_gridViews.get(position).getId());
                                        startActivity(intent);
                                    }
                                });
                                ListAdapter listAdapter = fridview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < 3; i++) {
                                    View item = listAdapter.getView(i, null, fridview);
                                    item.measure(1, 1);
                                    h += item.getMeasuredHeight();
                                }
                                ViewGroup.LayoutParams params = fridview.getLayoutParams();
                                params.height = h + fridview.getHeight() * (listAdapter.getCount()) - 1;
                                fridview.setLayoutParams(params);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void WLCX_LBT() {
        new HttpUtil()
                .sendResultToken("/prod-api/api/rotation/list", "", "GET", new Callback() {
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
                                    }
                                });
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void WLCX_SS() {
        ss.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                new HttpUtil()
                        .sendResultToken("/prod-api/api/logistics-inquiry/logistics_info/" + ss.getText().toString(), "", "GET", new Callback() {

                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                String s = response.body().string();
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    if (jsonObject.getString("code").equals("200")) {
                                        Intent intent = new Intent(WLCX.this, DDXQ.class);
                                        intent.putExtra("name", ss.getText().toString());
                                        startActivity(intent);
                                    } else {
                                        runOnUiThread(() -> {
                                            try {
                                                Toast.makeText(WLCX.this, jsonObject.getString("msg").toString(), Toast.LENGTH_SHORT).show();
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
                return true;
            }
            return false;
        });
    }

    private void initView() {
        back = findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        ss = (TextView) findViewById(R.id.ss);
        banner = (Banner) findViewById(R.id.banner);
        fridview = (GridView) findViewById(R.id.fridview);
        listview = (ListView) findViewById(R.id.listview);
    }
}
