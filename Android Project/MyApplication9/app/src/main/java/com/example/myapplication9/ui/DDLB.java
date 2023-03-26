package com.example.myapplication9.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication9.R;
import com.example.myapplication9.adapter.Adapter_DDLB_LV;
import com.example.myapplication9.bean.S_DDLB_LV;
import com.example.myapplication9.util.HttpUtil;
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
    private ArrayList<String> type = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private TextView back;
    private TextView header;
    private ListView listview;
    private Spinner spinner = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddlb);
        initView();
        header.setText("订单列表");
        back.setOnClickListener(v -> {
            finish();
        });
        type.add("食品");
        type.add("定制班车");
        type.add("衣服");
        System.out.println(type);
        adapter = new ArrayAdapter<>(DDLB.this, android.R.layout.simple_spinner_item, type);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        runOnUiThread(() -> {
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    s_ddlb_lvs.clear();
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("type", spinner.getSelectedItem().toString());
                        new HttpUtil()
                                .sendResUtil("getOrderByType", jsonObject.toString(), "POST", new Callback() {
                                    @Override
                                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                    }

                                    @Override
                                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                        String s = response.body().string();
                                        try {
                                            JSONObject jsonObject1 = new JSONObject(s);
                                            s_ddlb_lvs = new Gson().fromJson(jsonObject1.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_DDLB_LV>>() {
                                            }.getType());
                                            adapter_ddlb_lv = new Adapter_DDLB_LV(DDLB.this, s_ddlb_lvs);
                                            runOnUiThread(() -> {
                                                listview.setAdapter(adapter_ddlb_lv);
                                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                                    Intent intent = new Intent(DDLB.this, DDLB_XQ.class);
                                                    intent.putExtra("id", s_ddlb_lvs.get(position).getId());
                                                    startActivity(intent);
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
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        listview = (ListView) findViewById(R.id.listview);
        spinner = (Spinner) findViewById(R.id.spinner);
    }
}

