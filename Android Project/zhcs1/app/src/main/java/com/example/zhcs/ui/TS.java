package com.example.zhcs.ui;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zhcs.R;
import com.example.zhcs.adapter.Adapter_TS;
import com.example.zhcs.bean.S_TS;
import com.example.zhcs.util.HttpUtil;
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

public class TS extends AppCompatActivity {
    private ArrayList<S_TS> s_tsArrayList = new ArrayList<>();
    private Adapter_TS adapter_ts;
    private TextView back;
    private TextView header;
    private ListView listview;
    private TextView xz;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ts);
        initView();
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        header.setText("投诉历史");
        back.setOnClickListener(view -> {
            finish();
        });
        xz.setOnClickListener(view -> {
            Intent intent = new Intent(TS.this, TS_XZ.class);
            intent.putExtra("name", name);
            startActivity(intent);
        });
        new HttpUtil()
                .sendResultToken("/prod-api/api/logistics-inquiry/logistics_complaint/my-list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_tsArrayList = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_TS>>() {
                            }.getType());
                            adapter_ts = new Adapter_TS(TS.this, s_tsArrayList);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_ts);
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
        xz = (TextView) findViewById(R.id.xz);
    }
}

