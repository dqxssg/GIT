package com.example.myapplication7.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication7.R;
import com.example.myapplication7.bean.S_ZFZ_LV;
import com.example.myapplication7.util.App;
import com.example.myapplication7.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZFZ_XQ extends AppCompatActivity {
    private ArrayList<S_ZFZ_LV> s_zfz_lvs = new ArrayList<>();
    private TextView back;
    private TextView header;
    private TextView zy;
    private ImageView image;
    private TextView mc;
    private TextView dj;
    private TextView lx;
    private TextView js;
    private TextView mj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zfz_xq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("详情");
        back.setOnClickListener(v -> {
            finish();
        });
        zy.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResuiltToken("/prod-api/api/house/housing/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_zfz_lvs = new Gson().fromJson(jsonObject.getString("rows"), new TypeToken<List<S_ZFZ_LV>>() {
                            }.getType());
                            for (S_ZFZ_LV s_zfz_lv : s_zfz_lvs) {
                                if (s_zfz_lv.getId() == id) {
                                    runOnUiThread(() -> {
                                        Glide.with(ZFZ_XQ.this)
                                                .load(App.url + s_zfz_lv.getPic())
                                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                                .into(image);
                                        mc.setText("房源名称：" + s_zfz_lv.getAddress());
                                        dj.setText("房源单价：" + s_zfz_lv.getPrice());
                                        lx.setText("房源类型：" + s_zfz_lv.getHouseType());
                                        js.setText("房源介绍：" + s_zfz_lv.getDescription());
                                        mj.setText("建筑面积：" + s_zfz_lv.getAreaSize());
                                    });
                                    break;
                                }
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
        zy = (TextView) findViewById(R.id.zy);
        image = (ImageView) findViewById(R.id.image);
        mc = (TextView) findViewById(R.id.mc);
        dj = (TextView) findViewById(R.id.dj);
        lx = (TextView) findViewById(R.id.lx);
        js = (TextView) findViewById(R.id.js);
        mj = (TextView) findViewById(R.id.mj);
    }
}
