package com.example.myapplication10.ui;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication10.R;
import com.example.myapplication10.adapter.Adapter_A_LV;
import com.example.myapplication10.bean.S_A_LV;
import com.example.myapplication10.bean.S_A_LV_QT;
import com.example.myapplication10.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class A_SS extends AppCompatActivity {
    private ArrayList<S_A_LV_QT> s_a_lv_qtArrayList1 = new ArrayList<>();
    private ArrayList<S_A_LV_QT> s_a_lv_qtArrayList2 = new ArrayList<>();
    private ArrayList<S_A_LV> s_a_lvArrayList1 = new ArrayList<>();
    private ArrayList<S_A_LV> s_a_lvArrayList2 = new ArrayList<>();
    private Adapter_A_LV adapter_a_lv;
    private TextView back;
    private TextView header;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ss);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        initView();
        header.setText("详情");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResUtil("getNEWsList", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_a_lvArrayList1 = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_A_LV>>() {
                            }.getType());
                            for (S_A_LV s_a_lv : s_a_lvArrayList1) {
                                boolean matches = Pattern.matches(".*" + name + ".*", s_a_lv.getTitle());
                                if (matches) {
                                    s_a_lvArrayList2.add(s_a_lv);
                                }
                            }
                            for (S_A_LV s_a_lv : s_a_lvArrayList2) {
                                JSONObject jsonObject1 = new JSONObject();
                                jsonObject1.put("newsid", s_a_lv.getNewsid());
                                new HttpUtil()
                                        .sendResUtil("getNewsInfoById", jsonObject1.toString(), "POST", new Callback() {
                                            @Override
                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                            }

                                            @Override
                                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                String s1 = response.body().string();
                                                try {
                                                    JSONObject jsonObject2 = new JSONObject(s1);
                                                    s_a_lv_qtArrayList1 = new Gson().fromJson(jsonObject2.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_A_LV_QT>>() {
                                                    }.getType());
                                                    for (S_A_LV_QT s_a_lv_qt : s_a_lv_qtArrayList1) {
                                                        s_a_lv_qtArrayList2.add(s_a_lv_qt);
                                                    }
                                                    if (Objects.equals(s_a_lvArrayList2.size(), s_a_lv_qtArrayList2.size())) {
                                                        adapter_a_lv = new Adapter_A_LV(A_SS.this, s_a_lvArrayList2, s_a_lv_qtArrayList2);
                                                        runOnUiThread(() -> {
                                                            listview.setAdapter(adapter_a_lv);
                                                        });
                                                    }
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
        listview = (ListView) findViewById(R.id.listview);
    }
}

