package com.example.myapplication8.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication8.R;
import com.example.myapplication8.bean.S_DTCX_LV;
import com.example.myapplication8.bean.S_DTCX_XQ;
import com.example.myapplication8.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DTCX_XQ extends AppCompatActivity {
    private ArrayList<S_DTCX_XQ> s_dtcx_xqs = new ArrayList<>();
    private ArrayList<S_DTCX_LV> s_dtcx_lvs = new ArrayList<>();
    private TextView back;
    private TextView header;
    private TextView sysj;
    private TextView jgjz;
    private TextView syjl;
    private LinearLayout line;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dtcx_xq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("详情");
        back.setOnClickListener(v -> {
            finish();
        });
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("id", id);
            new HttpUtil()
                    .sendResuilt("getAllStationById", jsonObject1.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                s_dtcx_xqs = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_DTCX_XQ>>() {
                                }.getType());
                                for (S_DTCX_XQ s_dtcx_xq : s_dtcx_xqs) {
                                    View view = LayoutInflater.from(DTCX_XQ.this).inflate(R.layout.adapter, null);
                                    TextView back1 = view.findViewById(R.id.back1);
                                    TextView back2 = view.findViewById(R.id.back2);
                                    TextView back3 = view.findViewById(R.id.back3);
                                    TextView back4 = view.findViewById(R.id.back4);
                                    TextView name = view.findViewById(R.id.name);
                                    name.setText(s_dtcx_xq.getStationname());
                                    if (Objects.equals("建国门站", s_dtcx_xq.getStationname())) {
                                        name.setTextColor(Color.parseColor("#ff0000"));
                                        back1.setBackgroundColor(R.drawable.hongdiyuanjiao);
                                        back2.setBackgroundColor(R.drawable.hongdiyuanjiao);
                                        back3.setBackgroundColor(R.drawable.hongdiyuanjiao);
                                        back4.setBackgroundColor(R.drawable.hongdiyuanjiao);
                                    }
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
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("stationName", "建国门站");
            new HttpUtil()
                    .sendResuilt("getSubwaysByStation", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                s_dtcx_lvs = new Gson().fromJson(jsonObject1.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_DTCX_LV>>() {
                                }.getType());
                                for (S_DTCX_LV s_dtcx_lv : s_dtcx_lvs) {
                                    if (Objects.equals(id, s_dtcx_lv.getSubwayid())) {
                                        runOnUiThread(() -> {
                                            sysj.setText("剩余时间：" + s_dtcx_lv.getTime() + "min");
                                            jgjz.setText("下一站：" + s_dtcx_lv.getNextname());
                                            syjl.setText("剩余距离：50km");
                                        });
                                    }
                                }
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
        sysj = (TextView) findViewById(R.id.sysj);
        jgjz = (TextView) findViewById(R.id.jgjz);
        syjl = (TextView) findViewById(R.id.syjl);
        line = (LinearLayout) findViewById(R.id.line);
    }
}
