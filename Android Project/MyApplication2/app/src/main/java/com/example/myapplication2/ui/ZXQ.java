package com.example.myapplication2.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication2.R;
import com.example.myapplication2.util.App;
import com.example.myapplication2.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZXQ extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private ImageView img;
    private TextView mc;
    private TextView flzc;
    private TextView zxrs;
    private TextView fwcs;
    private TextView slzt;
    private TextView wtlx;
    private TextView wtms;
    private TextView scdzp;
    private TextView lxdh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zxq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        new HttpUtil()
                .sendResultToken("/prod-api/api/lawyer-consultation/legal-advice/"+id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            runOnUiThread(() -> {
                                try {
                                    slzt.setText("受理状态：" + jsonObject1.getString("state"));
                                    wtlx.setText("问题类型：" + jsonObject1.getString("legalExpertiseName"));
                                    wtms.setText("问题描述：" + jsonObject1.getString("content"));
                                    scdzp.setText("上传照片：" + jsonObject1.getString("imageUrls"));
                                    lxdh.setText("联系电话：" + jsonObject1.getString("phone"));
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        new HttpUtil()
                .sendResultToken("/prod-api/api/lawyer-consultation/lawyer/" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            runOnUiThread(() -> {
                                try {
                                    Glide.with(ZXQ.this)
                                            .load(App.url + jsonObject1.getString("avatarUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(img);
                                    mc.setText(jsonObject1.getString("name"));
                                    flzc.setText("法律专长；" + jsonObject1.getString("legalExpertiseName"));
                                    zxrs.setText("咨询人数；" + jsonObject1.getString("serviceTimes"));
                                    fwcs.setText("服务人数；" + jsonObject1.getString("serviceTimes"));
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
        img = (ImageView) findViewById(R.id.img);
        mc = (TextView) findViewById(R.id.mc);
        flzc = (TextView) findViewById(R.id.flzc);
        zxrs = (TextView) findViewById(R.id.zxrs);
        fwcs = (TextView) findViewById(R.id.fwcs);
        slzt = (TextView) findViewById(R.id.slzt);
        wtlx = (TextView) findViewById(R.id.wtlx);
        wtms = (TextView) findViewById(R.id.wtms);
        scdzp = (TextView) findViewById(R.id.scdzp);
        lxdh = (TextView) findViewById(R.id.lxdh);
    }
}
