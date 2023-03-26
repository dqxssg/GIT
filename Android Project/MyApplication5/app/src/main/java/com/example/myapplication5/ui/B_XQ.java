package com.example.myapplication5.ui;

import android.os.Bundle;
import android.text.Html;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication5.R;
import com.example.myapplication5.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class B_XQ extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView bt;
    private TextView zw;
    private TextView fbr;
    private TextView fbsj;
    private EditText plsr;
    private TextView plan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_xq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        System.out.println(id);
        initView();
        header.setText("详情");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResurltToken("/prod-api/api/garbage-classification/news/" + id, "", "GET", new Callback() {
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
                                    bt.setText(jsonObject1.getString("title"));
                                    zw.setText(Html.fromHtml(jsonObject1.getString("content")));
                                    fbr.setText(jsonObject1.getString("author"));
                                    fbsj.setText(jsonObject1.getString("createTime"));
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        plan.setOnClickListener(v -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("newsId", id);
                jsonObject.put("content", plsr.getText().toString());
                new HttpUtil()
                        .sendResurltToken("/prod-api/api/garbage-classification/news-comment", jsonObject.toString(), "POST", new Callback() {
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
                                            Toast.makeText(B_XQ.this, "提交成功", Toast.LENGTH_SHORT).show();
                                        });
                                    } else {
                                        runOnUiThread(() -> {
                                            try {
                                                Toast.makeText(B_XQ.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
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
        bt = (TextView) findViewById(R.id.bt);
        zw = (TextView) findViewById(R.id.zw);
        fbr = (TextView) findViewById(R.id.fbr);
        fbsj = (TextView) findViewById(R.id.fbsj);
        plsr = (EditText) findViewById(R.id.plsr);
        plan = (TextView) findViewById(R.id.plan);
    }
}
