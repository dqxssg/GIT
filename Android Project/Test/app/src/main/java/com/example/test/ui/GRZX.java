package com.example.test.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GRZX extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private EditText nc;
    private EditText xb;
    private EditText dh;
    private TextView xg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grzx);
        initView();
        header.setText("个人中心");
        back.setOnClickListener(view -> {
            finish();
        });
        xg.setOnClickListener(view -> {
            if (xb.getText().equals("男")) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("nickName", nc.getText());
                    jsonObject.put("phonenumber", dh.getText());
                    jsonObject.put("sex", 0);
                    new HttpUtil()
                            .sendResulToken("/prod-api/api/common/user", jsonObject.toString(), "PUT", new Callback() {
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
                                                xb.setText("");
                                                nc.setText("");
                                                dh.setText("");
                                                Toast.makeText(GRZX.this, "修改成功", Toast.LENGTH_SHORT).show();
                                            });
                                        } else {
                                            runOnUiThread(() -> {
                                                try {
                                                    Toast.makeText(GRZX.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
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
            } else if (xb.getText().equals("女")) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("nickName", nc.getText());
                    jsonObject.put("phonenumber", dh.getText());
                    jsonObject.put("sex", 1);
                    new HttpUtil()
                            .sendResulToken("/prod-api/api/common/user", jsonObject.toString(), "PUT", new Callback() {
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
                                                xb.setText("");
                                                nc.setText("");
                                                dh.setText("");
                                                Toast.makeText(GRZX.this, "修改成功", Toast.LENGTH_SHORT).show();
                                            });
                                        } else {
                                            runOnUiThread(() -> {
                                                try {
                                                    Toast.makeText(GRZX.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
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
            } else {
                Toast.makeText(this, "请输入正确的性别", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        nc = (EditText) findViewById(R.id.nc);
        xb = (EditText) findViewById(R.id.xb);
        dh = (EditText) findViewById(R.id.dh);
        xg = (TextView) findViewById(R.id.xg);
    }
}
