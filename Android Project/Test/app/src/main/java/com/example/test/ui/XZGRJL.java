package com.example.test.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.test.R;
import com.example.test.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class XZGRJL extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private EditText yhnc;
    private EditText yx;
    private EditText dhhm;
    private EditText xb;
    private EditText gzjy;
    private EditText zgxl;
    private EditText xjzd;
    private EditText qwzw;
    private EditText qwxz;
    private EditText jyjl;
    private EditText grjl;
    private TextView xz;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xzgrjl);
        initView();
        header.setText("新增个人简历");
        back.setOnClickListener(view -> {
            finish();
        });
        xz.setOnClickListener(view -> {
            new HttpUtil()
                    .sendResulToken("/prod-api/api/job/profession/list?professionName=" + qwzw.getText().toString(), "", "GET", new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("rows");
                        JSONObject jsonObject1 = new JSONObject();
                        jsonObject.put("positionId", jsonArray.getJSONObject(0).getString("id"));
                        jsonObject.put("address", xjzd.getText().toString());
                        jsonObject.put("education", jyjl.getText().toString());
                        jsonObject.put("experience", gzjy.getText().toString());
                        jsonObject.put("files", xjzd.getText().toString());
                        jsonObject.put("individualResume", grjl.getText().toString());
                        jsonObject.put("money", qwxz.getText().toString());
                        jsonObject.put("mostEducation", zgxl.getText().toString());
                        new HttpUtil()
                                .sendResulToken("/prod-api/api/job/resume", jsonObject1.toString(), "POST", new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                try {
                                    JSONObject jsonObject2 = new JSONObject(response.body().string());
                                    if (jsonObject2.getString("code").equals("200")) {
                                        runOnUiThread(() -> {
                                            Toast.makeText(XZGRJL.this, "提交成功", Toast.LENGTH_SHORT).show();
                                        });
                                    } else {
                                        runOnUiThread(() -> {
                                            try {
                                                Toast.makeText(XZGRJL.this, jsonObject2.getString("msg"), Toast.LENGTH_SHORT).show();
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        });
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
    }

    private void initView() {
        back = findViewById(R.id.back);
        header = findViewById(R.id.header);
        yhnc = findViewById(R.id.yhnc);
        yx = findViewById(R.id.yx);
        dhhm = findViewById(R.id.dhhm);
        xb = findViewById(R.id.xb);
        gzjy = findViewById(R.id.gzjy);
        zgxl = findViewById(R.id.zgxl);
        xjzd = findViewById(R.id.xjzd);
        qwzw = findViewById(R.id.qwzw);
        qwxz = findViewById(R.id.qwxz);
        jyjl = findViewById(R.id.jyjl);
        grjl = findViewById(R.id.grjl);
        xz = findViewById(R.id.xz);
    }
}
