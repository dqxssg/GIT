package com.example.myapplication4.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication4.R;
import com.example.myapplication4.adapter.Adapter_F_A_LV;
import com.example.myapplication4.bean.S_F_A_LV;
import com.example.myapplication4.util.Httputil;
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

public class A_SS extends AppCompatActivity {
    private ArrayList<S_F_A_LV> s_f_a_lvArrayList = new ArrayList<>();
    private Adapter_F_A_LV adapter_f_a_lv;
    private TextView back;
    private TextView header;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ss);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        initView();
        header.setText("搜索");
        back.setOnClickListener(v -> {
            finish();
        });
        new Httputil()
                .sendResultToken("/prod-api/press/press/list?title=" + name, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_f_a_lvArrayList = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_F_A_LV>>() {
                            }.getType());
                            adapter_f_a_lv = new Adapter_F_A_LV(A_SS.this, s_f_a_lvArrayList);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_f_a_lv);
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
