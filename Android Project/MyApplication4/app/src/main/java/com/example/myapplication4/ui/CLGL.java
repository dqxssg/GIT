package com.example.myapplication4.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication4.R;
import com.example.myapplication4.adapter.Adapter_CLGL_LV;
import com.example.myapplication4.bean.S_LJYY_LV;
import com.example.myapplication4.util.Httputil;
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

public class CLGL extends AppCompatActivity {
    private ArrayList<S_LJYY_LV> s_ljyy_lvs = new ArrayList<>();
    private Adapter_CLGL_LV adapter_clgl_lv;
    private TextView back;
    private TextView header;
    private TextView add;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clgl);
        initView();
        header.setText("车辆管理");
        back.setOnClickListener(v -> {
            finish();
        });
        add.setOnClickListener(v -> {
            startActivity(new Intent(CLGL.this, TJCL.class));
            finish();
        });
        new Httputil()
                .sendResultToken("/prod-api/api/traffic/car/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_ljyy_lvs = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_LJYY_LV>>() {
                            }.getType());
                            adapter_clgl_lv = new Adapter_CLGL_LV(CLGL.this, s_ljyy_lvs);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_clgl_lv);
                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(CLGL.this,XGCL.class);
                                    intent.putExtra("id", s_ljyy_lvs.get(position).getId());
                                    intent.putExtra("PlateNo", s_ljyy_lvs.get(position).getPlateNo());
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
        add = (TextView) findViewById(R.id.add);
        listview = (ListView) findViewById(R.id.listview);
    }
}
