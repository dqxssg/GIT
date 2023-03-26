package com.example.test.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.util.App;
import com.example.test.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Login extends AppCompatActivity {
    //    JiEmony
    private EditText yhm;
    private EditText mm;
    private TextView dl;
    private TextView header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        header.setText("登陆界面");
        dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("username", yhm.getText().toString());
                    jsonObject.put("password", mm.getText().toString());
                    new HttpUtil()
                            .sendResult("/prod-api/api/login", jsonObject.toString(), "POST", new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String s = response.body().string();
                                    try {
                                        JSONObject jsonObject1 = new JSONObject(s);
                                        if (jsonObject1.getString("code").equals("200")) {
                                            SharedPreferences.Editor editor = App.sharedPreferences.edit();
                                            editor.putString("token", jsonObject1.getString("token"));
                                            editor.apply();
                                            startActivity(new Intent(Login.this, MainActivity.class));
                                            runOnUiThread(() -> {
                                                Toast.makeText(Login.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                            });
                                        } else {
                                            runOnUiThread(() -> {
                                                Toast.makeText(Login.this, "登录失败", Toast.LENGTH_SHORT).show();
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
    }

    private void initView() {
        yhm = (EditText) findViewById(R.id.yhm);
        mm = (EditText) findViewById(R.id.mm);
        dl = (TextView) findViewById(R.id.dl);
        header = (TextView) findViewById(R.id.header);
    }
}
