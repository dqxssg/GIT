package com.example.myapplication9.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication9.R;
import com.example.myapplication9.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class YYCG extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView ks;
    private TextView sj;
    private TextView yycg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yycg);
        Bundle bundle = this.getIntent().getExtras();
        String time = bundle.getString("time");
        String deptid = bundle.getString("deptid");
        String deptname = bundle.getString("deptname");
        String id = bundle.getString("ID");
        String tel = bundle.getString("tel");
        String name = bundle.getString("name");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pid", id);
            jsonObject.put("name", name);
            jsonObject.put("phone", tel);
            jsonObject.put("doctorId", deptid);
            jsonObject.put("appTime", time);
            new HttpUtil()
                    .sendResUtil("appointment", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                System.out.println("123+" + jsonObject1);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        initView();
        header.setText("挂号");
        ks.setText("预约科室：" + deptname);
        sj.setText("预约时间：" + time);
        back.setOnClickListener(v -> {
            finish();
        });
        yycg.setOnClickListener(v -> {
            startActivity(new Intent(YYCG.this, MZYY.class));
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        ks = (TextView) findViewById(R.id.ks);
        sj = (TextView) findViewById(R.id.sj);
        yycg = (TextView) findViewById(R.id.yycg);
    }
}
