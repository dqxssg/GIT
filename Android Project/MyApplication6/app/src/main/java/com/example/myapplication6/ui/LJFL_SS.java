package com.example.myapplication6.ui;

import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
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
import com.example.myapplication6.adapter.Adapter_LJFL_SS_LV;
import com.example.myapplication6.bean.S_LJFL_SS_LV;
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

public class LJFL_SS extends AppCompatActivity {
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<S_LJFL_SS_LV> s_ljfl_ss_lvs = new ArrayList<>();
    private Adapter_LJFL_SS_LV adapter_ljfl_ss_lv;
    private TextView back;
    private TextView header;
    private Banner banner;
    private GridView gridview;
    private TextView bt;
    private TextView tfzd;
    private EditText ss;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ljfl_ss);
        initView();
        header.setText("搜索");
        back.setOnClickListener(v -> {
            finish();
        });
        ss.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                SS();
                return true;
            }
            return false;
        });
        //banner
        LBT();
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

    private void SS() {
        new HttpUtil()
                .sendResurltToken("/prod-api/api/garbage-classification/garbage-classification/list?name=" + ss.getText().toString(), "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("rows");
                            if (jsonArray.length() != 0) {
                                runOnUiThread(() -> {
                                    try {
                                        bt.setVisibility(View.VISIBLE);
                                        tfzd.setText(jsonArray.getJSONObject(0).getString("guide"));
                                        new HttpUtil()
                                                .sendResurltToken("/prod-api/api/garbage-classification/garbage-example/list?type=" + jsonArray.getJSONObject(0).getString("id"), "", "GET", new Callback() {
                                                    @Override
                                                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                                    }

                                                    @Override
                                                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                        String s = response.body().string();
                                                        try {
                                                            JSONObject jsonObject1 = new JSONObject(s);
                                                            s_ljfl_ss_lvs = new Gson().fromJson(jsonObject1.getString("rows"), new TypeToken<List<S_LJFL_SS_LV>>() {
                                                            }.getType());
                                                            adapter_ljfl_ss_lv = new Adapter_LJFL_SS_LV(LJFL_SS.this, s_ljfl_ss_lvs);
                                                            runOnUiThread(() -> {
                                                                gridview.setAdapter(adapter_ljfl_ss_lv);
                                                                ListAdapter listAdapter = gridview.getAdapter();
                                                                if (listAdapter == null) {
                                                                    return;
                                                                }
                                                                int h = 0;
                                                                for (int i = 0; i < 4; i++) {
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
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        banner = (Banner) findViewById(R.id.banner);
        gridview = (GridView) findViewById(R.id.gridview);
        bt = (TextView) findViewById(R.id.bt);
        tfzd = (TextView) findViewById(R.id.tfzd);
        ss = (EditText) findViewById(R.id.ss);
    }
}
