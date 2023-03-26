package com.example.myapplication.ui;

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

public class YJFK extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private EditText nr;
    private TextView fk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yjfk);
        initView();
        nr.clearFocus();
        header.setText("意见反馈");
        back.setOnClickListener(v -> {
            finish();
        });
        fk.setOnClickListener(v -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("content", nr.getText().toString());
                new HttpUtil()
                        .sendResyltToken("/prod-api/api/common/feedback", jsonObject.toString(), "POST", new Callback() {
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
                                            nr.setText("");
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
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        nr = (EditText) findViewById(R.id.nr);
        fk = (TextView) findViewById(R.id.fk);
    }
}
