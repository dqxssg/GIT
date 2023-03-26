package com.example.myapplication7.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication7.R;
import com.example.myapplication7.adapter.Adapter_AA_LV;
import com.example.myapplication7.bean.S_AA_LV;
import com.example.myapplication7.util.HttpUtil;
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

public class AA_SS extends AppCompatActivity {
    private ArrayList<S_AA_LV> s_aa_lvArrayList = new ArrayList<>();
    private Adapter_AA_LV adapter_aa_lv;
    private TextView back;
    private TextView header;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_ss);
        initView();
        Bundle bundle = this.getIntent().getExtras();
        String title = bundle.getString("title");
        header.setText("搜索详情");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil().sendResuiltToken("/prod-api/press/press/list?title=" + title, "", "GET", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    s_aa_lvArrayList = new Gson().fromJson(jsonObject.getString("rows"), new TypeToken<List<S_AA_LV>>() {
                    }.getType());
                    adapter_aa_lv = new Adapter_AA_LV(AA_SS.this, s_aa_lvArrayList);
                    runOnUiThread(() -> {
                        listview.setAdapter(adapter_aa_lv);
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
