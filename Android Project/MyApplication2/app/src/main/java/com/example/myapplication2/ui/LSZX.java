package com.example.myapplication2.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;
import com.example.myapplication2.adapter.Adapter_LSZX_ListView;
import com.example.myapplication2.bean.ShuJv_LSZX_ListView;
import com.example.myapplication2.util.HttpUtil;
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

public class LSZX extends AppCompatActivity {
    private ArrayList<ShuJv_LSZX_ListView> shuJv_lszx_listViews = new ArrayList<>();
    private Adapter_LSZX_ListView adapter_lszx_listView;
    private TextView back;
    private TextView header;
    private TextView w;
    private TextView y;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lszx);
        initView();
        header.setText("咨询列表");
        back.setOnClickListener(v -> {
            finish();
        });
        w.setOnClickListener(v -> {
            XSLV("0");
            text(w, y);
        });
        y.setOnClickListener(v -> {
            XSLV("1");
            text(y, w);
        });
        //显示listview
        XSLV("");
    }

    private void XSLV(String stye) {
        new HttpUtil()
                .sendResultToken("/prod-api/api/lawyer-consultation/legal-advice/list?state=" + stye, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_lszx_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_LSZX_ListView>>() {
                            }.getType());
                            adapter_lszx_listView = new Adapter_LSZX_ListView(LSZX.this, shuJv_lszx_listViews);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_lszx_listView);
                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(LSZX.this, ZXXQ.class);
                                    intent.putExtra("id", shuJv_lszx_listViews.get(position).getId());
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
        w = (TextView) findViewById(R.id.w);
        y = (TextView) findViewById(R.id.y);
        listview = (ListView) findViewById(R.id.listview);
    }

    public void text(TextView t1, TextView t2) {
        t1.setTextColor(Color.parseColor("#002fa7"));
        t2.setTextColor(Color.parseColor("#000000"));
    }
}
