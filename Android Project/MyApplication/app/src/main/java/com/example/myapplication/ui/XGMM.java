package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class XGMM extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private EditText jmm;
    private EditText xmm1;
    private EditText xmm2;
    private TextView xg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xgmm);
        initView();
         jmm.clearFocus();
        xmm2.clearFocus();
         xmm1.clearFocus();
        header.setText("修改密码");
        back.setOnClickListener(v -> {
            finish();
        });
        xg.setOnClickListener(v -> {
            if (xmm1.getText().toString().equals(xmm2.getText().toString())) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("newPassword", xmm1.getText().toString());
                    jsonObject.put("oldPassword", jmm.getText().toString());
                    new HttpUtil()
                            .sendResyltToken("/prod-api/api/common/user/resetPwd", jsonObject.toString(), "PUT", new Callback() {
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
                                                startActivity(new Intent(XGMM.this, Login.class));
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
        jmm = (EditText) findViewById(R.id.jmm);
        xmm1 = (EditText) findViewById(R.id.xmm1);
        xmm2 = (EditText) findViewById(R.id.xmm2);
        xg = (TextView) findViewById(R.id.xg);
        back = findViewById(R.id.back);
        header = findViewById(R.id.header);
    }
}
