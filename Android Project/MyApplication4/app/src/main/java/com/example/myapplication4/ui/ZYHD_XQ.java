package com.example.myapplication4.ui;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication4.R;
import com.example.myapplication4.util.Httputil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZYHD_XQ extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView hdmc;
    private TextView hdjs;
    private TextView kssj;
    private TextView ryyq;
    private TextView cbdw;
    private TextView gznr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zyhd_xq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("活动详情");
        back.setOnClickListener(v -> {
            finish();
        });
        new Httputil()
                .sendResultToken("/prod-api/api/volunteer-service/activity/" + id, "", "GET", new Callback() {
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
                                    hdmc.setText("活动名称：" + jsonObject1.getString("title"));
                                    hdjs.setText("活动简介：" + Html.fromHtml(jsonObject1.getString("detail")));
                                    kssj.setText("开始时间：" + jsonObject1.getString("startAt"));
                                    ryyq.setText("人员要求：" + jsonObject1.getString("requireText"));
                                    cbdw.setText("承办单位：" + jsonObject1.getString("undertaker"));
                                    gznr.setText("工作内容：");
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
        hdmc = (TextView) findViewById(R.id.hdmc);
        hdjs = (TextView) findViewById(R.id.hdjs);
        kssj = (TextView) findViewById(R.id.kssj);
        ryyq = (TextView) findViewById(R.id.ryyq);
        cbdw = (TextView) findViewById(R.id.cbdw);
        gznr = (TextView) findViewById(R.id.gznr);
    }
}
