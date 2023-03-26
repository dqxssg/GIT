package com.example.myapplication6.ui;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FLJS_FL extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView nameBt;
    private ImageView image;
    private TextView flsm;
    private TextView tfyq;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fljs_fl);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        initView();
        header.setText("详情");
        nameBt.setText(name);
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResurltToken("/prod-api/api/garbage-classification/garbage-classification/list?name=" + name, "", "GET", new Callback() {
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
                                        Glide.with(FLJS_FL.this)
                                                .load(App.url + jsonArray.getJSONObject(0).getString("imgUrl"))
                                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                                .into(image);
                                        flsm.setText("分类说明：" + jsonArray.getJSONObject(0).getString("introduce"));
                                        tfyq.setText("投放要求：" + jsonArray.getJSONObject(0).getString("guide"));
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
        nameBt = (TextView) findViewById(R.id.name_bt);
        image = (ImageView) findViewById(R.id.image);
        flsm = (TextView) findViewById(R.id.flsm);
        tfyq = (TextView) findViewById(R.id.tfyq);
    }
}
