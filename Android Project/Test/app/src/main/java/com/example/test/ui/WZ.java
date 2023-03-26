package com.example.test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.test.R;
import com.example.test.bean.ShuJv_ZYS_ListView;
import com.example.test.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WZ extends AppCompatActivity {
    private ArrayList<ShuJv_ZYS_ListView> shuJv_zys_listViews = new ArrayList<>();
    private ImageView back;
    private TextView header;
    private ImageView ystx;
    private TextView ysxm;
    private TextView yszc;
    private TextView zybh;
    private EditText wtms;
    private TextView tj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wz);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        initView();
        header.setText("问诊");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResulToken("/prod-api/api/pet-hospital/pet-doctor/list?name=" + name, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_zys_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_ZYS_ListView>>() {
                            }.getType());
                            runOnUiThread(() -> {
                                Glide.with(WZ.this)
                                        .load("http://124.93.196.45:10001" + shuJv_zys_listViews.get(0).getAvatar())
                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                        .into(ystx);
                                ysxm.setText(shuJv_zys_listViews.get(0).getName());
                                yszc.setText(shuJv_zys_listViews.get(0).getJobName());
                                zybh.setText(shuJv_zys_listViews.get(0).getPracticeNo());

                                tj.setOnClickListener(v -> {
                                    JSONObject jsonObject1 = new JSONObject();
                                    try {
                                        jsonObject1.put("doctorId", shuJv_zys_listViews.get(0).getId());
                                        jsonObject1.put("question", wtms.getText().toString());
                                        new HttpUtil()
                                                .sendResulToken("/prod-api/api/pet-hospital/inquiry", jsonObject1.toString(), "POST", new Callback() {
                                                    @Override
                                                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                                    }

                                                    @Override
                                                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                        String s = response.body().string();
                                                        try {
                                                            JSONObject jsonObject2 = new JSONObject(s);
                                                            if (jsonObject2.getString("code").equals("200")) {
                                                                runOnUiThread(() -> {
                                                                    Toast.makeText(WZ.this, "提交成功", Toast.LENGTH_SHORT).show();
                                                                    startActivity(new Intent(WZ.this, CWYY.class));
                                                                });
                                                            } else {
                                                                runOnUiThread(() -> {
                                                                    Toast.makeText(WZ.this, "提交失败", Toast.LENGTH_SHORT).show();
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
        ystx = (ImageView) findViewById(R.id.ystx);
        ysxm = (TextView) findViewById(R.id.ysxm);
        yszc = (TextView) findViewById(R.id.yszc);
        zybh = (TextView) findViewById(R.id.zybh);
        wtms = (EditText) findViewById(R.id.wtms);
        tj = (TextView) findViewById(R.id.tj);
    }
}
