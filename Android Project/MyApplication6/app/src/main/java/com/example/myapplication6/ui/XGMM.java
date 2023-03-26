package com.example.myapplication6.ui;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class XGMM extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private EditText ymm;
    private EditText xmm1;
    private EditText xmm2;
    private TextView xg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xgmm);
        initView();
        header.setText("修改密码");
        back.setOnClickListener(v -> {
            finish();
        });
        xg.setOnClickListener(v -> {
            if (xmm1.getText().toString().equals(xmm2.getText().toString())) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("newPassword", xmm1.getText().toString());
                    jsonObject.put("oldPassword", ymm.getText().toString());
                    new HttpUtil()
                            .sendResurltToken("/prod-api/api/common/user/resetPwd", jsonObject.toString(), "PUT", new Callback() {
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
                                                Toast.makeText(XGMM.this, "修改成功", Toast.LENGTH_SHORT).show();
                                            });
                                        } else {
                                            runOnUiThread(() -> {
                                                try {
                                                    Toast.makeText(XGMM.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
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

            } else {
                runOnUiThread(() -> {
                    Toast.makeText(this, "两次新密码不同", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        ymm = (EditText) findViewById(R.id.ymm);
        xmm1 = (EditText) findViewById(R.id.xmm1);
        xmm2 = (EditText) findViewById(R.id.xmm2);
        xg = (TextView) findViewById(R.id.xg);
    }
}

