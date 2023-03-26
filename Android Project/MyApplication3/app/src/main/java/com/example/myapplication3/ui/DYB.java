package com.example.myapplication3.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;
import com.example.myapplication3.bean.ShuJv_ZHBS_ExpandableListView;
import com.example.myapplication3.bean.ShuJv_ZHBS_ZD;
import com.example.myapplication3.util.HttpUtil;
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
    private ArrayList<ShuJv_ZHBS_ExpandableListView> shuJv_zhbs_expandableListViews = new ArrayList<>();
    private ArrayList<ShuJv_ZHBS_ZD> shuJv_zhbs_zds = new ArrayList<>();
    private ImageView back;
    private TextView header;
    private TextView qd;
    private TextView zd;
    private TextView pj;
    private TextView lc;
    private LinearLayout line;
    private TextView xyb;
    private TextView fh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dyb);
        Bundle bundle = this.getIntent().getExtras();
        int i = bundle.getInt("i");
        int id = bundle.getInt("id");
        initView();
        header.setText("第一步");
        back.setOnClickListener(v -> {
            finish();
        });
        fh.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResltToken("/prod-api/api/bus/stop/list?linesId=" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_zhbs_zds = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_ZHBS_ZD>>() {
                            }.getType());
                            for (ShuJv_ZHBS_ZD shuJv_zhbs_zd : shuJv_zhbs_zds) {
                                View view = LayoutInflater.from(DYB.this).inflate(R.layout.line, null);
                                TextView zd = view.findViewById(R.id.zd);
                                zd.setText(shuJv_zhbs_zd.getName());
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
        new HttpUtil()
                .sendResltToken("/prod-api/api/bus/line/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_zhbs_expandableListViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_ZHBS_ExpandableListView>>() {
                            }.getType());
                            runOnUiThread(() -> {
                                qd.setText("起点：" + shuJv_zhbs_expandableListViews.get(i).getFirst());
                                zd.setText("终点：" + shuJv_zhbs_expandableListViews.get(i).getEnd());
                                pj.setText("票价：" + shuJv_zhbs_expandableListViews.get(i).getMileage() + "元");
                                lc.setText("里程：" + shuJv_zhbs_expandableListViews.get(i).getMileage() + "km");
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        xyb.setOnClickListener(v -> {
            Intent intent = new Intent(DYB.this, DEB.class);
            intent.putExtra("i", i);
            intent.putExtra("id", id);
            startActivity(intent);
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        qd = (TextView) findViewById(R.id.qd);
        zd = (TextView) findViewById(R.id.zd);
        pj = (TextView) findViewById(R.id.pj);
        lc = (TextView) findViewById(R.id.lc);
        line = (LinearLayout) findViewById(R.id.line);
        xyb = (TextView) findViewById(R.id.xyb);
        fh = (TextView) findViewById(R.id.fh);
    }
}
