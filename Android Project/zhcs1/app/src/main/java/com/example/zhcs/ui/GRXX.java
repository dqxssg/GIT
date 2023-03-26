package com.example.zhcs.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zhcs.R;
import com.example.zhcs.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GRXX extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private RadioGroup radiogroup;
    private TextView xg;
    private EditText nc;
    private EditText lxdh;
    private TextView sfzh;
    private RadioButton radiobutton1;
    private RadioButton radiobutton2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grxx);
        initView();
        header.setText("个人信息");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResultToken("/prod-api/api/common/user/getInfo", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("user");
                            runOnUiThread(() -> {
                                try {
                                    nc.setText(jsonObject1.getString("nickName"));
                                    lxdh.setText(jsonObject1.getString("phonenumber"));
                                    sfzh.setText(jsonObject1.getString("idCard"));
                                    if (jsonObject1.getString("sex").equals("0")) {
                                        radiobutton1.setChecked(true);
                                    } else if (jsonObject1.getString("sex").equals("1")) {
                                        radiobutton2.setChecked(true);
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
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                xg.setOnClickListener(v -> {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("sex", radioButton.getText().toString().equals("男") ? 0 : 1);
                        jsonObject.put("nickName", nc.getText().toString());
                        jsonObject.put("phonenumber", lxdh.getText().toString());
                        new HttpUtil()
                                .sendResultToken("/prod-api/api/common/user", jsonObject.toString(), "PUT", new Callback() {
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
                                                    Toast.makeText(GRXX.this, "修改成功", Toast.LENGTH_SHORT).show();
                                                });
                                            } else {
                                                runOnUiThread(() -> {
                                                    try {
                                                        Toast.makeText(GRXX.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
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
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        xg = (TextView) findViewById(R.id.xg);
        nc = (EditText) findViewById(R.id.nc);
        lxdh = (EditText) findViewById(R.id.lxdh);
        sfzh = (TextView) findViewById(R.id.sfzh);
        radiobutton1 = (RadioButton) findViewById(R.id.radiobutton1);
        radiobutton2 = (RadioButton) findViewById(R.id.radiobutton2);
    }
}

