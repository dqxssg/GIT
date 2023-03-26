package com.example.test.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.adapter.SS_ListView;
import com.example.test.bean.ShuJv_SS_ListView;
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

public class SS extends AppCompatActivity {
    private ArrayList<ShuJv_SS_ListView> shuJv_ss_listViews = new ArrayList<>();
    private SS_ListView ss_listView;

    private TextView header;
    private ListView listview;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ss);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        System.out.println(name);
        initView();
        header.setText("搜索");
        back.setOnClickListener(view -> {
            finish();
        });
        new HttpUtil()
                .sendResulToken("/prod-api/press/press/list?title=" + name, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_ss_listViews = new Gson().fromJson(jsonObject.getString("rows").toString(), new TypeToken<List<ShuJv_SS_ListView>>() {
                            }.getType());
                            ss_listView = new SS_ListView(SS.this, shuJv_ss_listViews);
                            runOnUiThread(() -> {
                                listview.setAdapter(ss_listView);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void initView() {
        header = (TextView) findViewById(R.id.header);
        listview = (ListView) findViewById(R.id.listview);
        back = (ImageView) findViewById(R.id.back);
    }
}
