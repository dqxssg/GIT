package com.example.myapplication5.ui;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication5.R;
import com.example.myapplication5.adapter.Adapter_B_SS_GV1;
import com.example.myapplication5.bean.S_B_SS_GV1;
import com.example.myapplication5.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class B_SS_XQ extends AppCompatActivity {
    private ArrayList<S_B_SS_GV1> s_b_ss_gv1ArrayList = new ArrayList<>();
    private Adapter_B_SS_GV1 adapter_b_ss_gv1;
    private TextView back;
    private TextView header;
    private GridView gridview1;
    private TextView tfzd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_ss_xq);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        initView();
        header.setText("详情");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil().sendResurltToken("/prod-api/api/garbage-classification/garbage-classification/list?name=" + name, "", "GET", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    runOnUiThread(()->{
                        try {
                            tfzd.setText(jsonArray.getJSONObject(0).getString("guide"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    new HttpUtil()
                            .sendResurltToken("/prod-api/api/garbage-classification/garbage-example/list?type=" + jsonArray.getJSONObject(0).getString("id"), "", "GET", new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String s = response.body().string();
                                    try {
                                        JSONObject jsonObject1 = new JSONObject(s);
                                        s_b_ss_gv1ArrayList = new Gson().fromJson(jsonObject1.getJSONArray("rows").toString(), new TypeToken<List<S_B_SS_GV1>>() {
                                        }.getType());
                                        adapter_b_ss_gv1 = new Adapter_B_SS_GV1(B_SS_XQ.this, s_b_ss_gv1ArrayList);
                                        runOnUiThread(() -> {
                                            gridview1.setAdapter(adapter_b_ss_gv1);
                                        });


                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }

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
        gridview1 = (GridView) findViewById(R.id.gridview1);
        tfzd = (TextView) findViewById(R.id.tfzd);
    }
}
