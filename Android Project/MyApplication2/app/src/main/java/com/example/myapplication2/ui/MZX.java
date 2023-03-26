package com.example.myapplication2.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MZX extends AppCompatActivity {
    private ArrayList<String> item = new ArrayList<>();
    private ImageView img;
    private TextView mc;
    private TextView flzc;
    private TextView zxrs;
    private TextView fwcs;
    private Spinner wtlx;
    private EditText wtms;
    private EditText lxdh;
    private TextView tj;
    private TextView back;
    private TextView header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mzx);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("律师咨询");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResultToken("/prod-api/api/lawyer-consultation/legal-expertise/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("rows");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                item.add(jsonArray.getJSONObject(i).getString("name"));
                            }
                            runOnUiThread(()->{
                                ArrayAdapter<String>adapter=new ArrayAdapter<>(MZX.this, android.R.layout.simple_spinner_item,item);
                                wtlx.setAdapter(adapter);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });
        tj.setOnClickListener(v -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("lawyerId", "10");
                jsonObject.put("legalExpertiseId", "7");
                jsonObject.put("content", wtms.getText().toString());
                jsonObject.put("phone", lxdh.getText().toString());
                new HttpUtil()
                        .sendResultToken("/prod-api/api/lawyer-consultation/legal-advice", jsonObject.toString(), "POST", new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                try {
                                    JSONObject jsonObject1 = new JSONObject(response.body().string());
                                    if (jsonObject1.getString("code").equals("200")) {
                                        runOnUiThread(() -> {
                                            Toast.makeText(MZX.this, "提交成功", Toast.LENGTH_SHORT).show();
                                            finish();
                                        });
                                    } else {
                                        runOnUiThread(() -> {
                                            try {
                                                Toast.makeText(MZX.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
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
                                    Glide.with(MZX.this)
                                            .load(App.url + jsonObject1.getString("avatarUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(img);
                                    mc.setText(jsonObject1.getString("name"));
                                    flzc.setText("法律专长；" + jsonObject1.getString("legalExpertiseName"));
                                    zxrs.setText("咨询人数；" + jsonObject1.getString("serviceTimes"));
                                    fwcs.setText("服务人数；" + jsonObject1.getString("serviceTimes"));
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
        img = (ImageView) findViewById(R.id.img);
        mc = (TextView) findViewById(R.id.mc);
        flzc = (TextView) findViewById(R.id.flzc);
        zxrs = (TextView) findViewById(R.id.zxrs);
        fwcs = (TextView) findViewById(R.id.fwcs);
        wtlx =  findViewById(R.id.wtlx);
        wtms = (EditText) findViewById(R.id.wtms);
        lxdh = (EditText) findViewById(R.id.lxdh);
        tj = (TextView) findViewById(R.id.tj);
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
    }
}
