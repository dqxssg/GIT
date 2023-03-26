package com.example.myapplication9.ui;

import android.content.Intent;
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
import com.example.myapplication9.R;
import com.example.myapplication9.util.HttpUtil;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class YYJJ extends AppCompatActivity {
    private ArrayList<String> img = new ArrayList<>();
    private TextView back;
    private TextView header;
    private Banner banner;
    private TextView jj;
    private TextView yygh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yyjj);
        Bundle bundle = this.getIntent().getExtras();
        String id = bundle.getString("id");
        System.out.println("id" + id);
        initView();
        header.setText("医院简介");
        back.setOnClickListener(v -> {
            finish();
        });
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("hospitalId", id);
            new HttpUtil()
                    .sendResUtil("getImagesByHospitalId", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                JSONArray jsonArray = jsonObject1.getJSONArray("ROWS_DETAIL");
                                String image1 = jsonArray.getJSONObject(0).getString("image1");
                                String image2 = jsonArray.getJSONObject(0).getString("image2");
                                String image3 = jsonArray.getJSONObject(0).getString("image3");
                                String image4 = jsonArray.getJSONObject(0).getString("image4");
                                img.add(image1);
                                img.add(image2);
                                img.add(image3);
                                img.add(image4);
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
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("hospitalId", id);
            new HttpUtil()
                    .sendResUtil("getHospitalListById", jsonObject1.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject2 = new JSONObject(s);
                                JSONArray jsonArray = jsonObject2.getJSONArray("ROWS_DETAIL");
                                runOnUiThread(() -> {
                                    try {
                                        jj.setText(jsonArray.getJSONObject(0).getString("desc"));
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
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
        yygh.setOnClickListener(v -> {
            Intent intent = new Intent(YYJJ.this, JZRKP.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        banner = (Banner) findViewById(R.id.baanner);
        jj = (TextView) findViewById(R.id.jj);
        yygh = (TextView) findViewById(R.id.yygh);
    }
}
