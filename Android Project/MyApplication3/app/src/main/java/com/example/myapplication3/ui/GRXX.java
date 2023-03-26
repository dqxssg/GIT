package com.example.myapplication3.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;
import com.example.myapplication3.util.HttpUtil;

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
        header.setText("个人信息");
        back.setOnClickListener(v -> {
            finish();
        });
        xg.setOnClickListener(v -> {
            int sex;
            if (xb.getText().equals("男")) {
                try {
                    Sex(0);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            } else if (xb.getText().equals("男")) {
                try {
                    Sex(1);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            } else {
                runOnUiThread(() -> {
                    Toast.makeText(this, "请输入正确的性别", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    public void Sex(int sex) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nickname", nc.getText().toString());
        jsonObject.put("sex", sex);
        jsonObject.put("phonenumber", lxdh.getText().toString());
        new HttpUtil()
                .sendResltToken("/prod-api/api/common/user", jsonObject.toString(), "PUT", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        JSONObject jsonObject1 = null;
                        try {
                            jsonObject1 = new JSONObject(s);
                            if (jsonObject1.getString("code").equals("200")) {
                                runOnUiThread(() -> {
                                    Toast.makeText(GRXX.this, "修改成功", Toast.LENGTH_SHORT).show();
                                });
                            } else {
                                Toast.makeText(GRXX.this, "修改失败", Toast.LENGTH_SHORT).show();
                            }
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
}
