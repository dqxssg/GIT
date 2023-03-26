package com.example.myapplication7.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication7.R;
import com.example.myapplication7.adapter.Adapter_XQLB_LV;
import com.example.myapplication7.bean.S_XQLB_LV;
import com.example.myapplication7.util.HttpUtil;
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

public class SQLB extends AppCompatActivity {
    private ArrayList<S_XQLB_LV> s_xqlb_lvs = new ArrayList<>();
    private Adapter_XQLB_LV adapter_xqlb_lv;
    private TextView back;
    private TextView header;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlb);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("述求列表");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResuiltToken("/prod-api/api/gov-service-hotline/appeal/list?appealCategoryId=" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_xqlb_lvs = new Gson().fromJson(jsonObject.getString("rows"), new TypeToken<List<S_XQLB_LV>>() {
                            }.getType());
                            adapter_xqlb_lv = new Adapter_XQLB_LV(SQLB.this, s_xqlb_lvs);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_xqlb_lv);
                                listview.setOnItemClickListener((parent, view, position, id1) -> {
                                    Intent intent = new Intent(SQLB.this,SQLB_XQ.class);
                                    intent.putExtra("id", s_xqlb_lvs.get(position).getId());
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
