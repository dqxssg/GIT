package com.example.myapplication2.ui;

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
import com.example.myapplication2.R;
import com.example.myapplication2.util.App;
import com.example.myapplication2.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZX extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private ImageView image;
    private TextView mc;
    private TextView zc;
    private TextView zxcs;
    private TextView fwzs;
    private EditText wtlx;
    private EditText wtms;
    private EditText lxdh;
    private TextView tj;
    private TextView cynx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zx);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("免费咨询");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResultToken("/prod-api/api/lawyer-consultation/lawyer/" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            runOnUiThread(() -> {
                                try {
                                    Glide.with(ZX.this)
                                            .load(App.url + jsonObject1.getString("avatarUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(image);
                                    mc.setText(jsonObject1.getString("name"));
                                    zc.setText("法律专长：" + jsonObject1.getString("legalExpertiseName"));
                                    cynx.setText("从业年限：" + jsonObject1.getString("legalExpertiseName"));
                                    zxcs.setText("咨询人数：" + jsonObject1.getString("serviceTimes"));
                                    fwzs.setText("好评率：" + jsonObject1.getString("serviceTimes"));
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        tj.setOnClickListener(v -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("lawyerId", id);
                jsonObject.put("legalExpertiseId", id);
                jsonObject.put("content", wtms.getText().toString());
                jsonObject.put("phone", lxdh.getText().toString());
                new HttpUtil().sendResultToken("/prod-api/api/lawyer-consultation/legal-advice", jsonObject.toString(), "POST", new Callback() {
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
                                    Toast.makeText(ZX.this, "提交成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                });
                            } else {
                                runOnUiThread(() -> {
                                    try {
                                        Toast.makeText(ZX.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
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
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        image = (ImageView) findViewById(R.id.image);
        mc = (TextView) findViewById(R.id.mc);
        zc = (TextView) findViewById(R.id.zc);
        zxcs = (TextView) findViewById(R.id.zxcs);
        fwzs = (TextView) findViewById(R.id.fwzs);
        wtlx = (EditText) findViewById(R.id.wtlx);
        wtms = (EditText) findViewById(R.id.wtms);
        lxdh = (EditText) findViewById(R.id.lxdh);
        tj = (TextView) findViewById(R.id.tj);
        cynx = (TextView) findViewById(R.id.cynx);
    }
}
