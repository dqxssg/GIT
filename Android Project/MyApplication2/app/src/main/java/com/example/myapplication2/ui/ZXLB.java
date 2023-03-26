package com.example.myapplication2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;
import com.example.myapplication2.adapter.Ad_L_LV1;
import com.example.myapplication2.adapter.Ad_ZXLV_LV;
import com.example.myapplication2.bean.SJ_L_LV1;
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

public class ZXLB extends AppCompatActivity {
    private ArrayList<SJ_L_LV1> sj_l_lv1s = new ArrayList<>();
    private Ad_ZXLV_LV ad_zxlv_lv;
    private TextView back;
    private TextView header;
    private Spinner spinner;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zxlb);
        initView();
        header.setText("咨询列表");
        back.setOnClickListener(v -> {
            finish();
        });
        LV();
    }

    private void LV() {
        new HttpUtil().sendResultToken("/prod-api/api/lawyer-consultation/legal-advice/list", "", "GET", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    sj_l_lv1s = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<SJ_L_LV1>>() {
                    }.getType());
                    ad_zxlv_lv = new Ad_ZXLV_LV(ZXLB.this, sj_l_lv1s);
                    runOnUiThread(() -> {
                        listview.setAdapter(ad_zxlv_lv);
                        listview.setOnItemClickListener((parent, view, position, id) -> {
                            Intent intent = new Intent(ZXLB.this, ZXQ.class);
                            intent.putExtra("id", sj_l_lv1s.get(position).getId());
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
        spinner = (Spinner) findViewById(R.id.spinner);
        listview = (ListView) findViewById(R.id.listview);
    }
}
