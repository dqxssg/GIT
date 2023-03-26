package com.example.myapplication4.ui;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication4.R;
import com.example.myapplication4.adapter.Adapter_WDYY_LV;
import com.example.myapplication4.bean.S_WDYY_LV;
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

public class WDYY extends AppCompatActivity {
    private ArrayList<S_WDYY_LV> s_wdyy_lvs = new ArrayList<>();
    private Adapter_WDYY_LV adapter_wdyy_lv;
    private TextView back;
    private TextView header;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wdyy);
        initView();
        header.setText("我的预约");
        back.setOnClickListener(v -> {
            finish();
        });
        new Httputil()
                .sendResultToken("/prod-api/api/traffic/checkCar/apply/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_wdyy_lvs = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_WDYY_LV>>() {
                            }.getType());
                            adapter_wdyy_lv = new Adapter_WDYY_LV(WDYY.this, s_wdyy_lvs);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_wdyy_lv);
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
