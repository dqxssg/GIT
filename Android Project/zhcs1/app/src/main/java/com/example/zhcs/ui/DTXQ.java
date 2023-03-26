package com.example.zhcs.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zhcs.R;
import com.example.zhcs.bean.S_STCXXQ;
import com.example.zhcs.util.HttpUtil;
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


public class DTXQ extends AppCompatActivity {
    private ArrayList<S_STCXXQ> shuJv_stcxxqs = new ArrayList<>();
    private TextView back;
    private TextView header;
    private TextView time;
    private TextView jgjz;
    private TextView syjl;
    private LinearLayout line;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dtxq);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        int id = bundle.getInt("id");
        initView();
        header.setText(name);
        back.setOnClickListener(view -> {
            finish();
        });
        new HttpUtil()
                .sendResultToken("/prod-api/api/metro/line/" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            runOnUiThread(() -> {
                                try {
                                    time.setText("运行时间：" + jsonObject1.getString("startTime") + "--" + jsonObject1.getString("endTime"));
                                    jgjz.setText("站数：" + jsonObject1.getString("stationsNumber") + "站");
                                    syjl.setText("距离：" + jsonObject1.getString("km") + "km");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            });
                            shuJv_stcxxqs = new Gson().fromJson(jsonObject1.getJSONArray("metroStepList").toString(), new TypeToken<List<S_STCXXQ>>() {
                            }.getType());
                            //路线
                            for (int i = 0; i < shuJv_stcxxqs.size(); i++) {
                                View view = LayoutInflater.from(DTXQ.this).inflate(R.layout.zd, null);
                                TextView name = view.findViewById(R.id.name);
                                TextView img = view.findViewById(R.id.img);
                                name.setText(shuJv_stcxxqs.get(i).getName());
                                if (shuJv_stcxxqs.get(i).getName().equals("建国门")) {
                                    name.setTextColor(Color.parseColor("#1e90ff"));
                                    name.setBackgroundColor(Color.parseColor("#ffff00"));
                                    img.setBackgroundResource(R.drawable.lx);
                                }
                                view.setPadding(0, 0, 0, 0);
                                runOnUiThread(() -> {
                                    line.addView(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        time = (TextView) findViewById(R.id.time);
        jgjz = (TextView) findViewById(R.id.jgjz);
        syjl = (TextView) findViewById(R.id.syjl);
        line = (LinearLayout) findViewById(R.id.line);
    }
}
