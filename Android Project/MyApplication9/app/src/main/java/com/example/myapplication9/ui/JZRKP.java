package com.example.myapplication9.ui;

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

import com.example.myapplication9.R;
import com.example.myapplication9.adapter.Adapter_JZRKP_LV;
import com.example.myapplication9.bean.S_JZRKP_LV;
import com.example.myapplication9.util.HttpUtil;
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

public class JZRKP extends AppCompatActivity {
    private ArrayList<S_JZRKP_LV> s_jzrkp_lvs = new ArrayList<>();
    private Adapter_JZRKP_LV adapter_jzrkp_lv;
    private TextView back;
    private TextView header;
    private ListView listview;
    private TextView tj;
    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jzrkp);
        Bundle bundle = this.getIntent().getExtras();
        id = bundle.getString("id");
        initView();
        header.setText("就诊人卡片");
        back.setOnClickListener(v -> {
            finish();
        });
        tj.setOnClickListener(v -> {
            startActivity(new Intent(JZRKP.this, XZ_JZRKP.class));
        });
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ID", "371402199902041133");
            new HttpUtil()
                    .sendResUtil("showCaseById", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                s_jzrkp_lvs = new Gson().fromJson(jsonObject1.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_JZRKP_LV>>() {
                                }.getType());
                                adapter_jzrkp_lv = new Adapter_JZRKP_LV(JZRKP.this, s_jzrkp_lvs);
                                adapter_jzrkp_lv.setItemOnclick(new Adapter_JZRKP_LV.setOnItemClick() {
                                    @Override
                                    public void onItemclick(int position) {
                                        Intent intent = new Intent(JZRKP.this, MZKSFL.class);
                                        intent.putExtra("id", id);
                                        intent.putExtra("ID", s_jzrkp_lvs.get(position).getID());
                                        intent.putExtra("tel", s_jzrkp_lvs.get(position).getTel());
                                        intent.putExtra("name", s_jzrkp_lvs.get(position).getName());
                                        startActivity(intent);
                                    }
                                });
                                runOnUiThread(() -> {
                                    listview.setAdapter(adapter_jzrkp_lv);
                                    listview.setOnItemClickListener((parent, view, position, id1) -> {
                                        startActivity(new Intent(JZRKP.this, XZ_JZRKP.class));
                                    });
                                    ListAdapter listAdapter = listview.getAdapter();
                                    if (listAdapter == null) {
                                        return;
                                    }
                                    int h = 0;
                                    for (int i = 0; i < s_jzrkp_lvs.size(); i++) {
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
        s_jzrkp_lvs.clear();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ID", "371402199902041133");
            new HttpUtil()
                    .sendResUtil("showCaseById", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                s_jzrkp_lvs = new Gson().fromJson(jsonObject1.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_JZRKP_LV>>() {
                                }.getType());
                                adapter_jzrkp_lv = new Adapter_JZRKP_LV(JZRKP.this, s_jzrkp_lvs);
                                adapter_jzrkp_lv.setItemOnclick(new Adapter_JZRKP_LV.setOnItemClick() {
                                    @Override
                                    public void onItemclick(int position) {
                                        Intent intent = new Intent(JZRKP.this, MZKSFL.class);
                                        intent.putExtra("id", id);
                                        intent.putExtra("ID", s_jzrkp_lvs.get(position).getID());
                                        intent.putExtra("tel", s_jzrkp_lvs.get(position).getTel());
                                        intent.putExtra("name", s_jzrkp_lvs.get(position).getName());
                                        startActivity(intent);
                                    }
                                });
                                runOnUiThread(() -> {
                                    listview.setAdapter(adapter_jzrkp_lv);
                                    listview.setOnItemClickListener((parent, view, position, id1) -> {
                                        startActivity(new Intent(JZRKP.this, XZ_JZRKP.class));
                                    });
                                    ListAdapter listAdapter = listview.getAdapter();
                                    if (listAdapter == null) {
                                        return;
                                    }
                                    int h = 0;
                                    for (int i = 0; i < s_jzrkp_lvs.size(); i++) {
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
        tj = (TextView) findViewById(R.id.tj);
    }
}
