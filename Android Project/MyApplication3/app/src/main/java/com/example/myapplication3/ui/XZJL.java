package com.example.myapplication3.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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

public class XZJL extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private TextView yhmc;
    private TextView yx;
    private TextView dhhm;
    private TextView xb;
    private TextView gzjy;
    private TextView zgxl;
    private TextView xjzd;
    private TextView qwazw;
    private TextView qwxz;
    private TextView jyjl;
    private TextView grjl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xzjl);
        initView();
        header.setText("查看简历");
        back.setOnClickListener(v -> {
            finish();
        });


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
                                                                        } catch (
                                                                                JSONException e) {
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

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        yhmc = (TextView) findViewById(R.id.yhmc);
        yx = (TextView) findViewById(R.id.yx);
        dhhm = (TextView) findViewById(R.id.dhhm);
        xb = (TextView) findViewById(R.id.xb);
        gzjy = (TextView) findViewById(R.id.gzjy);
        zgxl = (TextView) findViewById(R.id.zgxl);
        xjzd = (TextView) findViewById(R.id.xjzd);
        qwazw = (TextView) findViewById(R.id.qwazw);
        qwxz = (TextView) findViewById(R.id.qwxz);
        jyjl = (TextView) findViewById(R.id.jyjl);
        grjl = (TextView) findViewById(R.id.grjl);
    }
}
