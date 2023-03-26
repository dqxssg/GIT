package com.example.myapplication6.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication6.R;
import com.example.myapplication6.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GHH extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView kss;
    private TextView sj;
    private TextView cg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ghh);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        String ks = bundle.getString("ks");
        String mmz = bundle.getString("mmz");
        String mony = bundle.getString("mony");
        int id = bundle.getInt("id");
        initView();
        header.setText("挂号");
        back.setOnClickListener(v -> {
            finish();
        });
        kss.setText("预约科室：" + ks);
        sj.setText("预约时间：" + name);
        cg.setOnClickListener(v -> {
            JZRKP.activity.finish();
            MZKSFZ.activity.finish();
            GH.activity.finish();
            YYJJ.activity.finish();
            finish();
        });
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("categoryId", id);
            jsonObject.put("money", mony);
            jsonObject.put("patientName", mmz);
            jsonObject.put("reserveTime", name);
            jsonObject.put("type", "1");
            new HttpUtil()
                    .sendResurltToken("/prod-api/api/hospital", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                if (jsonObject1.getString("code").equals("200")) {
                                    runOnUiThread(() -> {
                                        cg.setText("预约成功");
                                    });
                                } else {
                                    runOnUiThread(() -> {
                                        try {
                                            cg.setText(jsonObject1.getString("msg"));
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

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        kss = (TextView) findViewById(R.id.ks);
        sj = (TextView) findViewById(R.id.sj);
        cg = (TextView) findViewById(R.id.cg);
    }
}
