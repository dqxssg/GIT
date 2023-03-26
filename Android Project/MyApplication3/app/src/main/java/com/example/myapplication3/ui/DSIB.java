package com.example.myapplication3.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;
import com.example.myapplication3.bean.ShuJv_ZHBS_ExpandableListView;
import com.example.myapplication3.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DSIB extends AppCompatActivity {
    private ArrayList<ShuJv_ZHBS_ExpandableListView> shuJv_zhbs_expandableListViews = new ArrayList<>();
    private ImageView back;
    private TextView header;
    private TextView ckxx;
    private TextView xyb;
    private TextView fh;
    private TextView sjhm;
    private TextView scdd;
    private TextView xcdd;
    private TextView ccrq;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dsib);
        Bundle bundle = this.getIntent().getExtras();
        int i = bundle.getInt("i");
        int id = bundle.getInt("id");
        String ckxm1 = bundle.getString("ckxm");
        String sjhm1 = bundle.getString("sjhm");
        String scdd1 = bundle.getString("scdd");
        String xcdd1 = bundle.getString("xcdd");
        String date1 = bundle.getString("date");
        String time1 = bundle.getString("time");
        initView();
        header.setText("第四步");
        back.setOnClickListener(v -> {
            finish();
        });
        fh.setOnClickListener(v -> {
            finish();
        });
        runOnUiThread(() -> {
            ckxx.setText("乘客姓名：" + ckxm1);
            sjhm.setText("手机号码：" + sjhm1);
            scdd.setText("上车地点：" + scdd1);
            xcdd.setText("下车地点：" + xcdd1);
            ccrq.setText("乘车日期：" + date1 + time1);
        });
        xyb.setOnClickListener(v -> {
            new HttpUtil()
                    .sendResltToken("/prod-api/api/bus/line/list", "", "GET", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                shuJv_zhbs_expandableListViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_ZHBS_ExpandableListView>>() {
                                }.getType());
                                JSONObject jsonObject2 = new JSONObject();
                                try {
                                    jsonObject2.put("start", scdd1);
                                    jsonObject2.put("end", xcdd1);
                                    jsonObject2.put("price", id);
                                    jsonObject2.put("price", shuJv_zhbs_expandableListViews.get(i).getPrice());
                                    jsonObject2.put("path", shuJv_zhbs_expandableListViews.get(i).getName());
                                    jsonObject2.put("status", 1);
                                    new HttpUtil()
                                            .sendResltToken("/prod-api/api/bus/order", jsonObject2.toString(), "POST", new Callback() {
                                                @Override
                                                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                                }

                                                @Override
                                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                    String s = response.body().string();
                                                    try {
                                                        JSONObject jsonObject1 = new JSONObject(s);
                                                        if (jsonObject1.getString("code").equals("200")) {
                                                            runOnUiThread(() -> {
                                                                Toast.makeText(DSIB.this, "提交成功", Toast.LENGTH_SHORT).show();
                                                            });
                                                        } else {
                                                            runOnUiThread(() -> {
                                                                try {
                                                                    Toast.makeText(DSIB.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
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
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        ckxx = (TextView) findViewById(R.id.ckxx);
        xyb = (TextView) findViewById(R.id.xyb);
        fh = (TextView) findViewById(R.id.fh);
        sjhm = (TextView) findViewById(R.id.sjhm);
        scdd = (TextView) findViewById(R.id.scdd);
        xcdd = (TextView) findViewById(R.id.xcdd);
        ccrq = (TextView) findViewById(R.id.ccrq);
    }
}
