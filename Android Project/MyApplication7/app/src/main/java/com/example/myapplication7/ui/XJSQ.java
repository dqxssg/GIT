package com.example.myapplication7.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication7.R;
import com.example.myapplication7.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class XJSQ extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private EditText bt;
    private EditText sqnr;
    private EditText cbdw;
    private EditText fksjh;
    private TextView fb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xjsq);
        initView();
        header.setText("新建诉求");
        back.setOnClickListener(v -> {
            finish();
        });
        fb.setOnClickListener(v -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("appealCategoryId", "23");
                jsonObject.put("title", bt.getText().toString());
                jsonObject.put("content", sqnr.getText().toString());
                jsonObject.put("undertaker", cbdw.getText().toString());
                new HttpUtil()
                        .sendResuiltToken("/prod-api/api/gov-service-hotline/appeal", jsonObject.toString(), "POST", new Callback() {
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
                                            Toast.makeText(XJSQ.this, "发布成功", Toast.LENGTH_SHORT).show();
                                        });
                                    } else {
                                        runOnUiThread(() -> {
                                            try {
                                                Toast.makeText(XJSQ.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
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
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        bt = (EditText) findViewById(R.id.bt);
        sqnr = (EditText) findViewById(R.id.sqnr);
        cbdw = (EditText) findViewById(R.id.cbdw);
        fksjh = (EditText) findViewById(R.id.fksjh);
        fb = (TextView) findViewById(R.id.fb);
    }
}
