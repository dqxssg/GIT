package com.example.zhcs.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.zhcs.R;
import com.example.zhcs.adapter.Adapter_DDXQ_YD;
import com.example.zhcs.bean.S_DDXQ_YD;
import com.example.zhcs.util.App;
import com.example.zhcs.util.HttpUtil;
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

public class DDXQ extends AppCompatActivity {
    private ArrayList<S_DDXQ_YD> shuJv_ddxq_yds = new ArrayList<>();
    private Adapter_DDXQ_YD adapter_ddxq_yd;
    private TextView back;
    private TextView header;
    private TextView ss;
    private ImageView logo;
    private TextView gsmc;
    private ListView listview;
    private TextView tx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddxq);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        initView();
        ss.setText(name);
        header.setText("订单详情");
        back.setOnClickListener(view -> {
            finish();
        });
        tx.setOnClickListener(view -> {
            Intent intent = new Intent(DDXQ.this, TS.class);
            intent.putExtra("name", name);
            startActivity(intent);
            finish();
        });
        new HttpUtil()
                .sendResultToken("/prod-api/api/logistics-inquiry/logistics_info/" + name, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("company");
                            runOnUiThread(() -> {
                                try {
                                    gsmc.setText(jsonObject2.getString("name"));
                                    Glide.with(DDXQ.this)
                                            .load(App.url + jsonObject2.getString("imgUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(logo);
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                            shuJv_ddxq_yds = new Gson().fromJson(jsonObject1.getJSONArray("stepList").toString(), new TypeToken<List<S_DDXQ_YD>>() {
                            }.getType());
                            adapter_ddxq_yd = new Adapter_DDXQ_YD(DDXQ.this, shuJv_ddxq_yds);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_ddxq_yd);
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < shuJv_ddxq_yds.size(); i++) {
                                    View item = listAdapter.getView(i, null, listview);
                                    item.measure(1, 1);
                                    h += item.getMeasuredHeight();
                                }
                                ViewGroup.LayoutParams params = listview.getLayoutParams();
                                params.height = h + (listview.getDividerHeight() * (listAdapter.getCount() - 1));
                                listview.setLayoutParams(params);
                            });
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void initView() {
        back = findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        ss = (TextView) findViewById(R.id.ss);
        logo = findViewById(R.id.logo);
        gsmc = (TextView) findViewById(R.id.gsmc);
        listview = (ListView) findViewById(R.id.listview);
        tx = (TextView) findViewById(R.id.tx);
    }
}

