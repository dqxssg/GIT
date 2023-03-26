package com.example.myapplication10.ui;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication10.R;
import com.example.myapplication10.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GRXX extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private RadioGroup radioGroup;
    private RadioButton radioButtonMan;
    private RadioButton radioButtonWoman;
    private EditText lxdh;
    private TextView xg;
    private EditText nc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grxx);
        initView();
        header.setText("个人信息");
        back.setOnClickListener(v -> {
            finish();
        });
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userid", "2");
            new HttpUtil().sendResUtil("getUserInfo", jsonObject.toString(), "POST", new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String s = response.body().string();
                    try {
                        JSONObject jsonObject1 = new JSONObject(s);
                        JSONArray jsonArray = jsonObject1.getJSONArray("ROWS_DETAIL");
                        runOnUiThread(() -> {
                            try {
                                nc.setText(jsonArray.getJSONObject(0).getString("name"));
                                lxdh.setText(jsonArray.getJSONObject(0).getString("phone"));
                                if (Objects.equals(jsonArray.getJSONObject(0).getString("sex"), "男")) {
                                    radioButtonMan.setChecked(true);
                                } else {
                                    radioButtonWoman.setChecked(true);
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                JSONObject jsonObject1 = new JSONObject();
                xg.setOnClickListener(v -> {
                    try {
                        jsonObject1.put("userid", "2");
                        jsonObject1.put("name", nc.getText().toString());
                        jsonObject1.put("avatar", "");
                        jsonObject1.put("phone", lxdh.getText().toString());
                        jsonObject1.put("id", "1");
                        jsonObject1.put("gender", radioButton.getText().toString());
                        new HttpUtil()
                                .sendResUtil("setUserInfo", jsonObject1.toString(), "POST", new Callback() {
                                    @Override
                                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                    }

                                    @Override
                                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                        String s = response.body().string();
                                        try {
                                            JSONObject jsonObject2 = new JSONObject(s);
                                            if (Objects.equals(jsonObject2.getString("RESULT"), "S")) {
                                                runOnUiThread(() -> {
                                                    Toast.makeText(GRXX.this, "修改成功", Toast.LENGTH_SHORT).show();
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
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButtonMan = (RadioButton) findViewById(R.id.radioButton_man);
        radioButtonWoman = (RadioButton) findViewById(R.id.radioButton_woman);
        lxdh = (EditText) findViewById(R.id.lxdh);
        xg = (TextView) findViewById(R.id.xg);
        nc = (EditText) findViewById(R.id.nc);
    }
}


