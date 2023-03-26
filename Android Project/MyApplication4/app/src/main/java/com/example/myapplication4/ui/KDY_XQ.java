package com.example.myapplication4.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication4.R;
import com.example.myapplication4.util.App;
import com.example.myapplication4.util.Httputil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class KDY_XQ extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private ImageView image;
    private TextView dymc;
    private TextView pf;
    private TextView sysj;
    private TextView xkrs;
    private TextView tc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kdy_xq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("电影详情");
        back.setOnClickListener(v -> {
            finish();
        });
        tc.setOnClickListener(v -> {
            finish();
        });
        new Httputil()
                .sendResultToken("/prod-api/api/movie/film/detail/" + id, "", "GET", new Callback() {
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
                                    Glide.with(KDY_XQ.this)
                                            .load(App.url + jsonObject1.getString("cover"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(image);
                                    dymc.setText(jsonObject1.getString("name"));
                                    pf.setText("" + jsonObject1.getString("score"));
                                    sysj.setText("" + jsonObject1.getString("playDate"));
                                    xkrs.setText("" + jsonObject1.getString("likeNum"));
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
        dymc = (TextView) findViewById(R.id.dymc);
        pf = (TextView) findViewById(R.id.pf);
        sysj = (TextView) findViewById(R.id.sysj);
        xkrs = (TextView) findViewById(R.id.xkrs);
        tc = (TextView) findViewById(R.id.tc);
    }
}
