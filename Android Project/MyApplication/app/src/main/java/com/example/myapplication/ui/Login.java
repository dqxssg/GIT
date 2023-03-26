package com.example.myapplication.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.util.App;
import com.example.myapplication.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Login extends AppCompatActivity {
    private TextView header;
    private EditText yhm;
    private EditText mm;
    private TextView dl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        yhm.clearFocus();
        mm.clearFocus();
        header.setText("智慧城市");
        dl.setOnClickListener(v -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("username", yhm.getText().toString());

                jsonObject.put("password", mm.getText().toString());
                new HttpUtil()
                        .sendResylt("/prod-api/api/login", jsonObject.toString(), "POST", new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                String s = response.body().string();
                                try {
                                    JSONObject jsonObject1 = new JSONObject(s);
                                    if (jsonObject1.getString("code").equals("200")) {
                                        SharedPreferences.Editor editor = App.sp.edit();
                                        editor.putString("token", jsonObject1.getString("token"));
                                        editor.apply();
                                        startActivity(new Intent(Login.this, MainActivity.class));
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
        header = (TextView) findViewById(R.id.header);
        yhm = (EditText) findViewById(R.id.yhm);
        mm = (EditText) findViewById(R.id.mm);
        dl = (TextView) findViewById(R.id.dl);
    }
}
