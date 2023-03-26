package com.example.myapplication6.ui;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication6.R;
import com.example.myapplication6.adapter.Adapter_DDLB_LV;
import com.example.myapplication6.bean.S_DDLB_LV;
import com.example.myapplication6.util.HttpUtil;
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

public class DDLB extends AppCompatActivity {
    private ArrayList<S_DDLB_LV> s_ddlb_lvs = new ArrayList<>();
    private Adapter_DDLB_LV adapter_ddlb_lv;
    private TextView back;
    private TextView header;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddlb);
        initView();
        header.setText("订单列表");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResurltToken("/prod-api/api/allorder/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_ddlb_lvs = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_DDLB_LV>>() {
                            }.getType());
                            adapter_ddlb_lv = new Adapter_DDLB_LV(DDLB.this, s_ddlb_lvs);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_ddlb_lv);
                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(DDLB.this,DDLB_XQ.class);
                                    intent.putExtra("no", s_ddlb_lvs.get(position).getOrderNo());
                                    startActivity(intent);
                                });
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
        listview = (ListView) findViewById(R.id.listview);
    }
}

