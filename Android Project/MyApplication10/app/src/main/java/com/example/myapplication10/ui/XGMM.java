package com.example.myapplication10.ui;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication10.R;
import com.example.myapplication10.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class XGMM extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView text0;
    private EditText edit0;
    private TextView text1;
    private EditText edit1;
    private TextView text2;
    private EditText edit2;
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
            if (Objects.equals(edit1, edit2)) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("userid", "2");
                    new HttpUtil()
                            .sendResUtil("getPwd", jsonObject.toString(), "POST", new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String s = response.body().string();
                                    try {
                                        JSONObject jsonObject1 = new JSONObject(s);
                                        if (Objects.equals(jsonObject1.getString("password"), edit0.getText().toString())) {
                                            JSONObject jsonObject2 = new JSONObject();
                                            jsonObject2.put("userid", "2");
                                            jsonObject2.put("password", edit1.getText().toString());
                                            new HttpUtil()
                                                    .sendResUtil("setPwd", jsonObject2.toString(), "POST", new Callback() {
                                                        @Override
                                                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                                        }

                                                        @Override
                                                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                            String s = response.body().string();
                                                            JSONObject jsonObject3 = null;
                                                            try {
                                                                jsonObject3 = new JSONObject(s);
                                                                if (Objects.equals(jsonObject3.getString("RESULT"), "S")) {
                                                                    runOnUiThread(() -> {
                                                                        Toast.makeText(XGMM.this, "修改成功", Toast.LENGTH_SHORT).show();
                                                                    });
                                                                }
                                                            } catch (JSONException e) {
                                                                throw new RuntimeException(e);
                                                            }
                                                        }
                                                    });
                                        } else {
                                            runOnUiThread(() -> {
                                                Toast.makeText(XGMM.this, "原密码输入错误", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "两次新密码密码你同", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        text0 = (TextView) findViewById(R.id.text0);
        edit0 = (EditText) findViewById(R.id.edit0);
        text1 = (TextView) findViewById(R.id.text1);
        edit1 = (EditText) findViewById(R.id.edit1);
        text2 = (TextView) findViewById(R.id.text2);
        edit2 = (EditText) findViewById(R.id.edit2);
        xg = (TextView) findViewById(R.id.xg);
    }
}

