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
import com.example.myapplication7.util.App;
import com.example.myapplication7.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SQLB_XQ extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView bt;
    private TextView nr;
    private ImageView image;
    private TextView cbdw;
    private TextView tjsj;
    private TextView clzt;
    private TextView cljg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xqlb_xq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("详情");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResuiltToken("/prod-api/api/gov-service-hotline/appeal/"+id, "", "GET", new Callback() {
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
                                    Glide.with(SQLB_XQ.this)
                                            .load(App.url + jsonObject1.getString("imgUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(image);
                                    bt.setText(jsonObject1.getString("title"));
                                    nr.setText(jsonObject1.getString("content"));
                                    cbdw.setText(jsonObject1.getString("undertaker"));
                                    tjsj.setText(jsonObject1.getString("createTime"));
                                    if (jsonObject1.getString("state").equals("1")) {
                                        clzt.setText("已处理");
                                    } else {
                                        clzt.setText("未处理");
                                    }
                                    cljg.setText(jsonObject1.getString("detailResult"));
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
        bt = (TextView) findViewById(R.id.bt);
        nr = (TextView) findViewById(R.id.nr);
        image = (ImageView) findViewById(R.id.image);
        cbdw = (TextView) findViewById(R.id.cbdw);
        tjsj = (TextView) findViewById(R.id.tjsj);
        clzt = (TextView) findViewById(R.id.clzt);
        cljg = (TextView) findViewById(R.id.cljg);
    }
}
