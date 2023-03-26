package com.example.myapplication6.ui;

import android.os.Bundle;
import android.text.Html;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication6.R;
import com.example.myapplication6.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LJFL_XWXQ extends AppCompatActivity {


    private TextView back;
    private TextView header;
    private TextView bt;
    private TextView zw;
    private TextView fbr;
    private TextView sj;
    private EditText plsr;
    private TextView plan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ljfl_xwxq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("详情");
        back.setOnClickListener(v -> {
            finish();
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
                                            Toast.makeText(LJFL_XWXQ.this, "评论成功", Toast.LENGTH_SHORT).show();
                                            plsr.setText("");
                                        });
                                    } else {
                                        runOnUiThread(() -> {
                                            try {
                                                Toast.makeText(LJFL_XWXQ.this, jsonObject1.getString("nsg"), Toast.LENGTH_SHORT).show();
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
        new HttpUtil().sendResurltToken("/prod-api/api/garbage-classification/news/" + id, "", "GET", new Callback() {
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
                            sj.setText(jsonObject1.getString("createTime"));
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
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        bt = (TextView) findViewById(R.id.bt);
        zw = (TextView) findViewById(R.id.zw);
        fbr = (TextView) findViewById(R.id.fbr);
        sj = (TextView) findViewById(R.id.sj);
        plsr = (EditText) findViewById(R.id.plsr);
        plan = (TextView) findViewById(R.id.plan);
    }
}
