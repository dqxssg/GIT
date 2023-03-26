package com.example.test.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.bean.ShuJv_ZHBS;
import com.example.test.util.HttpUtil;
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

public class DSIB extends AppCompatActivity {
    private ArrayList<ShuJv_ZHBS> shuJv_zhbs = new ArrayList<>();
    private ImageView back;
    private TextView header;
    private TextView fh;
    private TextView ckxm;
    private TextView sjhm;
    private TextView scdd;
    private TextView xcdd;
    private TextView tjdd;
    private TextView ccrq;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dsib);
        Bundle bundle = this.getIntent().getExtras();
        String ckxm1 = bundle.getString("ckxm");
        String sjhm1 = bundle.getString("sjhm");
        String scdd1 = bundle.getString("scdd");
        String xcdd1 = bundle.getString("xcdd");
        int i = bundle.getInt("i");
        int id = bundle.getInt("id");
        String datee = bundle.getString("datee");
        String timee = bundle.getString("timee");
        initView();
        ccrq.setText(datee + "   " + timee);
        ckxm.setText(ckxm1);
        sjhm.setText(sjhm1);
        scdd.setText(scdd1);
        xcdd.setText(xcdd1);
        header.setText("第四步");
        fh.setOnClickListener(view -> {
            finish();
        });
        back.setOnClickListener(view -> {
            finish();
        });
        tjdd.setOnClickListener(view -> {
            new HttpUtil()
                    .sendResulToken("/prod-api/api/bus/line/list", "", "GET", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                shuJv_zhbs = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_ZHBS>>() {
                                }.getType());
                                JSONObject jsonObject1 = new JSONObject();
                                jsonObject1.put("start", scdd1);
                                jsonObject1.put("end", xcdd1);
                                jsonObject1.put("price", shuJv_zhbs.get(i).getPrice());
                                jsonObject1.put("path", shuJv_zhbs.get(i).getName());
                                jsonObject1.put("status", "1");
                                new HttpUtil()
                                        .sendResulToken("/prod-api/api/bus/order", jsonObject1.toString(), "POST", new Callback() {
                                            @Override
                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                            }

                                            @Override
                                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                String s = response.body().string();
                                                try {
                                                    JSONObject jsonObject2 = new JSONObject(s);
                                                    if (jsonObject2.getString("code").equals("200")) {
                                                        runOnUiThread(() -> {
                                                            Toast.makeText(DSIB.this, "提交成功", Toast.LENGTH_SHORT).show();
                                                        });
                                                    } else {
                                                        runOnUiThread(() -> {
                                                            try {
                                                                Toast.makeText(DSIB.this, jsonObject2.getString("msg"), Toast.LENGTH_SHORT).show();
                                                            } catch (JSONException e) {
                                                                throw new RuntimeException(e);
                                                            }
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
                    });
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        fh = (TextView) findViewById(R.id.fh);
        ckxm = (TextView) findViewById(R.id.ckxm);
        sjhm = (TextView) findViewById(R.id.sjhm);
        scdd = (TextView) findViewById(R.id.scdd);
        xcdd = (TextView) findViewById(R.id.xcdd);
        tjdd = (TextView) findViewById(R.id.tjdd);
        ccrq = (TextView) findViewById(R.id.ccrq);
    }
}
