package com.example.test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.adapter.TS1;
import com.example.test.bean.ShuJv_TS;
import com.example.test.bean.ShuJv_TS1;
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

public class TS extends AppCompatActivity {
    private ArrayList<ShuJv_TS1> shuJv_ts1s = new ArrayList<>();
    private TS1 ts1;
    private ImageView back;
    private TextView header;
    private ListView listview;
    private TextView xz;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ts);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        initView();
        header.setText("投诉历史");
        back.setOnClickListener(view -> {
            finish();
        });
        xz.setOnClickListener(view -> {
            Intent intent = new Intent(TS.this, TS_XZ.class);
            intent.putExtra("name", name);
            startActivity(intent);
        });
        new HttpUtil()
                .sendResulToken("/prod-api/api/logistics-inquiry/logistics_complaint/my-list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_ts1s = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_TS1>>() {
                            }.getType());
                            ts1 = new TS1(TS.this, shuJv_ts1s);
                            runOnUiThread(() -> {
                                listview.setAdapter(ts1);
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
        listview = (ListView) findViewById(R.id.listview);
        xz = (TextView) findViewById(R.id.xz);
    }
}
