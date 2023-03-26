package com.example.myapplication3.ui;

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
import com.example.myapplication3.R;
import com.example.myapplication3.bean.ShuJv_ZYS_ListView;
import com.example.myapplication3.util.App;
import com.example.myapplication3.util.HttpUtil;
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
    private ImageView img;
    private TextView xm;
    private TextView zc;
    private TextView xh;
    private TextView tw;
    private EditText sr;

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
                .sendResltToken("/prod-api/api/pet-hospital/pet-doctor/list?name=" + name, "", "GET", new Callback() {
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
                                        .load(App.url + shuJv_zys_listViews.get(0).getAvatar())
                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                        .into(img);
                                xm.setText(shuJv_zys_listViews.get(0).getName());
                                zc.setText(shuJv_zys_listViews.get(0).getJobName());
                                xh.setText(shuJv_zys_listViews.get(0).getPracticeNo());
                                tw.setOnClickListener(v -> {
                                    JSONObject jsonObject1 = new JSONObject();
                                    try {
                                        jsonObject1.put("doctorId", shuJv_zys_listViews.get(0).getId());
                                        jsonObject1.put("question", sr.getText().toString());
                                        new HttpUtil()
                                                .sendReslt("/prod-api/api/pet-hospital/inquiry", jsonObject1.toString(), "POST", new Callback() {
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
                                                                    sr.setText("");
                                                                });
                                                            } else {
                                                                runOnUiThread(() -> {
                                                                    try {
                                                                        Toast.makeText(WZ.this, jsonObject2.getString("msg"), Toast.LENGTH_SHORT).show();
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
        img = (ImageView) findViewById(R.id.img);
        xm = (TextView) findViewById(R.id.xm);
        zc = (TextView) findViewById(R.id.zc);
        xh = (TextView) findViewById(R.id.xh);
        tw = (TextView) findViewById(R.id.tw);
        sr = (EditText) findViewById(R.id.sr);
    }
}
