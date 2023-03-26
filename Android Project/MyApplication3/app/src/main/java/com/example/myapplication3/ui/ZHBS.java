package com.example.myapplication3.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;
import com.example.myapplication3.adapter.ZHBS_ExpandableListView;
import com.example.myapplication3.bean.ShuJv_ZHBS_ExpandableListView;
import com.example.myapplication3.bean.ShuJv_ZHBS_ZD;
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

public class ZHBS extends AppCompatActivity {
    private ArrayList<ShuJv_ZHBS_ExpandableListView> shuJv_zhbs_expandableListViews = new ArrayList<>();
    private ArrayList<ArrayList<ShuJv_ZHBS_ZD>> arrayLists = new ArrayList<>();
    private ZHBS_ExpandableListView zhbs_expandableListView;
    private ArrayList<ShuJv_ZHBS_ZD> shuJv_zhbs_zds = new ArrayList<>();
    private ImageView back;
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
                .sendResltToken("/prod-api/api/bus/line/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_zhbs_expandableListViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_ZHBS_ExpandableListView>>() {
                            }.getType());
                            for (int i = 0; i < shuJv_zhbs_expandableListViews.size(); i++) {
                                new HttpUtil()
                                        .sendResltToken("/prod-api/api/bus/stop/list?linesId=" + shuJv_zhbs_expandableListViews.get(i).getId(), "", "GET", new Callback() {
                                            @Override
                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                            }

                                            @Override
                                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                String s = response.body().string();
                                                try {
                                                    JSONObject jsonObject1 = new JSONObject(s);
                                                    shuJv_zhbs_zds = new Gson().fromJson(jsonObject1.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_ZHBS_ZD>>() {
                                                    }.getType());
                                                    arrayLists.add(shuJv_zhbs_zds);
                                                } catch (JSONException e) {
                                                    throw new RuntimeException(e);
                                                }

                                            }
                                        });
                            }
                            zhbs_expandableListView = new ZHBS_ExpandableListView(shuJv_zhbs_expandableListViews, arrayLists);
                            runOnUiThread(() -> {
                                expandableListView.setAdapter(zhbs_expandableListView);
                                zhbs_expandableListView.setOnItemListener(new ZHBS_ExpandableListView.onItemListener() {
                                    @Override
                                    public void onClick(int i) {
                                        Intent intent = new Intent(ZHBS.this, DYB.class);
                                        intent.putExtra("i", i);
                                        intent.putExtra("id", shuJv_zhbs_expandableListViews.get(i).getId());
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
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
    }
}
