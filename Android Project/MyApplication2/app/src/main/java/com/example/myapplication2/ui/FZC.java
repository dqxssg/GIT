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
import com.example.myapplication2.adapter.Ad_L_GV;
import com.example.myapplication2.bean.SJ_L_GV;
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

public class FZC extends AppCompatActivity {
    private ArrayList<SJ_L_GV> sj_l_gvs = new ArrayList<>();
    private Ad_L_GV ad_l_gv;
    private TextView back;
    private TextView header;
    private GridView gridview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fzc);
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
                            sj_l_gvs = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<SJ_L_GV>>() {
                            }.getType());
                            sj_l_gvs.sort(new Comparator<SJ_L_GV>() {
                                @Override
                                public int compare(SJ_L_GV o1, SJ_L_GV o2) {
                                    return o2.getId() - o1.getId();
                                }
                            });
                            ad_l_gv = new Ad_L_GV(FZC.this, sj_l_gvs);
                            runOnUiThread(() -> {
                                gridview.setAdapter(ad_l_gv);
                                gridview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(FZC.this, L_SS.class);
                                    intent.putExtra("name", "");
                                    intent.putExtra("id", sj_l_gvs.get(position).getId().toString());
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
