package com.example.myapplication9.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication9.R;
import com.example.myapplication9.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class XZ_JZRKP extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private EditText xm;
    private EditText xb;
    private EditText sfzh;
    private EditText csrq;
    private EditText lxdh;
    private EditText dz;
    private TextView qd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xz_jzrkp);
        initView();
        header.setText("新增就诊人卡片");
        back.setOnClickListener(v -> {
            finish();
        });
        qd.setOnClickListener(v -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", xm.getText().toString());
                jsonObject.put("sex", xb.getText().toString());
                jsonObject.put("ID", sfzh.getText().toString());
                jsonObject.put("birthday", csrq.getText().toString());
                jsonObject.put("tel", lxdh.getText().toString());
                jsonObject.put("address", dz.getText().toString());
                new HttpUtil()
                        .sendResUtil("createCase", jsonObject.toString(), "POST", new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                String s = response.body().string();
                                System.out.println("123+"+s);
                                try {
                                    JSONObject jsonObject1 = new JSONObject(s);
                                    if (Objects.equals(jsonObject1.getString("RESULT"), "S")) {
                                        runOnUiThread(() -> {
                                            Toast.makeText(XZ_JZRKP.this, "添加成功", Toast.LENGTH_SHORT).show();
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
        xm = (EditText) findViewById(R.id.xm);
        xb = (EditText) findViewById(R.id.xb);
        sfzh = (EditText) findViewById(R.id.sfzh);
        csrq = (EditText) findViewById(R.id.csrq);
        lxdh = (EditText) findViewById(R.id.lxdh);
        dz = (EditText) findViewById(R.id.dz);
        qd = (TextView) findViewById(R.id.qd);
    }
}
