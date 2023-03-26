package com.example.myapplication8.ui;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication8.R;
import com.example.myapplication8.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class YJFK extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView xs;
    private TextView tj;
    private EditText sr;

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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("userid", "2");
                jsonObject.put("content", sr.getText().toString());
                jsonObject.put("time", simpleDateFormat.format(date));
                new HttpUtil()
                        .sendResuilt("publicOpinion", jsonObject.toString(), "POST", new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                String s = response.body().string();
                                try {
                                    JSONObject jsonObject1 = new JSONObject(s);
                                    if (jsonObject1.getString("RESULT").equals("S")) {
                                        runOnUiThread(() -> {
                                            Toast.makeText(YJFK.this, "提交成功", Toast.LENGTH_SHORT).show();
                                            sr.setText("");
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
        xs = (TextView) findViewById(R.id.xs);
        tj = (TextView) findViewById(R.id.tj);
        sr = (EditText) findViewById(R.id.sr);
    }
}
