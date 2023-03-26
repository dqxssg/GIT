package com.example.myapplication9.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication9.R;
import com.example.myapplication9.adapter.Adapter_WZCX_LB_LV;
import com.example.myapplication9.bean.S_WZCX_LB_LV;
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

public class WZCX_LB extends AppCompatActivity {
    private ArrayList<S_WZCX_LB_LV> s_wzcx_lb_lvs = new ArrayList<>();
    private Adapter_WZCX_LB_LV adapter_wzcx_lb_lv;
    private TextView back;
    private TextView header;
    private ListView listview;
    private TextView clgd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wzcx_lb);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        initView();
        header.setText("违章记录");
        back.setOnClickListener(v -> {
            finish();
        });
        clgd.setOnClickListener(v -> {
            clgd.setVisibility(View.VISIBLE);
            try {
                LV(s_wzcx_lb_lvs.size(), name);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
        //listview
        try {
            LV(5, name);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void LV(int count, String name) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("carid", name);
        new HttpUtil()
                .sendResUtil("getViolationsByCarId", jsonObject.toString(), "POST", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_wzcx_lb_lvs = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_WZCX_LB_LV>>() {
                            }.getType());
                            adapter_wzcx_lb_lv = new Adapter_WZCX_LB_LV(WZCX_LB.this, s_wzcx_lb_lvs, count);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_wzcx_lb_lv);
                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(WZCX_LB.this, WZCX_XQ.class);
                                    intent.putExtra("id", s_wzcx_lb_lvs.get(position).getId());
                                    intent.putExtra("name", name);
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
        clgd = (TextView) findViewById(R.id.clgd);
    }
}
