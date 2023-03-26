package com.example.myapplication4.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication4.R;
import com.example.myapplication4.bean.S_ZHBS_ER;
import com.example.myapplication4.bean.S_ZHBS_YI;
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

public class DYB extends AppCompatActivity {
    public static Activity yactivity;
    private ArrayList<S_ZHBS_YI> s_zhbs_yis = new ArrayList<>();
    private ArrayList<S_ZHBS_ER> s_zhbs_ers = new ArrayList<>();
    private TextView back;
    private TextView header;
    private LinearLayout line;
    private TextView qd;
    private TextView zd;
    private TextView pj;
    private TextView lc;
    private TextView xyb;
    private TextView fh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dyb);
        yactivity = this;
        Bundle bundle = this.getIntent().getExtras();
        int i = bundle.getInt("i");
        int id = bundle.getInt("id");
        initView();
        header.setText("智慧巴士");
        back.setOnClickListener(v -> {
            finish();
        });
        fh.setOnClickListener(v -> {
            finish();
        });
        xyb.setOnClickListener(v -> {
            Intent intent = new Intent(DYB.this, DEB.class);
            intent.putExtra("id", id);
            intent.putExtra("i", i);
            startActivity(intent);
        });
        new Httputil()
                .sendResultToken("/prod-api/api/bus/line/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_zhbs_yis = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_ZHBS_YI>>() {
                            }.getType());
                            runOnUiThread(() -> {
                                qd.setText("起点：" + s_zhbs_yis.get(i).getFirst());
                                zd.setText("终点：" + s_zhbs_yis.get(i).getEnd());
                                lc.setText("里程：" + s_zhbs_yis.get(i).getMileage());
                                pj.setText("票价：" + s_zhbs_yis.get(i).getPrice());
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        new Httputil()
                .sendResultToken("/prod-api/api/bus/stop/list?linesId=" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_zhbs_ers = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_ZHBS_ER>>() {
                            }.getType());
                            for (S_ZHBS_ER s_zhbs_er : s_zhbs_ers) {
                                View view = LayoutInflater.from(DYB.this).inflate(R.layout.dyb_line, null);
                                TextView text = view.findViewById(R.id.text);
                                text.setText(s_zhbs_er.getName());
                                view.setPadding(0, 0, 0, 0);
                                runOnUiThread(() -> {
                                    line.addView(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                });
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
        line = (LinearLayout) findViewById(R.id.line);
        qd = (TextView) findViewById(R.id.qd);
        zd = (TextView) findViewById(R.id.zd);
        pj = (TextView) findViewById(R.id.pj);
        lc = (TextView) findViewById(R.id.lc);
        xyb = (TextView) findViewById(R.id.xyb);
        fh = (TextView) findViewById(R.id.fh);
    }
}
