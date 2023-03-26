package com.example.myapplication4.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication4.R;
import com.example.myapplication4.util.Httputil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TJCL extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private EditText cph;
    private EditText cjh;
    private EditText cllx;
    private TextView tj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tjcl);
        initView();
        header.setText("添加车辆");
        back.setOnClickListener(v -> {
            finish();
        });
        tj.setOnClickListener(v -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("engineNo", cjh.getText().toString());
                jsonObject.put("plateNo", cph.getText().toString());
                jsonObject.put("type", cllx.getText().toString());
                new Httputil().sendResultToken("/prod-api/api/traffic/car", jsonObject.toString(), "POST", new Callback() {
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
                                    Toast.makeText(TJCL.this, "添加成功", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(TJCL.this, CLGL.class));
                                    finish();
                                });
                            } else {
                                runOnUiThread(() -> {
                                    try {
                                        Toast.makeText(TJCL.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
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
        cph = (EditText) findViewById(R.id.cph);
        cjh = (EditText) findViewById(R.id.cjh);
        cllx = (EditText) findViewById(R.id.cllx);
        tj = (TextView) findViewById(R.id.tj);
    }
}
