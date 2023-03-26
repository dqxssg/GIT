package com.example.myapplication2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class A_RMZT extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView title;
    private ImageView image;
    private TextView content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_rmzt);
        Bundle bundle = this.getIntent().getExtras();
        int i = bundle.getInt("i");
        initView();
        header.setText("热门主题");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResultToken("/prod-api/press/press/list?hot=y", "", "GET", new Callback() {
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
                                    title.setText(jsonArray.getJSONObject(i).getString("title"));
                                    content.setText(Html.fromHtml(jsonArray.getJSONObject(i).getString("content")));
                                    Glide.with(A_RMZT.this)
                                            .load(App.url + jsonArray.getJSONObject(i).getString("cover"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(image);
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
        title = (TextView) findViewById(R.id.title);
        image = (ImageView) findViewById(R.id.image);
        content = (TextView) findViewById(R.id.content);
    }
}
