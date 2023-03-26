package com.example.myapplication4.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication4.R;
import com.example.myapplication4.adapter.Adapter_ExpandableListView;
import com.example.myapplication4.bean.S_ZHBS_ER;
import com.example.myapplication4.bean.S_ZHBS_YI;
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

public class ZHBS extends AppCompatActivity {
    private ArrayList<S_ZHBS_YI> s_zhbs_yis = new ArrayList<>();
    private ArrayList<S_ZHBS_ER> s_zhbs_ers = new ArrayList<>();
    private ArrayList<ArrayList<S_ZHBS_ER>> arrayLists = new ArrayList<>();
    private Adapter_ExpandableListView adapter_expandableListView;
    private TextView back;
    private TextView header;
    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhbs);
        initView();
        header.setText("智慧巴士");
        back.setOnClickListener(v -> {
            finish();
        });
        new Httputil()
                .sendResultToken("/prod-api/api/bus/line/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_zhbs_yis = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_ZHBS_YI>>() {
                            }.getType());
                            for (S_ZHBS_YI s_zhbs_yi : s_zhbs_yis) {
                                System.out.println("123"+s_zhbs_yi.getId());
                                new Httputil()
                                        .sendResultToken("/prod-api/api/bus/stop/list?linesId=" + s_zhbs_yi.getId(), "", "GET", new Callback() {
                                            @Override
                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                            }

                                            @Override
                                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                String s = response.body().string();
                                                try {
                                                    JSONObject jsonObject1 = new JSONObject(s);
                                                    s_zhbs_ers = new Gson().fromJson(jsonObject1.getJSONArray("rows").toString(), new TypeToken<List<S_ZHBS_ER>>() {
                                                    }.getType());
                                                    arrayLists.add(s_zhbs_ers);
                                                    adapter_expandableListView = new Adapter_ExpandableListView(s_zhbs_yis, arrayLists);
                                                    runOnUiThread(() -> {
                                                        expandableListView.setAdapter(adapter_expandableListView);
                                                        adapter_expandableListView.setOnItemListener(new Adapter_ExpandableListView.onItemListener() {
                                                            @Override
                                                            public void onClick(int i) {
                                                                Intent intent = new Intent(ZHBS.this,DYB.class);
                                                                intent.putExtra("i", i);
                                                                intent.putExtra("id", s_zhbs_yis.get(i).getId());
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
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
    }
}
