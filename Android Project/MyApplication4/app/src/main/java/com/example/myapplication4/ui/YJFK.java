package com.example.myapplication4.ui;


import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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

public class YJFK extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private EditText sr;
    private TextView xs;
    private TextView tj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yjfk);
        initView();
        header.setText("意见反馈");
        back.setOnClickListener(v -> {
            finish();
        });
        sr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                xs.setText(s.length() + "/150字");
            }
        });
        tj.setOnClickListener(v -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("content", sr.getText().toString());
                new Httputil()
                        .sendResultToken("/prod-api/api/common/feedback", jsonObject.toString(), "POST", new Callback() {
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
                                            Toast.makeText(YJFK.this, "反馈成功", Toast.LENGTH_SHORT).show();
                                        });
                                    } else {
                                        runOnUiThread(() -> {
                                            try {
                                                Toast.makeText(YJFK.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
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
        sr = (EditText) findViewById(R.id.sr);
        xs = (TextView) findViewById(R.id.xs);
        tj = (TextView) findViewById(R.id.tj);
    }
}
