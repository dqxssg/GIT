package com.example.myapplication2.ui;

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

import com.example.myapplication2.R;
import com.example.myapplication2.adapter.Adapter_TNE_ListView;
import com.example.myapplication2.bean.ShuJv_TNE_ListView;
import com.example.myapplication2.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TNE extends AppCompatActivity {
    private ArrayList<ShuJv_TNE_ListView> shuJv_tne_listViews = new ArrayList<>();
    private ArrayList<ShuJv_TNE_ListView> shuJv_tne_listViews1 = new ArrayList<>();
    private Adapter_TNE_ListView adapter_tne_listView;
    private TextView back;
    private TextView header;
    private TextView lb;
    private ListView listview;
    private TextView ckgd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tne);
        initView();
        header.setText("停哪儿");
        lb.setOnClickListener(v -> {
            startActivity(new Intent(TNE.this, TNEJL.class));
        });
        back.setOnClickListener(v -> {
            finish();
        });
        ckgd.setOnClickListener(v -> {
            ckgd.setVisibility(View.INVISIBLE);
            XSTCCLB(shuJv_tne_listViews.size());
        });
        //显示停车场列表
        XSTCCLB(6);
    }

    private void XSTCCLB(int j) {
        new HttpUtil()
                .sendResultToken("/prod-api/api/park/lot/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_tne_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_TNE_ListView>>() {
                            }.getType());
                            shuJv_tne_listViews.sort(new Comparator<ShuJv_TNE_ListView>() {
                                @Override
                                public int compare(ShuJv_TNE_ListView o1, ShuJv_TNE_ListView o2) {
//                                    return o1.getDistance().compareTo(o2.getDistance());
                                    return Integer.parseInt(o1.getDistance()) - Integer.parseInt(o2.getDistance());
                                }
                            });
                            for (ShuJv_TNE_ListView shuJv_tne_listView : shuJv_tne_listViews) {
                                if (shuJv_tne_listView != null) {
                                    shuJv_tne_listViews1.add(shuJv_tne_listView);
                                }
                            }
                            adapter_tne_listView = new Adapter_TNE_ListView(TNE.this, shuJv_tne_listViews1, j);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_tne_listView);
                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(TNE.this, TNEXQ.class);
                                    intent.putExtra("id", shuJv_tne_listViews1.get(position).getId());
                                    startActivity(intent);
                                });
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < j; i++) {
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
        lb = (TextView) findViewById(R.id.lb);
        listview = (ListView) findViewById(R.id.listview);
        ckgd = (TextView) findViewById(R.id.ckgd);
    }
}
