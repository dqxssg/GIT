package com.example.myapplication4.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication4.R;
import com.example.myapplication4.adapter.Adapter_LJYY_Dialog;
import com.example.myapplication4.adapter.Adapter_LJYY_LV;
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

public class LJYY extends AppCompatActivity {
    private ArrayList<S_LJYY_LV> s_ljyy_lvs = new ArrayList<>();
    private Adapter_LJYY_LV adapter_ljyy_lv;
    private TextView back;
    private TextView header;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ljyy);
        initView();
        header.setText("立即预约");
        back.setOnClickListener(v -> {
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
                            adapter_ljyy_lv = new Adapter_LJYY_LV(LJYY.this, s_ljyy_lvs);
                            runOnUiThread(() -> {
                                adapter_ljyy_lv.setOnItemListener(new Adapter_LJYY_LV.onItemListener() {
                                    @Override
                                    public void onClick(int i) {
                                        Intent intent = new Intent(LJYY.this, Adapter_LJYY_Dialog.class);
                                        intent.putExtra("id", s_ljyy_lvs.get(i).getId());
                                        startActivity(intent);
                                    }
                                });
                                listview.setAdapter(adapter_ljyy_lv);
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
