package com.example.myapplication7.ui;

import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication7.R;
import com.example.myapplication7.bean.S;
import com.example.myapplication7.util.App;
import com.example.myapplication7.util.HttpUtil;
import com.google.gson.Gson;
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

public class YZXQ extends AppCompatActivity {
    private ArrayList<String> img = new ArrayList<>();
    private S ss;
    private Banner banner;
    private TextView dz;
    private TextView lxdh;
    private TextView rzsj;
    private TextView nan;
    private TextView nv;
    private TextView yzjj;
    private TextView fjpz;
    private TextView zbtc;
    private TextView tsfw;
    private TextView back;
    private TextView header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yzxq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("驿站详情");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResuiltToken("/prod-api/api/youth-inn/youth-inn/" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            ss = new Gson().fromJson(new JSONObject(s).optJSONObject("data").toString(), S.class);
                            List<String> imageUrlList = ss.getImageUrlList();
                            for (int i = 0; i < imageUrlList.size(); i++) {
                                img.add(App.url + imageUrlList.get(i));
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
                                try {
                                    dz.setText("地址：" + jsonObject1.getString("address"));
                                    lxdh.setText("联系电话：" + jsonObject1.getString("phone"));
                                    rzsj.setText("办理入住时间段：" + jsonObject1.getString("workTime"));
                                    nan.setText("剩余床数男：" + jsonObject1.getString("bedsCountBoy"));
                                    nv.setText("剩余床数女：" + jsonObject1.getString("bedsCountGirl"));
                                    yzjj.setText("驿站简介：" + jsonObject1.getString("introduce"));
                                    fjpz.setText("房间配置：" + jsonObject1.getString("internalFacilities"));
                                    zbtc.setText("周边配套：" + jsonObject1.getString("surroundingFacilities"));
                                    tsfw.setText("特色服务：" + jsonObject1.getString("specialService"));
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
        banner = (Banner) findViewById(R.id.banner);
        dz = (TextView) findViewById(R.id.dz);
        lxdh = (TextView) findViewById(R.id.lxdh);
        rzsj = (TextView) findViewById(R.id.rzsj);
        nan = (TextView) findViewById(R.id.nan);
        nv = (TextView) findViewById(R.id.nv);
        yzjj = (TextView) findViewById(R.id.yzjj);
        fjpz = (TextView) findViewById(R.id.fjpz);
        zbtc = (TextView) findViewById(R.id.zbtc);
        tsfw = (TextView) findViewById(R.id.tsfw);
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
    }
}
