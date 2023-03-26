package com.example.myapplication4.ui;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication4.R;
import com.example.myapplication4.adapter.Adapter_ZYHD_LV;
import com.example.myapplication4.bean.S_ZYHD_LV;
import com.example.myapplication4.util.Httputil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZYHD extends AppCompatActivity {
    private ArrayList<S_ZYHD_LV> s_zyhd_lvs = new ArrayList<>();
    private ArrayList<S_ZYHD_LV> s_zyhd_lvs1 = new ArrayList<>();
    private Adapter_ZYHD_LV adapter_zyhd_lv;
    private TextView back;
    private TextView header;
    private EditText ss;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zyhd);
        initView();
        header.setText("志愿活动");
        back.setOnClickListener(v -> {
            finish();
        });
        //listview
        LV("");
        //搜索
        SS();
    }

    private void SS() {
        ss.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                LV(ss.getText().toString());
                return true;
            }
            return false;
        });
    }

    private void LV(String name) {
        s_zyhd_lvs.clear();
        s_zyhd_lvs1.clear();
        new Httputil()
                .sendResultToken("/prod-api/api/volunteer-service/activity/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_zyhd_lvs = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_ZYHD_LV>>() {
                            }.getType());
                            for (S_ZYHD_LV s_zyhd_lv : s_zyhd_lvs) {
                                boolean matches = Pattern.matches(".*" + name + ".*", s_zyhd_lv.getTitle());
                                if (matches) {
                                    s_zyhd_lvs1.add(s_zyhd_lv);
                                }
                            }
                            if (s_zyhd_lvs1 == null) {
                                s_zyhd_lvs1 = s_zyhd_lvs;
                            }
                            adapter_zyhd_lv = new Adapter_ZYHD_LV(ZYHD.this, s_zyhd_lvs1);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_zyhd_lv);
                                adapter_zyhd_lv.setOnItemListener(new Adapter_ZYHD_LV.onItemListener() {
                                    @Override
                                    public void onClick(int i) {
                                        JSONObject jsonObject = new JSONObject();
                                        try {
                                            jsonObject.put("activityId", s_zyhd_lvs1.get(i).getId());
                                            jsonObject.put("newState", true);
                                            new Httputil()
                                                    .sendResultToken("/prod-api/api/volunteer-service/register", jsonObject.toString(), "POST", new Callback() {
                                                        @Override
                                                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                                        }

                                                        @Override
                                                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                            String s = response.body().string();
                                                            try {
                                                                JSONObject jsonObject1 = new JSONObject(s);
                                                                if (jsonObject1.getString("code").equals("200")) {
                                                                    runOnUiThread(() -> {
                                                                        Toast.makeText(ZYHD.this, "报名成功", Toast.LENGTH_SHORT).show();
                                                                    });
                                                                } else {
                                                                    runOnUiThread(() -> {
                                                                        try {
                                                                            Toast.makeText(ZYHD.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
                                                                        } catch (JSONException e) {
                                                                            throw new RuntimeException(e);
                                                                        }
                                                                    });
                                                                }
                                                            } catch (JSONException e) {
                                                                throw new RuntimeException(e);
                                                            }
                                                        }
                                                    });
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                });
                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(ZYHD.this, ZYHD_XQ.class);
                                    intent.putExtra("id", s_zyhd_lvs.get(position).getId());
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
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        ss = (EditText) findViewById(R.id.ss);
        listview = (ListView) findViewById(R.id.listview);
    }
}
