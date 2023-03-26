package com.example.myapplication5.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication5.R;
import com.example.myapplication5.adapter.Adapter_D_SS;
import com.example.myapplication5.bean.S_D_SS;
import com.example.myapplication5.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class D_SS extends AppCompatActivity {
    private ArrayList<S_D_SS> s_d_sses = new ArrayList<>();
    private Adapter_D_SS adapter_d_ss;
    private TextView back;
    private TextView header;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_ss);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        initView();
        header.setText("搜索详情");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResurltToken("/prod-api/api/lawyer-consultation/lawyer/list?name="+name, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_d_sses = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_D_SS>>() {
                            }.getType());
                            adapter_d_ss = new Adapter_D_SS(D_SS.this, s_d_sses);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_d_ss);
                                adapter_d_ss.setOnItemListener(new Adapter_D_SS.onItemListener() {
                                    @Override
                                    public void onClick(int i) {
                                        Intent intent = new Intent(D_SS.this, XS_XQ.class);
                                        intent.putExtra("id", s_d_sses.get(i).getId());
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
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        listview = (ListView) findViewById(R.id.listview);
    }
}
