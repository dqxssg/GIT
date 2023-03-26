package com.example.myapplication10.ui;

import android.content.Intent;
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
import com.example.myapplication10.adapter.Adapter_HHFL_LV;
import com.example.myapplication10.bean.S_HHFL_LV;
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

public class HHGL extends AppCompatActivity {
    private ArrayList<S_HHFL_LV> s_hhfl_lvs = new ArrayList<>();
    private Adapter_HHFL_LV adapter_hhfl_lv;
    private TextView back;
    private TextView header;
    private ListView listview;
    private TextView j;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hhgl);
        initView();
        header.setText("户号管理");
        back.setOnClickListener(v -> {
            finish();
        });
        j.setOnClickListener(v -> {
            startActivity(new Intent(HHGL.this,HHFZ.class));
        });
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userid", "2");
            new HttpUtil()
                    .sendResUtil("accountGroup", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                s_hhfl_lvs = new Gson().fromJson(jsonObject1.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_HHFL_LV>>() {
                                }.getType());
                                adapter_hhfl_lv = new Adapter_HHFL_LV(HHGL.this, s_hhfl_lvs);
                                runOnUiThread(() -> {
                                    listview.setAdapter(adapter_hhfl_lv);
                                    ListAdapter listAdapter = listview.getAdapter();
                                    if (listAdapter == null) {
                                        return;
                                    }
                                    int h = 0;
                                    for (int i = 0; i < s_hhfl_lvs.size(); i++) {
                                        View item = listAdapter.getView(i, null, listview);
                                        item.measure(1, 1);
                                        h += item.getMeasuredHeight();
                                    }
                                    ViewGroup.LayoutParams params = listview.getLayoutParams();
                                    params.height = h + listview.getDividerHeight() * (listAdapter.getCount() - 1);
                                    listview.setLayoutParams(params);
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

    @Override
    protected void onStart() {
        super.onStart();
        s_hhfl_lvs.clear();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userid", "2");
            new HttpUtil()
                    .sendResUtil("accountGroup", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                s_hhfl_lvs = new Gson().fromJson(jsonObject1.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_HHFL_LV>>() {
                                }.getType());
                                adapter_hhfl_lv = new Adapter_HHFL_LV(HHGL.this, s_hhfl_lvs);
                                runOnUiThread(() -> {
                                    listview.setAdapter(adapter_hhfl_lv);
                                    ListAdapter listAdapter = listview.getAdapter();
                                    if (listAdapter == null) {
                                        return;
                                    }
                                    int h = 0;
                                    for (int i = 0; i < s_hhfl_lvs.size(); i++) {
                                        View item = listAdapter.getView(i, null, listview);
                                        item.measure(1, 1);
                                        h += item.getMeasuredHeight();
                                    }
                                    ViewGroup.LayoutParams params = listview.getLayoutParams();
                                    params.height = h + listview.getDividerHeight() * (listAdapter.getCount() - 1);
                                    listview.setLayoutParams(params);
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
        listview = (ListView) findViewById(R.id.listview);
        j = (TextView) findViewById(R.id.j);
    }
}
