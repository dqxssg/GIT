package com.example.myapplication5.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication5.R;
import com.example.myapplication5.util.App;
import com.example.myapplication5.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FL_XQ extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private ImageView image;
    private TextView mc;
    private TextView tfsm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fl_xq);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        initView();
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
                                        Glide.with(FL_XQ.this)
                                                .load(App.url + jsonArray.getJSONObject(0).getString("imgUrl"))
                                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                                .into(image);
                                        header.setText(jsonArray.getJSONObject(0).getString("name"));
                                        mc.setText(jsonArray.getJSONObject(0).getString("introduce"));
                                        tfsm.setText(jsonArray.getJSONObject(0).getString("guide"));
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                            } else {
                                runOnUiThread(() -> {
                                    header.setText("垃圾分类");
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
        image = (ImageView) findViewById(R.id.image);
        mc = (TextView) findViewById(R.id.mc);
        tfsm = (TextView) findViewById(R.id.tfsm);
    }
}
