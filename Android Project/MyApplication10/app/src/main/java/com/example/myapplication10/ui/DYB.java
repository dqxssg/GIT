package com.example.myapplication10.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication10.R;
import com.example.myapplication10.adapter.Adapter_ZHBS_ELV;
import com.example.myapplication10.bean.S_ZHBS_E;
import com.example.myapplication10.bean.S_ZHBS_Y;
import com.example.myapplication10.util.HttpUtil;
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

public class DYB extends AppCompatActivity {
    private ArrayList<S_ZHBS_Y> s_zhbs_is = new ArrayList<>();
    private ArrayList<S_ZHBS_E> s_zhbs_es = new ArrayList<>();
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
        Bundle bundle = this.getIntent().getExtras();
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
            startActivity(intent);
        });
        new HttpUtil()
                .sendResUtil("busList", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_zhbs_is = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_ZHBS_Y>>() {
                            }.getType());
                            for (S_ZHBS_Y s_zhbs_i : s_zhbs_is) {
                                if (Objects.equals(id,s_zhbs_i.getBusid())){
                                    runOnUiThread(() -> {
                                        qd.setText("起点：" + s_zhbs_i.getStartSite());
                                        zd.setText("终点：" + s_zhbs_i.getEndSite());
                                        lc.setText("里程：" + s_zhbs_i.getMileage());
                                        pj.setText("票价：" + s_zhbs_i.getPrice());
                                    });
                                }
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("busid", id);
            new HttpUtil()
                    .sendResUtil("busStationById", jsonObject1.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s1 = response.body().string();
                            try {
                                JSONObject jsonObject2 = new JSONObject(s1);
                                s_zhbs_es = new Gson().fromJson(jsonObject2.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_ZHBS_E>>() {
                                }.getType());


                                for (S_ZHBS_E s_zhbs_er : s_zhbs_es) {
                                    View view = LayoutInflater.from(DYB.this).inflate(R.layout.dyb_line, null);
                                    TextView text = view.findViewById(R.id.text);
                                    text.setText(s_zhbs_er.getSiteName());
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
