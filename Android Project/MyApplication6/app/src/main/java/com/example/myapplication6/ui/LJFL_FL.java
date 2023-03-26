package com.example.myapplication6.ui;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication6.R;
import com.example.myapplication6.adapter.Adapter_LJFL_FL_GV;
import com.example.myapplication6.adapter.S_LJFL_FL_GV;
import com.example.myapplication6.util.App;
import com.example.myapplication6.util.HttpUtil;
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

public class LJFL_FL extends AppCompatActivity {
    private ArrayList<S_LJFL_FL_GV> s_ljfl_fl_gvs = new ArrayList<>();
    private Adapter_LJFL_FL_GV adapter_ljfl_fl_gv;
    private ArrayList<String> img = new ArrayList<>();
    private TextView back;
    private TextView header;
    private Banner banner;
    private TextView khsw;
    private TextView yhlj;
    private TextView slj;
    private TextView glj;
    private GridView gridview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ljfl_fl);
        initView();
        header.setText("垃圾分类");
        back.setOnClickListener(v -> {
            finish();
        });
        //banner
        LBT();
        //跳转
        TZ();
        //gridview
        GV();
    }

    private void GV() {
        new HttpUtil()
                .sendResurltToken("/prod-api/api/garbage-classification/garbage-classification/hot/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_ljfl_fl_gvs = new Gson().fromJson(jsonObject.getString("data"), new TypeToken<List<S_LJFL_FL_GV>>() {
                            }.getType());
                            adapter_ljfl_fl_gv = new Adapter_LJFL_FL_GV(LJFL_FL.this, s_ljfl_fl_gvs);
                            runOnUiThread(() -> {
                                gridview.setAdapter(adapter_ljfl_fl_gv);
                                gridview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(LJFL_FL.this, FLJS_FL.class);
                                    intent.putExtra("name", s_ljfl_fl_gvs.get(position).getKeyword());
                                    startActivity(intent);
                                });
                                ListAdapter listAdapter = gridview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < 5; i++) {
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

    private void TZ() {
        khsw.setOnClickListener(v -> {
            Intent intent = new Intent(LJFL_FL.this, FLJS.class);
            intent.putExtra("name", "可回收垃圾");
            startActivity(intent);
        });
        yhlj.setOnClickListener(v -> {
            Intent intent = new Intent(LJFL_FL.this, FLJS.class);
            intent.putExtra("name", "有害垃圾");
            startActivity(intent);
        });
        slj.setOnClickListener(v -> {
            Intent intent = new Intent(LJFL_FL.this, FLJS.class);
            intent.putExtra("name", "厨余垃圾");
            startActivity(intent);
        });
        glj.setOnClickListener(v -> {
            Intent intent = new Intent(LJFL_FL.this, FLJS.class);
            intent.putExtra("name", "其他垃圾");
            startActivity(intent);
        });
    }

    private void LBT() {
        new HttpUtil()
                .sendResurltToken("/prod-api/api/garbage-classification/poster/list", "", "GET", new Callback() {
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

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        banner = (Banner) findViewById(R.id.banner);
        khsw = (TextView) findViewById(R.id.khsw);
        yhlj = (TextView) findViewById(R.id.yhlj);
        slj = (TextView) findViewById(R.id.slj);
        glj = (TextView) findViewById(R.id.glj);
        gridview = (GridView) findViewById(R.id.gridview);
    }
}
