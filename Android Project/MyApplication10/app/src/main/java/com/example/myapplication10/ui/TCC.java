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
import com.example.myapplication10.adapter.Adapter_TCC_LV;
import com.example.myapplication10.bean.S_TCC_LV;
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

public class TCC extends AppCompatActivity {
    private ArrayList<S_TCC_LV> s_tcc_lvs = new ArrayList<>();
    private Adapter_TCC_LV adapter_tcc_lv;
    private TextView back;
    private TextView header;
    private TextView add;
    private ListView listview;
    private TextView ckgd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tcc);
        initView();
        header.setText("停车场");
        back.setOnClickListener(v -> {
            finish();
        });
        add.setOnClickListener(v -> {
            startActivity(new Intent(TCC.this, TCJL.class));
        });
        ckgd.setOnClickListener(v -> {
            ckgd.setVisibility(View.INVISIBLE);
            s_tcc_lvs.clear();
            new HttpUtil()
                    .sendResUtil("getParkInfor", "", "GET", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                s_tcc_lvs = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_TCC_LV>>() {
                                }.getType());
                                adapter_tcc_lv = new Adapter_TCC_LV(TCC.this, s_tcc_lvs, s_tcc_lvs.size());
                                runOnUiThread(() -> {
                                    listview.setAdapter(adapter_tcc_lv);
                                    listview.setOnItemClickListener((parent, view, position, id) -> {
                                        Intent intent = new Intent(TCC.this, TCC_XQ.class);
                                        intent.putExtra("id", s_tcc_lvs.get(position).getParkingid());
                                        startActivity(intent);
                                    });
                                    ListAdapter listAdapter = listview.getAdapter();
                                    if (listAdapter == null) {
                                        return;
                                    }
                                    int h = 0;
                                    for (int i = 0; i < s_tcc_lvs.size(); i++) {
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
        });
        new HttpUtil()
                .sendResUtil("getParkInfor", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_tcc_lvs = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_TCC_LV>>() {
                            }.getType());
                            adapter_tcc_lv = new Adapter_TCC_LV(TCC.this, s_tcc_lvs, 5);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_tcc_lv);
                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(TCC.this, TCC_XQ.class);
                                    intent.putExtra("id", s_tcc_lvs.get(position).getParkingid());
                                    startActivity(intent);
                                });
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < 5; i++) {
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
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        add = (TextView) findViewById(R.id.add);
        listview = (ListView) findViewById(R.id.listview);
        ckgd = (TextView) findViewById(R.id.ckgd);
    }
}
