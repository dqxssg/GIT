package com.example.myapplication2.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;
import com.example.myapplication2.adapter.Adapter_FY_LV;
import com.example.myapplication2.bean.SJ_FY_LV;
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

public class FY extends AppCompatActivity {
    private ArrayList<SJ_FY_LV> sj_fy_lvArrayList = new ArrayList<>();
    private Adapter_FY_LV adapter_fy_lv;
    private TextView back;
    private TextView header;
    private TextView yi;
    private TextView er;
    private TextView san;
    private TextView si;
    private TextView wu;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fy);
        initView();
        header.setText("分页");
        back.setOnClickListener(v -> {
            finish();
        });
        XS(1);
        textColor(yi, er, san, si, wu);
        yi.setOnClickListener(v -> {
            XS(1);
            textColor(yi, er, san, si, wu);
        });
        er.setOnClickListener(v -> {
            XS(2);
            textColor(er, yi, san, si, wu);
        });
        san.setOnClickListener(v -> {
            XS(3);
            textColor(san, er, yi, si, wu);
        });
        si.setOnClickListener(v -> {
            XS(4);
            textColor(si, er, san, yi, wu);
        });
        wu.setOnClickListener(v -> {
            XS(5);
            textColor(wu, er, san, si, yi);
        });
    }

    private void XS(int i) {
        new HttpUtil()
                .sendResultToken("/prod-api/api/public-welfare/public-welfare-activity/list?pageNum=" + i + "&pageSize=4", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            sj_fy_lvArrayList = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<SJ_FY_LV>>() {
                            }.getType());
                            adapter_fy_lv = new Adapter_FY_LV(FY.this, sj_fy_lvArrayList);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_fy_lv);
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
        yi = (TextView) findViewById(R.id.yi);
        er = (TextView) findViewById(R.id.er);
        san = (TextView) findViewById(R.id.san);
        si = (TextView) findViewById(R.id.si);
        wu = (TextView) findViewById(R.id.wu);
        listview = (ListView) findViewById(R.id.listview);
    }

    public void textColor(TextView t1, TextView t2, TextView t3, TextView t4, TextView t5) {
        t1.setTextColor(Color.parseColor("#002fa7"));
        t2.setTextColor(Color.parseColor("#000000"));
        t3.setTextColor(Color.parseColor("#000000"));
        t4.setTextColor(Color.parseColor("#000000"));
        t5.setTextColor(Color.parseColor("#000000"));
    }
}
