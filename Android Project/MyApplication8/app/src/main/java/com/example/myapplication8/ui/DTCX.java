package com.example.myapplication8.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication8.R;
import com.example.myapplication8.adapter.Adapter_DTCX_LV;
import com.example.myapplication8.bean.S_DTCX_LV;
import com.example.myapplication8.util.HttpUtil;
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

public class DTCX extends AppCompatActivity {
    private ArrayList<S_DTCX_LV> s_dtcx_lvs = new ArrayList<>();
    private Adapter_DTCX_LV adapter_dtcx_lv;
    private TextView back;
    private TextView header;
    private TextView add;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dtcq);
        initView();
        header.setText("地铁查询");
        back.setOnClickListener(v -> {
            finish();
        });
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("stationName", "建国门站");
            new HttpUtil()
                    .sendResuilt("getSubwaysByStation", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                s_dtcx_lvs = new Gson().fromJson(jsonObject1.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_DTCX_LV>>() {
                                }.getType());
                                adapter_dtcx_lv = new Adapter_DTCX_LV(DTCX.this, s_dtcx_lvs);
                                runOnUiThread(() -> {
                                    listview.setAdapter(adapter_dtcx_lv);
                                    listview.setOnItemClickListener((parent, view, position, id) -> {
                                        Intent intent = new Intent(DTCX.this,DTCX_XQ.class);
                                        intent.putExtra("id", s_dtcx_lvs.get(position).getSubwayid());
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

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        add = (TextView) findViewById(R.id.add);
        listview = (ListView) findViewById(R.id.listview);
    }
}
