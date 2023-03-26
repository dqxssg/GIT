package com.example.test.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.test.R;
import com.example.test.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RMZT extends AppCompatActivity {
    private ImageView img;
    private ImageView back;
    private TextView header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmzt);
        Bundle bundle = this.getIntent().getExtras();
        int i = bundle.getInt("i");
        System.out.println(i);
        initView();
        header.setText("热门主题");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        new HttpUtil()
                .sendResulToken("/prod-api/press/press/list?hot=Y", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("rows");
                            runOnUiThread(() -> {
                                try {
                                    Glide.with(RMZT.this)
                                            .load("http://124.93.196.45:10001/" + jsonArray.getJSONObject(i).getString("cover"))
                                            .apply(new RequestOptions().transform(new RoundedCorners(20)))
                                            .into(img);
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
        img = (ImageView) findViewById(R.id.img);
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
    }
}
