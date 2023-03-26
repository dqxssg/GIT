package com.example.myapplication2.ui;

import android.content.Intent;
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

public class ZXXQ extends AppCompatActivity {
    private ImageView image1;
    private TextView cynx;
    private TextView zxrs;
    private TextView flzc;
    private TextView hpl;
    private ImageView image2;
    private TextView lxdh;
    private TextView wtms;
    private TextView wtlx;
    private TextView slzt;
    private TextView xx;
    private TextView back;
    private TextView header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zxxq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("咨询详情");
        back.setOnClickListener(v -> {
            finish();
        });
        xx.setOnClickListener(v -> {
            if (xx.getText().toString().equals("评价")) {
                Intent intent=new Intent(ZXXQ.this,FWPJ.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        new HttpUtil().sendResultToken("/prod-api/api/lawyer-consultation/legal-advice/" + id, "", "GET", new Callback() {
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
                            Glide.with(ZXXQ.this).load(App.url + jsonObject1.getString("imageUrls")).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(image1);
                            Glide.with(ZXXQ.this).load(App.url + jsonObject1.getString("imageUrls")).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(image2);
                            cynx.setText("从业年限：" + jsonObject1.getString("content"));
                            zxrs.setText("咨询日期：" + jsonObject1.getString("createTime"));
                            flzc.setText("法律专长：" + jsonObject1.getString("legalExpertiseName"));
                            hpl.setText("好评率：" + jsonObject1.getString("content"));
                            lxdh.setText("联系电话：" + jsonObject1.getString("phone"));
                            wtms.setText("问题描述：" + jsonObject1.getString("content"));
                            wtlx.setText("问题类型：" + jsonObject1.getString("legalExpertiseName"));
                            if (jsonObject1.getString("content").equals("0")) {
                                slzt.setText("受理状态：暂未受理");
                            } else {
                                slzt.setText("受理状态：暂未受理");
                            }
                            if (jsonObject1.getString("state").equals("0")) {
                                xx.setText("暂未受理");
                            } else {
                                xx.setText("评价");
                            }
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
        image1 = (ImageView) findViewById(R.id.image1);
        cynx = (TextView) findViewById(R.id.cynx);
        zxrs = (TextView) findViewById(R.id.zxrs);
        flzc = (TextView) findViewById(R.id.flzc);
        hpl = (TextView) findViewById(R.id.hpl);
        image2 = (ImageView) findViewById(R.id.image2);
        lxdh = (TextView) findViewById(R.id.lxdh);
        wtms = (TextView) findViewById(R.id.wtms);
        wtlx = (TextView) findViewById(R.id.wtlx);
        slzt = (TextView) findViewById(R.id.slzt);
        xx = (TextView) findViewById(R.id.xx);
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
    }
}
