package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.adapter.Adapter_WDHD_ListView;
import com.example.myapplication.bean.ShuJv_WDHD_ListView;
import com.example.myapplication.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZYHD extends AppCompatActivity {
    private ArrayList<ShuJv_WDHD_ListView> shuJv_wdhd_listViews = new ArrayList<>();
    private ArrayList<ShuJv_WDHD_ListView> shuJv_wdhd_listViews1 = new ArrayList<>();
    private Adapter_WDHD_ListView adapter_wdhd_listView;
    private TextView header;
    private EditText sr;
    private ListView listview;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zyhd);
        initView();
        header.setText("志愿活动");
        back.setOnClickListener(v -> {
            finish();
        });
        //列表
        LB("");
        sr.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                LB(sr.getText().toString());
                return true;
            }
            return false;
        });
    }

    private void LB(String name) {
        shuJv_wdhd_listViews1.clear();
        shuJv_wdhd_listViews.clear();
        new HttpUtil()
                .sendResyltToken("/prod-api/api/volunteer-service/activity/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_wdhd_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_WDHD_ListView>>() {
                            }.getType());
                            Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
                            for (ShuJv_WDHD_ListView shuJv_wdhd_listView : shuJv_wdhd_listViews) {
                                Matcher matcher = pattern.matcher(shuJv_wdhd_listView.getTitle());
                                if (matcher.find()) {
                                    shuJv_wdhd_listViews1.add(shuJv_wdhd_listView);
                                }
                                if (shuJv_wdhd_listViews1 == null) {
                                    shuJv_wdhd_listViews1.add(shuJv_wdhd_listView);
                                }
                            }
                            adapter_wdhd_listView = new Adapter_WDHD_ListView(ZYHD.this, shuJv_wdhd_listViews1);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_wdhd_listView);
                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(ZYHD.this, ZYHQXQ.class);
                                    intent.putExtra("id", shuJv_wdhd_listViews1.get(position).getId());
                                    startActivity(intent);
                                });
                                adapter_wdhd_listView.setOnItemListener(new Adapter_WDHD_ListView.onItemListener() {
                                    @Override
                                    public void onClick(int i) {
                                        JSONObject jsonObject1 = new JSONObject();
                                        try {
                                            jsonObject1.put("activityId", shuJv_wdhd_listViews1.get(i).getId());
                                            jsonObject1.put("newState", true);
                                            new HttpUtil()
                                                    .sendResyltToken("/prod-api/api/volunteer-service/register", jsonObject1.toString(), "POST", new Callback() {
                                                        @Override
                                                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                                        }

                                                        @Override
                                                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                            String s = response.body().string();
                                                            try {
                                                                JSONObject jsonObject2 = new JSONObject(s);
                                                                if (jsonObject2 == null) {
                                                                    runOnUiThread(() -> {
                                                                        Toast.makeText(ZYHD.this, "报名成功", Toast.LENGTH_SHORT).show();
                                                                    });
                                                                } else {
                                                                    runOnUiThread(() -> {
                                                                        try {
                                                                            Toast.makeText(ZYHD.this, jsonObject2.getString("msg"), Toast.LENGTH_SHORT).show();
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
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void initView() {
        header = (TextView) findViewById(R.id.header);
        sr = (EditText) findViewById(R.id.sr);
        listview = (ListView) findViewById(R.id.listview);
        back = (ImageView) findViewById(R.id.back);
    }
}
