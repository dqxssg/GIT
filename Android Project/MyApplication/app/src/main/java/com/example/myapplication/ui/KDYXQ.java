package com.example.myapplication.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.util.App;
import com.example.myapplication.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class KDYXQ extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private TextView mz;
    private TextView pf;
    private TextView sysj;
    private TextView xkrs;
    private ImageView img;
    private TextView zy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kdyxq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("电影详情");
        zy.setOnClickListener(v -> {
            finish();
        });
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResyltToken("/prod-api/api/movie/film/detail/" + id, "", "GET", new Callback() {
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
                                    Glide.with(KDYXQ.this)
                                            .load(App.url + jsonObject1.getString("cover"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(img);
                                    mz.setText(jsonObject1.getString("name"));
                                    pf.setText("评分：" + jsonObject1.getString("score"));
                                    sysj.setText("上映时间：" + jsonObject1.getString("playDate"));
                                    xkrs.setText("想看人数：" + jsonObject1.getString("likeNum"));
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
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        mz = (TextView) findViewById(R.id.mz);
        pf = (TextView) findViewById(R.id.pf);
        sysj = (TextView) findViewById(R.id.sysj);
        xkrs = (TextView) findViewById(R.id.xkrs);
        img = (ImageView) findViewById(R.id.img);
        zy = (TextView) findViewById(R.id.zy);
    }
}
