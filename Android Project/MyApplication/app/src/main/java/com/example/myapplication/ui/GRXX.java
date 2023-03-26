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

public class GRXX extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private EditText nc;
    private EditText xb;
    private EditText lxdh;
    private TextView xg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grxx);
        initView();
        nc.clearFocus();
        xb.clearFocus();
        lxdh.clearFocus();
        header.setText("个人信息");
        back.setOnClickListener(v -> {
            finish();
        });
        //显示
        XS();
        xg.setOnClickListener(v -> {
            if (xb.getText().toString().equals("男")) {
                XG(0);
            } else if (xb.getText().toString().equals("女")) {
                XG(1);
            } else {
                runOnUiThread(() -> {
                    Toast.makeText(this, "请输入正确的性别", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void XS() {
        new HttpUtil()
                .sendResyltToken("/prod-api/api/common/user/getInfo", "", "GET", new Callback() {
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
                                    nc.setText(jsonObject1.getString("userName"));
                                    lxdh.setText(jsonObject1.getString("phonenumber"));
                                    if (jsonObject1.getString("sex").equals("0")) {
                                        xb.setText("男");
                                    } else {
                                        xb.setText("女");
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
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        nc = (EditText) findViewById(R.id.nc);
        xb = (EditText) findViewById(R.id.xb);
        lxdh = (EditText) findViewById(R.id.lxdh);
        xg = (TextView) findViewById(R.id.xg);
    }

    //修改
    public void XG(int sex) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nickName", nc.getText().toString());
            jsonObject.put("sex", sex);
            jsonObject.put("phonenumber", lxdh.getText().toString());
            new HttpUtil()
                    .sendResyltToken("/prod-api/api/common/user", jsonObject.toString(), "PUT", new Callback() {
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

    }
}
