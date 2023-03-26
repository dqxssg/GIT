package com.example.zhcs.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zhcs.R;
import com.example.zhcs.adapter.Adapter_CSDT_LV;
import com.example.zhcs.bean.S_CSDT_LV;
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

public class CSDT extends AppCompatActivity {
    private ArrayList<S_CSDT_LV> s_csdt_lvs = new ArrayList<>();
    private Adapter_CSDT_LV adapter_csdt_lv;
    private TextView back;
    private TextView header;
    private ListView listview;
    private LinearLayout line;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.csdt);
        initView();
        header.setText("城市地铁");
        back.setOnClickListener(v -> {
            finish();
        });
        line.setOnClickListener(v -> {
            startActivity(new Intent(CSDT.this, DT.class));
        });
        new HttpUtil()
                .sendResultToken("/prod-api/api/metro/list?currentName=建国门", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_csdt_lvs = new Gson().fromJson(jsonObject.getJSONArray("data").toString(), new TypeToken<List<S_CSDT_LV>>() {
                            }.getType());
                            adapter_csdt_lv = new Adapter_CSDT_LV(CSDT.this, s_csdt_lvs);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_csdt_lv);
                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(CSDT.this, DTXQ.class);
                                    intent.putExtra("name", s_csdt_lvs.get(position).getLineName());
                                    intent.putExtra("id", s_csdt_lvs.get(position).getLineId());
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
        line = (LinearLayout) findViewById(R.id.line);
    }
}
