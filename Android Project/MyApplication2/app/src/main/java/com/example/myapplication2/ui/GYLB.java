package com.example.myapplication2.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;
import com.example.myapplication2.adapter.Adapter_GYLB_ListView;
import com.example.myapplication2.bean.ShuJv_GYLB_ListView;
import com.example.myapplication2.util.HttpUtil;
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

public class GYLB extends AppCompatActivity {
    private ArrayList<ShuJv_GYLB_ListView> shuJv_gylb_listViews = new ArrayList<>();
    private Adapter_GYLB_ListView adapter_gylb_listView;
    private TextView back;
    private TextView header;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gylb);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        initView();
        header.setText("公益列表");
        back.setOnClickListener(v -> {
            finish();
        });
        //显示
        XS(name);
    }

    private void XS(String name) {
        new HttpUtil()
                .sendResultToken("/prod-api/api/public-welfare/public-welfare-activity/list?&name=" + name, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_gylb_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_GYLB_ListView>>() {
                            }.getType());
                            adapter_gylb_listView = new Adapter_GYLB_ListView(GYLB.this, shuJv_gylb_listViews);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_gylb_listView);
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
        listview = (ListView) findViewById(R.id.listview);
    }
}
