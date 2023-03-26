package com.example.myapplication9.ui;
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
import com.example.myapplication9.adapter.Adapter_DDLB_XQ_LV;
import com.example.myapplication9.bean.S_DDLB_XQ_LV;
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

public class DDLB_XQ extends AppCompatActivity {
    private ArrayList<S_DDLB_XQ_LV> s_ddlb_xq_lvs = new ArrayList<>();
    private Adapter_DDLB_XQ_LV adapter_ddlb_xq_lv;
    private TextView back;
    private TextView header;
    private TextView type;
    private ListView listview;
    private TextView mony;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddlb_xq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("订单详情");
        back.setOnClickListener(v -> {
            finish();
        });
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            new HttpUtil()
                    .sendResUtil("getOrderById", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                runOnUiThread(() -> {
                                    try {
                                        mony.setText("花费：" + jsonObject1.getString("cost"));
                                        type.setText(jsonObject1.getString("type"));
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                                s_ddlb_xq_lvs = new Gson().fromJson(jsonObject1.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_DDLB_XQ_LV>>() {
                                }.getType());
                                adapter_ddlb_xq_lv = new Adapter_DDLB_XQ_LV(DDLB_XQ.this, s_ddlb_xq_lvs);
                                runOnUiThread(() -> {
                                    listview.setAdapter(adapter_ddlb_xq_lv);
                                    ListAdapter listAdapter = listview.getAdapter();
                                    if (listAdapter == null) {
                                        return;
                                    }
                                    int h = 0;
                                    for (int i = 0; i < s_ddlb_xq_lvs.size(); i++) {
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
        type = (TextView) findViewById(R.id.type);
        listview = (ListView) findViewById(R.id.listview);
        mony = (TextView) findViewById(R.id.mony);
    }
}

