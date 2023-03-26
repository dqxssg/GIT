package com.example.myapplication2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;
import com.example.myapplication2.adapter.Adapter_LV_ListView2;
import com.example.myapplication2.adapter.Adapter_SS_LS_ListView;
import com.example.myapplication2.bean.ShuJv_SS_LS_ListView;
import com.example.myapplication2.util.HttpUtil;
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

public class SS_LS extends AppCompatActivity {
    private ArrayList<ShuJv_SS_LS_ListView> shuJv_ss_ls_listViews = new ArrayList<>();
    private Adapter_SS_LS_ListView adapter_ss_ls_listView;
    private TextView back;
    private TextView header;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ss_ls);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        int id = bundle.getInt("id");
        initView();
        header.setText("律师列表");
        back.setOnClickListener(v -> {
            finish();
        });
        if (name.equals("不显示")) {
            //显示id
            XSID(id);
        } else {
            //显示name
            XSNAME(name);
        }
    }

    private void XSID(int id) {
        new HttpUtil()
                .sendResultToken("/prod-api/api/lawyer-consultation/lawyer/list?legalExpertiseId=" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_ss_ls_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_SS_LS_ListView>>() {
                            }.getType());
                            adapter_ss_ls_listView = new Adapter_SS_LS_ListView(SS_LS.this, shuJv_ss_ls_listViews);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_ss_ls_listView);
                                adapter_ss_ls_listView.setOnItemListener(new Adapter_LV_ListView2.onItemListener() {
                                    @Override
                                    public void onClick(int i) {
                                        Intent intent = new Intent(SS_LS.this, LSXQ.class);
                                        intent.putExtra("id", shuJv_ss_ls_listViews.get(i).getId());
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

    private void XSNAME(String name) {
        new HttpUtil()
                .sendResultToken("/prod-api/api/lawyer-consultation/lawyer/list?name=" + name, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_ss_ls_listViews = new Gson().fromJson(jsonObject.getJSONArray("").toString(), new TypeToken<List<ShuJv_SS_LS_ListView>>() {
                            }.getType());
                            adapter_ss_ls_listView = new Adapter_SS_LS_ListView(SS_LS.this, shuJv_ss_ls_listViews);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_ss_ls_listView);
                                adapter_ss_ls_listView.setOnItemListener(new Adapter_LV_ListView2.onItemListener() {
                                    @Override
                                    public void onClick(int i) {
                                        Intent intent = new Intent(SS_LS.this, LSXQ.class);
                                        intent.putExtra("id", shuJv_ss_ls_listViews.get(i).getId());
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
