package com.example.myapplication2.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;
import com.example.myapplication2.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TNEXQ extends AppCompatActivity {

    private TextView back;
    private TextView header;
    private TextView tccmc;
    private TextView tccdz;
    private TextView tccjl;
    private TextView tccsfkf;
    private TextView sycw;
    private TextView sf;
    private TextView zg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tnexq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        back.setOnClickListener(v -> {
            finish();
        });
        header.setText("停车场详情");
        new HttpUtil()
                .sendResultToken("/prod-api/api/park/lot/" + id, "", "GET", new Callback() {
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
                                    tccmc.setText("停车场名称：" + jsonObject1.getString("parkName"));
                                    tccdz.setText("地址：" + jsonObject1.getString("address"));
                                    tccjl.setText("距离：" + jsonObject1.getString("distance"));
                                    if (jsonObject1.getString("open").equals("Y")) {
                                        tccsfkf.setText("是否对外开放：对外开放");
                                    } else if (jsonObject1.getString("open").equals("N")) {
                                        tccsfkf.setText("是否对外开放：不对外开放");
                                    }
                                    sycw.setText("剩余车位：" + jsonObject1.getString("vacancy"));
                                    sf.setText("收费标准：" + jsonObject1.getString("rates"));
                                    zg.setText("最高收费：" + jsonObject1.getString("priceCaps"));
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
        tccmc = (TextView) findViewById(R.id.tccmc);
        tccdz = (TextView) findViewById(R.id.tccdz);
        tccjl = (TextView) findViewById(R.id.tccjl);
        tccsfkf = (TextView) findViewById(R.id.tccsfkf);
        sycw = (TextView) findViewById(R.id.sycw);
        sf = (TextView) findViewById(R.id.sf);
        zg = (TextView) findViewById(R.id.zg);
    }
}
