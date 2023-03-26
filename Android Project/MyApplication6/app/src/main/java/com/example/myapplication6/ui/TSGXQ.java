package com.example.myapplication6.ui;

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
import com.example.myapplication6.R;
import com.example.myapplication6.util.App;
import com.example.myapplication6.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TSGXQ extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private ImageView image;
    private TextView mc;
    private TextView js;
    private TextView dz;
    private TextView sj;
    private TextView zt;
    private TextView pl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tsgxq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        pl.setOnClickListener(v -> {
            Intent intent = new Intent(TSGXQ.this,SZTSG_PL.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });
        header.setText("图书馆详情");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResurltToken("/prod-api/api/digital-library/library/" + id, "", "GET", new Callback() {
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
                                    Glide.with(TSGXQ.this)
                                            .load(App.url + jsonObject1.getString("imgUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(image);
                                    mc.setText(jsonObject1.getString("name"));
                                    js.setText(jsonObject1.getString("description"));
                                    dz.setText("地址：" + jsonObject1.getString("address"));
                                    sj.setText("营业时间：" + jsonObject1.getString("businessHours"));
                                    if (jsonObject1.getString("businessState").equals("1")) {
                                        zt.setText("营业中");
                                    } else {
                                        zt.setText("未营业");
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
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        image = (ImageView) findViewById(R.id.image);
        mc = (TextView) findViewById(R.id.mc);
        js = (TextView) findViewById(R.id.js);
        dz = (TextView) findViewById(R.id.dz);
        sj = (TextView) findViewById(R.id.sj);
        zt = (TextView) findViewById(R.id.zt);
        pl = (TextView) findViewById(R.id.pl);
    }
}
