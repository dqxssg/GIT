package com.example.zhcs.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zhcs.R;
import com.example.zhcs.bean.S_TS1;
import com.example.zhcs.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TS_XZ extends AppCompatActivity {
    private ArrayList<String> wt = new ArrayList<>();
    private ArrayList<S_TS1> s_ts1s = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private TextView back;
    private TextView header;
    private TextView gsmc;
    private TextView ydh;
    private Spinner spring = null;
    private EditText tsnr;
    private TextView tj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ts_xz);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        initView();
        header.setText("新增投诉");
        ydh.setText(name);
        back.setOnClickListener(view -> {
            finish();
        });
        new HttpUtil().sendResultToken("/prod-api/api/logistics-inquiry/logistics_info/" + name, "", "GET", new Callback() {
            @Override
            public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("company");
                    runOnUiThread(() -> {
                        try {
                            gsmc.setText(jsonObject2.getString("name"));
                            new HttpUtil().sendResultToken("/prod-api/system/dict/data/type/li-complaint-question-type", "", "GET", new Callback() {
                                @Override
                                public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {

                                }

                                @Override
                                public void onResponse(@NonNull okhttp3.Call call, @NonNull Response response) throws IOException {
                                    String s = response.body().string();
                                    try {
                                        JSONObject jsonObject = new JSONObject(s);
                                        s_ts1s = new Gson().fromJson(jsonObject.getJSONArray("data").toString(), new TypeToken<List<S_TS1>>() {
                                        }.getType());
                                        for (S_TS1 s_ts1 : s_ts1s) {
                                            wt.add(s_ts1.getDictLabel());
                                        }
                                        adapter = new ArrayAdapter<String>(TS_XZ.this, android.R.layout.simple_spinner_item, wt);
                                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                        ArrayAdapter<String> finalAdapter = adapter;
                                        runOnUiThread(() -> {
                                            spring.setAdapter(finalAdapter);
                                            tj.setOnClickListener(view -> {
                                                int aa = 0;
                                                if (spring.getAutofillValue().equals("快递丢失")) {
                                                    aa = 218;
                                                } else if (spring.getAutofillValue().equals("快递破损")) {
                                                    aa = 219;
                                                } else if (spring.getAutofillValue().equals("快递被拆封")) {
                                                    aa = 220;
                                                } else if (spring.getAutofillValue().equals("工作人员态度恶劣")) {
                                                    aa = 221;
                                                } else if (spring.getAutofillValue().equals("未送货上门")) {
                                                    aa = 222;
                                                }
                                                try {
                                                    JSONObject jsonObject1 = new JSONObject();
                                                    jsonObject1.put("companyId", jsonObject2.getString("id"));
                                                    jsonObject1.put("infoNo", name);
                                                    jsonObject1.put("questionType", aa);
                                                    jsonObject1.put("description", tsnr.getText().toString());
                                                    new HttpUtil().sendResultToken("/prod-api/api/logistics-inquiry/logistics_complaint", jsonObject1.toString(), "POST", new Callback() {
                                                        @Override
                                                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                                        }

                                                        @Override
                                                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                            String s1 = response.body().string();
                                                            try {
                                                                JSONObject jsonObject2 = new JSONObject(s1);
                                                                if (jsonObject2.getString("code").equals("200")) {
                                                                    runOnUiThread(() -> {
                                                                        Toast.makeText(TS_XZ.this, "投诉成功", Toast.LENGTH_SHORT).show();
                                                                        finish();
                                                                    });
                                                                } else {
                                                                    runOnUiThread(() -> {
                                                                        try {
                                                                            Toast.makeText(TS_XZ.this, jsonObject2.getString("msg"), Toast.LENGTH_SHORT).show();
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
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        gsmc = (TextView) findViewById(R.id.gsmc);
        ydh = (TextView) findViewById(R.id.ydh);
        spring = (Spinner) findViewById(R.id.spring);
        tsnr = (EditText) findViewById(R.id.tsnr);
        tj = (TextView) findViewById(R.id.tj);
    }
}
