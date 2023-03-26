package com.example.myapplication6.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication6.R;
import com.example.myapplication6.adapter.Adapter_JZRKP_LV;
import com.example.myapplication6.bean.S_JZRKP_LV;
import com.example.myapplication6.util.HttpUtil;
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
    public static Activity activity;
    private ArrayList<S_JZRKP_LV> s_jzrkp_lvs = new ArrayList<>();
    private Adapter_JZRKP_LV adapter_jzrkp_lv;
    private TextView back;
    private TextView header;
    private ListView listview;
    private LinearLayout tj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jzrkp);
        activity=this;
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("就诊人卡片");
        back.setOnClickListener(v -> {
            finish();
        });
        tj.setOnClickListener(v -> {
            Intent intent = new Intent(JZRKP.this, CJJZRKP.class);
            intent.putExtra("id", "wu");
            startActivity(intent);
        });
        new HttpUtil().sendResurltToken("/prod-api/api/hospital/patient/list", "", "GET", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    s_jzrkp_lvs = new Gson().fromJson(jsonObject.getString("rows"), new TypeToken<List<S_JZRKP_LV>>() {
                    }.getType());
                    adapter_jzrkp_lv = new Adapter_JZRKP_LV(JZRKP.this, s_jzrkp_lvs);
                    runOnUiThread(() -> {
                        listview.setAdapter(adapter_jzrkp_lv);
                        listview.setOnItemClickListener((parent, view, position, id1) -> {
                            Intent intent = new Intent(JZRKP.this, CJJZRKP.class);
                            intent.putExtra("id", s_jzrkp_lvs.get(position).getId());
                            startActivity(intent);
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
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        listview = (ListView) findViewById(R.id.listview);
        tj = (LinearLayout) findViewById(R.id.tj);
    }
}
