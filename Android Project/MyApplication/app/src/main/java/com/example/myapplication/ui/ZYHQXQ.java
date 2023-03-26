package com.example.myapplication.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZYHQXQ extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private TextView hdmc;
    private TextView hdjj;
    private TextView hdkssj;
    private TextView ryyq;
    private TextView gznr;
    private TextView cbdw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zyhdxq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("活动详情");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResyltToken("/prod-api/api/volunteer-service/activity/" + id, "", "GET", new Callback() {
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
                                    hdmc.setText(jsonObject1.getString("title"));
                                    hdjj.setText("活动简介：" + jsonObject1.getString("detail"));
                                    hdkssj.setText("开始时间：" + jsonObject1.getString("startAt"));
                                    ryyq.setText("人员要求：" + jsonObject1.getString("requireText"));
                                    gznr.setText("工作内容：" + jsonObject1.getString("detail"));
                                    cbdw.setText("承办单位：" + jsonObject1.getString("undertaker"));
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
        hdmc = (TextView) findViewById(R.id.hdmc);
        hdjj = (TextView) findViewById(R.id.hdjj);
        hdkssj = (TextView) findViewById(R.id.hdkssj);
        ryyq = (TextView) findViewById(R.id.ryyq);
        gznr = (TextView) findViewById(R.id.gznr);
        cbdw = (TextView) findViewById(R.id.cbdw);
    }
}
