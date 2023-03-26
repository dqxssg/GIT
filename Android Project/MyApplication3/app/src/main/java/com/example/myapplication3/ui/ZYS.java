package com.example.myapplication3.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;
import com.example.myapplication3.adapter.ZYS_ListView;
import com.example.myapplication3.bean.ShuJv_ZYS_ListView;
import com.example.myapplication3.util.HttpUtil;
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
    private ArrayList<ShuJv_ZYS_ListView> shuJv_zys_listViews = new ArrayList<>();
    private ZYS_ListView zys_listView;
    private ImageView back;
    private TextView header;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zys);
        initView();
        header.setText("找医生");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResltToken("/prod-api/api/pet-hospital/pet-doctor/list?pageSize=10&pageNum=1", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_zys_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_ZYS_ListView>>() {
                            }.getType());
                            zys_listView = new ZYS_ListView(ZYS.this, shuJv_zys_listViews);
                            runOnUiThread(() -> {
                                listview.setAdapter(zys_listView);
                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(ZYS.this,WZ.class);
                                    intent.putExtra("name", shuJv_zys_listViews.get(position).getName());
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
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        listview = (ListView) findViewById(R.id.listview);
    }
}
