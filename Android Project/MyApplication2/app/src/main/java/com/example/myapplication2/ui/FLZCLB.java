package com.example.myapplication2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;
import com.example.myapplication2.adapter.Adapter_LV_GridView;
import com.example.myapplication2.bean.ShuJv_LV_GridView;
import com.example.myapplication2.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FLZCLB extends AppCompatActivity {
    private ArrayList<ShuJv_LV_GridView> shuJv_lv_gridViews = new ArrayList<>();
    private Adapter_LV_GridView adapter_lv_gridView;
    private TextView back;
    private TextView header;
    private GridView gridview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flzclb);
        initView();
        header.setText("法律专长");
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
                            shuJv_lv_gridViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_LV_GridView>>() {
                            }.getType());
                            shuJv_lv_gridViews.sort(new Comparator<ShuJv_LV_GridView>() {
                                @Override
                                public int compare(ShuJv_LV_GridView o1, ShuJv_LV_GridView o2) {
                                    return o1.getId() - o2.getId();
                                }
                            });
                            adapter_lv_gridView = new Adapter_LV_GridView(FLZCLB.this, shuJv_lv_gridViews);
                            runOnUiThread(() -> {
                                gridview.setAdapter(adapter_lv_gridView);
                                gridview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(FLZCLB.this, SS_LS.class);
                                    intent.putExtra("id", shuJv_lv_gridViews.get(position).getId());
                                    intent.putExtra("name","不显示");
                                    startActivity(intent);
                                });
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
        gridview = (GridView) findViewById(R.id.gridview);
    }
}
