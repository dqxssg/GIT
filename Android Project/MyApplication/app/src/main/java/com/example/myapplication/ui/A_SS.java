package com.example.myapplication.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.adapter.Adapter_A_ListView;
import com.example.myapplication.bean.ShuJv_A_ListView;
import com.example.myapplication.util.HttpUtil;
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

public class A_SS extends AppCompatActivity {
    private ArrayList<ShuJv_A_ListView> shuJv_a_listViews = new ArrayList<>();
    private Adapter_A_ListView adapter_a_listView;
    private ImageView back;
    private TextView header;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ss);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        initView();
        header.setText("详情");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResyltToken("/prod-api/press/press/list?title=" + name, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_a_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_A_ListView>>() {
                            }.getType());
                            adapter_a_listView = new Adapter_A_ListView(A_SS.this, shuJv_a_listViews);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_a_listView);
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
    }
}
