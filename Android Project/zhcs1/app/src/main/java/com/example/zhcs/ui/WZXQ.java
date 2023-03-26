package com.example.zhcs.ui;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.zhcs.R;
import com.example.zhcs.adapter.Adapter_WZXQ_LV;
import com.example.zhcs.bean.S_CWYY_LV;
import com.example.zhcs.bean.S_WZXQ_LV;
import com.example.zhcs.util.App;
import com.example.zhcs.util.HttpUtil;
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

public class WZXQ extends AppCompatActivity {
    private ArrayList<S_CWYY_LV> shuJv_cwyy_listViews = new ArrayList<>();
    private ArrayList<S_WZXQ_LV> shuJv_wzxq_listViews = new ArrayList<>();
    private Adapter_WZXQ_LV wzxq_listView;
    private TextView back;
    private TextView header;
    private EditText sr;
    private TextView zw;
    private ImageView ystx;
    private TextView ysxm;
    private TextView yszc;
    private TextView zybh;
    private TextView zxms;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wzxq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        int i = bundle.getInt("i");
        initView();
        header.setText("问诊详情");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        zw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("inquiryId", i);
                    jsonObject.put("content", sr.getText().toString());
                    new HttpUtil()
                            .sendResultToken("/prod-api/api/pet-hospital/inquiry-message", jsonObject.toString(), "POST", new Callback() {
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
                                                sr.setText("");
                                                shuJv_wzxq_listViews.clear();
                                                shuJv_wzxq_listViews.clear();
                                                new HttpUtil()
                                                        .sendResultToken("/prod-api/api/pet-hospital/inquiry-message/list?inquiryId=" + i, "", "GET", new Callback() {
                                                            @Override
                                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                                            }

                                                            @Override
                                                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                                String s = response.body().string();
                                                                try {
                                                                    JSONObject jsonObject = new JSONObject(s);
                                                                    shuJv_wzxq_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_WZXQ_LV>>() {
                                                                    }.getType());
                                                                    wzxq_listView = new Adapter_WZXQ_LV(WZXQ.this, shuJv_wzxq_listViews);
                                                                    runOnUiThread(() -> {
                                                                        listview.setAdapter(wzxq_listView);
                                                                        ListAdapter listAdapter = listview.getAdapter();
                                                                        if (listAdapter == null) {
                                                                            return;
                                                                        }
                                                                        int h = 0;
                                                                        for (int i = 0; i < shuJv_wzxq_listViews.size(); i++) {
                                                                            View item = listAdapter.getView(i, null, listview);
                                                                            item.measure(1, 1);
                                                                            h += item.getMeasuredHeight();
                                                                        }
                                                                        ViewGroup.LayoutParams params = listview.getLayoutParams();
                                                                        params.height = h + (listview.getMeasuredHeight() * (listAdapter.getCount() - 1));
                                                                        listview.setLayoutParams(params);
                                                                    });
                                                                } catch (JSONException e) {
                                                                    throw new RuntimeException(e);
                                                                }
                                                            }
                                                        });
                                            });
                                        } else {
                                            runOnUiThread(() -> {
                                                Toast.makeText(WZXQ.this, "追问失败", Toast.LENGTH_SHORT).show();
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
        new HttpUtil()
                .sendResultToken("/prod-api/api/pet-hospital/inquiry-message/list?inquiryId=" + i, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_wzxq_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_WZXQ_LV>>() {
                            }.getType());
                            wzxq_listView = new Adapter_WZXQ_LV(WZXQ.this, shuJv_wzxq_listViews);
                            runOnUiThread(() -> {
                                listview.setAdapter(wzxq_listView);
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < shuJv_wzxq_listViews.size(); i++) {
                                    View item = listAdapter.getView(i, null, listview);
                                    item.measure(1, 1);
                                    h += item.getMeasuredHeight();
                                }
                                ViewGroup.LayoutParams params = listview.getLayoutParams();
                                params.height = h + (listview.getMeasuredHeight() * (listAdapter.getCount() - 1));
                                listview.setLayoutParams(params);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        new HttpUtil()
                .sendResultToken("/prod-api/api/pet-hospital/inquiry/my-list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_cwyy_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_CWYY_LV>>() {
                            }.getType());
                            for (S_CWYY_LV shuJv_cwyy_listView : shuJv_cwyy_listViews) {
                                if (shuJv_cwyy_listView.getId() == id) {
                                    runOnUiThread(() -> {
                                        Glide.with(WZXQ.this)
                                                .load(App.url + shuJv_cwyy_listView.getDoctor().getAvatar())
                                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                                .into(ystx);
                                        ysxm.setText(shuJv_cwyy_listView.getDoctor().getName());
                                        yszc.setText(shuJv_cwyy_listView.getDoctor().getJobName());
                                        zybh.setText(shuJv_cwyy_listView.getDoctor().getPracticeNo());
                                        zxms.setText(shuJv_cwyy_listView.getQuestion());
                                    });
                                    break;
                                }
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
        sr = (EditText) findViewById(R.id.sr);
        zw = (TextView) findViewById(R.id.zw);
        ystx = (ImageView) findViewById(R.id.ystx);
        ysxm = (TextView) findViewById(R.id.ysxm);
        yszc = (TextView) findViewById(R.id.yszc);
        zybh = (TextView) findViewById(R.id.zybh);
        zxms = (TextView) findViewById(R.id.zxms);
        listview = (ListView) findViewById(R.id.listview);
    }
}

