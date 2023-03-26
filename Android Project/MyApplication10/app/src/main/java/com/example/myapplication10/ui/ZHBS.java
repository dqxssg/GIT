package com.example.myapplication10.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication10.R;
import com.example.myapplication10.adapter.Adapter_ZHBS_ELV;
import com.example.myapplication10.bean.S_ZHBS_E;
import com.example.myapplication10.bean.S_ZHBS_Y;
import com.example.myapplication10.util.HttpUtil;
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
    private ArrayList<S_ZHBS_Y> s_zhbs_is = new ArrayList<>();
    private ArrayList<S_ZHBS_E> s_zhbs_es = new ArrayList<>();
    private ArrayList<ArrayList<S_ZHBS_E>> arrayLists = new ArrayList<>();
    private Adapter_ZHBS_ELV adapter_zhbs_elv;
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
        new HttpUtil()
                .sendResUtil("busList", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_zhbs_is = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_ZHBS_Y>>() {
                            }.getType());
                            for (S_ZHBS_Y s_zhbs_i : s_zhbs_is) {
                                JSONObject jsonObject1 = new JSONObject();
                                jsonObject1.put("busid", s_zhbs_i.getBusid());
                                new HttpUtil()
                                        .sendResUtil("busStationById", jsonObject1.toString(), "POST", new Callback() {
                                            @Override
                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                            }

                                            @Override
                                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                String s1 = response.body().string();
                                                try {
                                                    JSONObject jsonObject2 = new JSONObject(s1);
                                                    s_zhbs_es = new Gson().fromJson(jsonObject2.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_ZHBS_E>>() {
                                                    }.getType());
                                                    arrayLists.add(s_zhbs_es);
                                                    adapter_zhbs_elv = new Adapter_ZHBS_ELV(s_zhbs_is, arrayLists);
                                                    runOnUiThread(() -> {
                                                        expandableListView.setAdapter(adapter_zhbs_elv);
                                                        adapter_zhbs_elv.setOnItemListener(new Adapter_ZHBS_ELV.onItemListener() {
                                                            @Override
                                                            public void onClick(int position) {
                                                                Intent intent = new Intent(ZHBS.this, DYB.class);
                                                                intent.putExtra("id", s_zhbs_is.get(position).getBusid());
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
