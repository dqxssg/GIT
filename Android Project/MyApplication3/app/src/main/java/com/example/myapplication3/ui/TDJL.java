package com.example.myapplication3.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;
import com.example.myapplication3.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TDJL extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private TextView zwmc;
    private TextView gswz;
    private TextView gsdd;
    private TextView xzdy;
    private TextView lxr;
    private TextView zwms;
    private TextView zwxq;
    private TextView gsmc;
    private TextView gsjj;
    private TextView tjl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tdjl);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        int companyId = bundle.getInt("companyId");
        String name = bundle.getString("name");
        initView();
        header.setText("投递简历");
        back.setOnClickListener(v -> {
            finish();
        });
        //显示
        XS(id);
        //投简历
        TJL(companyId, name, id);
    }

    private void TJL(int companyId, String name, int id) {
        tjl.setOnClickListener(v -> {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date curDate = new Date(System.currentTimeMillis());
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("companyId", companyId);
                jsonObject.put("satrTime", formatter.format(curDate));
                jsonObject.put("postId", id);
                jsonObject.put("postName", name);
                new HttpUtil()
                        .sendResltToken("/prod-api/api/job/deliver", jsonObject.toString(), "POST", new Callback() {
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
                                            Toast.makeText(TDJL.this, "投递简历成功", Toast.LENGTH_SHORT).show();
                                        });
                                    } else {
                                        runOnUiThread(() -> {
                                            try {
                                                Toast.makeText(TDJL.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
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
        });
    }

    private void XS(int id) {
        new HttpUtil()
                .sendResltToken("/prod-api/api/job/post/" + id, "", "GET", new Callback() {
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
                                    zwmc.setText("职位名称：" + jsonObject1.getString("name"));
                                    lxr.setText("联系人：" + jsonObject1.getString("contacts"));
                                    gswz.setText("岗位职责：" + jsonObject1.getString("obligation"));
                                    gsdd.setText("公司地点：" + jsonObject1.getString("address"));
                                    xzdy.setText("薪资待遇：" + jsonObject1.getString("salary"));
                                    zwxq.setText("职位需求：" + jsonObject1.getString("need"));
                                    gsmc.setText("公司名称：" + jsonObject1.getString("companyName"));
                                    new HttpUtil()
                                            .sendResltToken("/prod-api/api/job/company/" + jsonObject1.getString("companyId"), "", "GET", new Callback() {
                                                @Override
                                                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                                }

                                                @Override
                                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                    String s = response.body().string();
                                                    try {
                                                        JSONObject jsonObject2 = new JSONObject(s);
                                                        JSONObject jsonObject3 = jsonObject2.getJSONObject("data");
                                                        runOnUiThread(() -> {
                                                            try {
                                                                gsjj.setText("公司简介：" + jsonObject3.getString("introductory"));
                                                            } catch (JSONException e) {
                                                                throw new RuntimeException(e);
                                                            }
                                                            zwms.setText("职位描述：");
                                                        });
                                                    } catch (JSONException e) {
                                                        throw new RuntimeException(e);
                                                    }

                                                }
                                            });

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
        zwmc = (TextView) findViewById(R.id.zwmc);
        gswz = (TextView) findViewById(R.id.gswz);
        gsdd = (TextView) findViewById(R.id.gsdd);
        xzdy = (TextView) findViewById(R.id.xzdy);
        lxr = (TextView) findViewById(R.id.lxr);
        zwms = (TextView) findViewById(R.id.zwms);
        zwxq = (TextView) findViewById(R.id.zwxq);
        gsmc = (TextView) findViewById(R.id.gsmc);
        gsjj = (TextView) findViewById(R.id.gsjj);
        tjl = (TextView) findViewById(R.id.tjl);
    }
}
