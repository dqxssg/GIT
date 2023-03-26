package com.example.myapplication10.ui;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication10.R;
import com.example.myapplication10.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DSIB extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView ckxx;
    private TextView sjhm;
    private TextView scdd;
    private TextView xcdd;
    private TextView ccrq;
    private TextView xyb;
    private TextView fh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dsib);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        String ckxm1 = bundle.getString("ckxm");
        String sjhm1 = bundle.getString("sjhm");
        String scdd1 = bundle.getString("scdd");
        String xcdd1 = bundle.getString("xcdd");
        String date1 = bundle.getString("data");
        initView();
        header.setText("智慧巴士");
        fh.setOnClickListener(v -> {
            finish();
        });
        back.setOnClickListener(v -> {
            finish();
        });
        runOnUiThread(() -> {
            ckxx.setText("乘客姓名：" + ckxm1);
            sjhm.setText("手机号码：" + sjhm1);
            scdd.setText("上车地点：" + scdd1);
            xcdd.setText("下车地点：" + xcdd1);
            ccrq.setText("乘车日期：" + date1);
        });
        xyb.setOnClickListener(v -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("busid", id);
                jsonObject.put("name", ckxm1);
                jsonObject.put("phone", sjhm1);
                jsonObject.put("upsite", scdd1);
                jsonObject.put("downsite", xcdd1);
                jsonObject.put("traveltime", date1);
                jsonObject.put("isPay", "N");
                new HttpUtil()
                        .sendResUtil("setOrderBus", jsonObject.toString(), "POST", new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                String s = response.body().string();
                                try {
                                    JSONObject jsonObject1 = new JSONObject(s);
                                    if (Objects.equals(jsonObject1.getString("RESULT"), "S")) {
                                        runOnUiThread(() -> {
                                            Toast.makeText(DSIB.this, "提交成功", Toast.LENGTH_SHORT).show();
                                        });
                                    }
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }


                            }
                        });
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        ckxx = (TextView) findViewById(R.id.ckxx);
        sjhm = (TextView) findViewById(R.id.sjhm);
        scdd = (TextView) findViewById(R.id.scdd);
        xcdd = (TextView) findViewById(R.id.xcdd);
        ccrq = (TextView) findViewById(R.id.ccrq);
        xyb = (TextView) findViewById(R.id.xyb);
        fh = (TextView) findViewById(R.id.fh);
    }
}
