package com.example.myapplication6.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication6.R;
import com.example.myapplication6.adapter.Adapter_MZKSFZ_LV;
import com.example.myapplication6.bean.S_MZKSFZ_LV;
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

public class MZKSFZ extends AppCompatActivity {
    public static Activity activity;
    private ArrayList<S_MZKSFZ_LV> s_mzksfz_lvs = new ArrayList<>();
    private Adapter_MZKSFZ_LV adapter_mzksfz_lv;
    private TextView back;
    private TextView header;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mzksfz);
        activity = this;
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("ID");
        String mmz = bundle.getString("mmz");
        initView();
        header.setText("门诊科室分诊");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil().sendResurltToken("/prod-api/api/hospital/category/list", "", "GET", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                System.out.println(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    s_mzksfz_lvs = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_MZKSFZ_LV>>() {
                    }.getType());
                    adapter_mzksfz_lv = new Adapter_MZKSFZ_LV(MZKSFZ.this, s_mzksfz_lvs);
                    runOnUiThread(() -> {
                        listview.setAdapter(adapter_mzksfz_lv);
                        listview.setOnItemClickListener((parent, view, position, id1) -> {
                            Intent intent = new Intent(MZKSFZ.this, GH.class);
                            intent.putExtra("id", s_mzksfz_lvs.get(position).getId());
                            intent.putExtra("ks", s_mzksfz_lvs.get(position).getCategoryName());
                            intent.putExtra("mony", s_mzksfz_lvs.get(position).getMoney());
                            intent.putExtra("mmz", mmz);
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
        listview = (ListView) findViewById(R.id.listview);
    }
}
