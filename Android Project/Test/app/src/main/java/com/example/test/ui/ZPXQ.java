package com.example.test.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.test.R;
import com.example.test.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZPXQ extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private TextView gwmc;
    private TextView gwzz;
    private TextView gsdd;
    private TextView xzdy;
    private TextView lxr;
    private TextView zwms;
    private TextView gwxq;
    private TextView gsmc;
    private TextView gsjj;
    private TextView tjl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zpxq);
        initView();
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        int companyid = bundle.getInt("companyid");
        String name = bundle.getString("name");
        String companyname = bundle.getString("companyname");
        header.setText("招聘详情");
        back.setOnClickListener(view -> {
            finish();
        });
        //显示信息
        XSXX(id, companyname);
        //提交
        TJ(id, companyid, name);
    }

    //提交
    private void TJ(int id, int companyid, String name) {
        tjl.setOnClickListener(view -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String satrTime = simpleDateFormat.format(date);
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("companyId", companyid);
                jsonObject.put("satrTime", satrTime);
                jsonObject.put("postId", id);
                jsonObject.put("postName", name);
                new HttpUtil()
                        .sendResulToken("/prod-api/api/job/deliver", jsonObject.toString(), "POST", new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                try {
                                    JSONObject jsonObject1 = new JSONObject(response.body().string());
                                    if (jsonObject1.getString("code").equals("200")) {
                                        runOnUiThread(() -> {
                                            Toast.makeText(ZPXQ.this, "投简历成功", Toast.LENGTH_SHORT).show();
                                        });
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    //显示信息
    private void XSXX(int id, String companyname) {
        new HttpUtil()
                .sendResulToken("/prod-api/api/job/company/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            JSONArray jsonArray = jsonObject.getJSONArray("rows");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                if (jsonArray.getJSONObject(i).getString("companyName").equals(companyname)) {
                                    int finalI = i;
                                    runOnUiThread(() -> {
                                        try {
                                            gsmc.setText(jsonArray.getJSONObject(finalI).getString("companyName"));
                                            gsjj.setText(jsonArray.getJSONObject(finalI).getString("introductory"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    });
                                    break;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        new HttpUtil()
                .sendResulToken("/prod-api/api/job/post/" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            runOnUiThread(() -> {
                                try {
                                    gwmc.setText(jsonObject1.getString("name"));
                                    gwzz.setText(jsonObject1.getString("obligation"));
                                    gsdd.setText(jsonObject1.getString("address"));
                                    xzdy.setText(jsonObject1.getString("salary"));
                                    lxr.setText(jsonObject1.getString("contacts"));
                                    zwms.setText(jsonObject1.getString("need"));
                                    gwxq.setText(jsonObject1.getString("need"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    private void initView() {
        back = findViewById(R.id.back);
        header = findViewById(R.id.header);
        gwmc = findViewById(R.id.gwmc);
        gwzz = findViewById(R.id.gwzz);
        gsdd = findViewById(R.id.gsdd);
        xzdy = findViewById(R.id.xzdy);
        lxr = findViewById(R.id.lxr);
        zwms = findViewById(R.id.zwms);
        gwxq = findViewById(R.id.gwxq);
        gsmc = findViewById(R.id.gsmc);
        gsjj = findViewById(R.id.gsjj);
        tjl = findViewById(R.id.tjl);
    }
}