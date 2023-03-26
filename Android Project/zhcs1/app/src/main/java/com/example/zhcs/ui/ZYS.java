package com.example.zhcs.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zhcs.R;
import com.example.zhcs.adapter.Adapter_ZYS_LV;
import com.example.zhcs.bean.S_ZYS_LV;
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

public class ZYS extends AppCompatActivity {
    private ArrayList<S_ZYS_LV> shuJv_zys_listViews = new ArrayList<>();
    private Adapter_ZYS_LV zys_listView;
    private TextView back;
    private TextView header;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zys);
        initView();
        header.setText("找医生");
        back.setOnClickListener(view -> {
            finish();
        });
        new HttpUtil()
                .sendResultToken("/prod-api/api/pet-hospital/pet-doctor/list?pageSize=10&pageNum=1", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_zys_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_ZYS_LV>>() {
                            }.getType());
                            runOnUiThread(() -> {
                                zys_listView = new Adapter_ZYS_LV(ZYS.this, shuJv_zys_listViews);
                                listview.setAdapter(zys_listView);
                                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(ZYS.this,WZ.class);
                                        intent.putExtra("name", shuJv_zys_listViews.get(position).getName());
                                        startActivity(intent);
                                    }
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

