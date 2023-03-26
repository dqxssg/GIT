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

public class HHFZ extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private EditText sr;
    private TextView qd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hhfz);
        initView();
        header.setText("户号分组");
        back.setOnClickListener(v -> {
            finish();
        });
        qd.setOnClickListener(v -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("userid", "2");
                jsonObject.put("groupName", sr.getText().toString());
                new HttpUtil()
                        .sendResUtil("setAccountGroup", jsonObject.toString(), "POST", new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                String s = response.body().string();
                                try {
                                    JSONObject jsonObject1 = new JSONObject(s);
                                    if (Objects.equals(jsonObject1.getString("RESULT"), "S")) {
                                        runOnUiThread(() -> {
                                            Toast.makeText(HHFZ.this, "添加成功", Toast.LENGTH_SHORT).show();
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
        qd = (TextView) findViewById(R.id.qd);
    }
}
