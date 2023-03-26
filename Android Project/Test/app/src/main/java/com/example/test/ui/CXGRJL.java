package com.example.test.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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

public class CXGRJL extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private TextView yhnc;
    private TextView yx;
    private TextView dhhm;
    private TextView xb;
    private TextView gzjy;
    private TextView zgxl;
    private TextView xjzd;
    private TextView qwzw;
    private TextView qwxz;
    private TextView jyjl;
    private TextView grjl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cxgrjl);
        initView();
        header.setText("查询个人简历");
        back.setOnClickListener(view -> {
            finish();
        });
        new HttpUtil()
                .sendResulToken("/prod-api/api/common/user/getInfo", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            JSONObject jsonObject1 = jsonObject.getJSONObject("user");
                            yhnc.setText(jsonObject1.getString("nickName"));
                            yx.setText(jsonObject1.getString("email"));
                            dhhm.setText(jsonObject1.getString("phonenumber"));
                            if (jsonObject1.getString("sex").equals("0")) {
                                xb.setText("男");
                            } else {
                                xb.setText("女");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        new HttpUtil()
                .sendResulToken("/prod-api/api/job/resume/queryResumeByUserId/1113004", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            runOnUiThread(() -> {
                                try {
                                    gzjy.setText(jsonObject1.getString("experience"));
                                    zgxl.setText(jsonObject1.getString("mostEducation"));
                                    xjzd.setText(jsonObject1.getString("address"));
                                    qwxz.setText(jsonObject1.getString("money"));
                                    jyjl.setText(jsonObject1.getString("education"));
                                    grjl.setText(jsonObject1.getString("individualResume"));
                                    new HttpUtil()
                                            .sendResulToken("/prod-api/api/job/profession/list", "", "GET", new Callback() {
                                                @Override
                                                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                                }

                                                @Override
                                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                    try {
                                                        JSONObject jsonObject2 = new JSONObject(response.body().string());
                                                        JSONArray jsonArray = jsonObject2.getJSONArray("rows");
                                                        for (int i = 0; i < jsonArray.length(); i++) {
                                                            if (jsonArray.getJSONObject(i).getString("id").equals(jsonObject1.getString("positionId"))) {
                                                                int finalI = i;
                                                                runOnUiThread(() -> {
                                                                    try {
                                                                        qwzw.setText(jsonArray.getJSONObject(finalI).getString("professionName"));
                                                                    } catch (JSONException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                });
                                                                break;
                                                            }
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
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
    }
}

