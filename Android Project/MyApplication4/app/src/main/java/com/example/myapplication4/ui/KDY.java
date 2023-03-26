package com.example.myapplication4.ui;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication4.R;
import com.example.myapplication4.adapter.Adapter_KDY_LV;
import com.example.myapplication4.bean.S_KDY_LV;
import com.example.myapplication4.util.App;
import com.example.myapplication4.util.Httputil;
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

public class KDY extends AppCompatActivity {
    private ArrayList<S_KDY_LV> s_kdy_lvs = new ArrayList<>();
    private Adapter_KDY_LV adapter_kdy_lv;
    private ArrayList<String> img = new ArrayList<>();
    private TextView back;
    private TextView header;
    private EditText ss;
    private Banner banner;
    private ListView listview;
    private TextView ckgd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kdy);
        initView();
        header.setText("看电影");
        back.setOnClickListener(v -> {
            finish();
        });
        //搜索
        SS();
        //轮播图
        LBT();
        //listview
        LV("", 5);
        //查看更多
        ckgd.setOnClickListener(v -> {
            ckgd.setVisibility(View.INVISIBLE);
            LV("", s_kdy_lvs.size());
        });
    }

    private void SS() {
        ss.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                s_kdy_lvs.clear();
                new Httputil()
                        .sendResultToken("/prod-api/api/movie/film/list?name="+ss.getText().toString() , "", "GET", new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                String s = response.body().string();
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    s_kdy_lvs = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_KDY_LV>>() {
                                    }.getType());

                                    System.out.println("123"+s_kdy_lvs.size());
                                    adapter_kdy_lv = new Adapter_KDY_LV(KDY.this, s_kdy_lvs,  s_kdy_lvs.size());
                                    runOnUiThread(() -> {
                                        listview.setAdapter(adapter_kdy_lv);
                                        listview.setOnItemClickListener((parent, view, position, id) -> {
                                            Intent intent = new Intent(KDY.this, KDY_XQ.class);
                                            intent.putExtra("id", s_kdy_lvs.get(position).getId());
                                            startActivity(intent);
                                        });
                                        ListAdapter listAdapter = listview.getAdapter();
                                        if (listAdapter == null) {
                                            return;
                                        }
                                        int h = 0;
                                        for (int i = 0; i < s_kdy_lvs.size(); i++) {

                                            System.out.println("123"+s_kdy_lvs.size());
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




                ckgd.setVisibility(View.INVISIBLE);
                return true;
            }
            return false;
        });
    }

    private void LBT() {
        new Httputil()
                .sendResultToken("/prod-api/api/movie/rotation/list", "", "GET", new Callback() {
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

    private void LV(String mame, int ii) {
        s_kdy_lvs.clear();
        new Httputil()
                .sendResultToken("/prod-api/api/movie/film/list?name=" + mame, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_kdy_lvs = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_KDY_LV>>() {
                            }.getType());

                            System.out.println("123"+s_kdy_lvs.size());
                            adapter_kdy_lv = new Adapter_KDY_LV(KDY.this, s_kdy_lvs, ii);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_kdy_lv);
                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(KDY.this, KDY_XQ.class);
                                    intent.putExtra("id", s_kdy_lvs.get(position).getId());
                                    startActivity(intent);
                                });
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < ii; i++) {

                                    System.out.println("123"+s_kdy_lvs.size());
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

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        ss = (EditText) findViewById(R.id.ss);
        banner = (Banner) findViewById(R.id.banner);
        listview = (ListView) findViewById(R.id.listview);
        ckgd = (TextView) findViewById(R.id.ckgd);
    }
}
