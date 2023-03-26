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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BJJL extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private TextView xg;
    private TextView yhmc;
    private TextView yx;
    private TextView dhhm;
    private TextView xb;
    private EditText gzjy;
    private EditText zgxl;
    private EditText xjzd;
    private EditText qwazw;
    private EditText qwxz;
    private EditText jyjl;
    private EditText grjl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bjjl);
        initView();
        header.setText("编辑简历");
        back.setOnClickListener(v -> {
            finish();
        });
        //显示
        XS();
        //修改
        XG();
    }

    private void XS() {
        new HttpUtil().sendResltToken("/prod-api/api/common/user/getInfo", "", "GET", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(s);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("user");
                    runOnUiThread(() -> {
                        try {
                            yhmc.setText(jsonObject1.getString("userName"));
                            yx.setText(jsonObject1.getString("email"));
                            dhhm.setText(jsonObject1.getString("phonenumber"));
                            if (jsonObject1.getString("sex").equals("0")) {
                                xb.setText("男");
                            } else if (jsonObject1.getString("sex").equals("1")) {
                                xb.setText("女");
                            }
                            new HttpUtil()
                                    .sendResltToken("/prod-api/api/job/resume/queryResumeByUserId/" + jsonObject1.getString("userId"), "", "GET", new Callback() {
                                        @Override
                                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                        }

                                        @Override
                                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                            String s = response.body().string();
                                            try {
                                                JSONObject jsonObject2 = new JSONObject(s);
                                                JSONObject jsonObject3 = jsonObject2.getJSONObject("data");
                                                runOnUiThread(() -> {
                                                    try {
                                                        gzjy.setText(jsonObject3.getString("experience"));
                                                        zgxl.setText(jsonObject3.getString("mostEducation"));
                                                        xjzd.setText(jsonObject3.getString("address"));
                                                        qwxz.setText(jsonObject3.getString("money"));
                                                        jyjl.setText(jsonObject3.getString("education"));
                                                        grjl.setText(jsonObject3.getString("individualResume"));
                                                        new HttpUtil()
                                                                .sendResltToken("/prod-api/api/job/profession/list", "", "GET", new Callback() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                                                    }

                                                                    @Override
                                                                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                                        String s = response.body().string();
                                                                        try {
                                                                            JSONObject jsonObject4 = new JSONObject(s);
                                                                            JSONArray jsonArray = jsonObject4.getJSONArray("rows");
                                                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                                                if (jsonObject3.getString("positionId").equals(jsonArray.getJSONObject(i).getString("id"))) {
                                                                                    int finalI = i;
                                                                                    runOnUiThread(() -> {
                                                                                        try {
                                                                                            qwazw.setText(jsonArray.getJSONObject(finalI).getString("professionName"));
                                                                                        } catch (
                                                                                                JSONException e) {
                                                                                            throw new RuntimeException(e);
                                                                                        }
                                                                                    });
                                                                                    break;
                                                                                }
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
                                            } catch (JSONException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    });
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

    private void XG() {
        xg.setOnClickListener(v -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("address", xjzd.getText().toString());
                jsonObject.put("education", jyjl.getText().toString());
                jsonObject.put("experience", gzjy.getText().toString());
                jsonObject.put("individualResume", grjl.getText().toString());
                jsonObject.put("money", qwxz.getText().toString());
                jsonObject.put("mostEducation", zgxl.getText().toString());
                jsonObject.put("positionId", "1");
                new HttpUtil()
                        .sendResltToken("/prod-api/api/job/resume", jsonObject.toString(), "PUT", new Callback() {
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
                                            Toast.makeText(BJJL.this, "修改成功", Toast.LENGTH_SHORT).show();
                                        });
                                    } else {
                                        runOnUiThread(() -> {
                                            try {
                                                Toast.makeText(BJJL.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
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
        xg = (TextView) findViewById(R.id.xg);
        yhmc = (TextView) findViewById(R.id.yhmc);
        yx = (TextView) findViewById(R.id.yx);
        dhhm = (TextView) findViewById(R.id.dhhm);
        xb = (TextView) findViewById(R.id.xb);
        gzjy = (EditText) findViewById(R.id.gzjy);
        zgxl = (EditText) findViewById(R.id.zgxl);
        xjzd = (EditText) findViewById(R.id.xjzd);
        qwazw = (EditText) findViewById(R.id.qwazw);
        qwxz = (EditText) findViewById(R.id.qwxz);
        jyjl = (EditText) findViewById(R.id.jyjl);
        grjl = (EditText) findViewById(R.id.grjl);
    }
}
