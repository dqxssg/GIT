package com.example.myapplication5.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication5.R;
import com.example.myapplication5.adapter.Adapter_ZXLB_LV;
import com.example.myapplication5.bean.S_D_LV2;
import com.example.myapplication5.bean.S_ZXLB_LV;
import com.example.myapplication5.bean.S_ZXLB_TX;
import com.example.myapplication5.util.HttpUtil;
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
    private ArrayList<S_ZXLB_TX> s_zxlb_txes = new ArrayList<>();
    private ArrayList<S_ZXLB_LV> s_zxlb_lvs = new ArrayList<>();
    private Adapter_ZXLB_LV adapter_zxlb_lv;
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
        //listview
        LV("");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("123+"+position);
                switch (position) {
                    case 1:
                        s_zxlb_lvs.clear();
                        s_zxlb_txes.clear();
                        s_zxlb_lvs.clear();
                        System.out.println("++"+s_zxlb_lvs.size());
                        System.out.println("+++"+s_zxlb_txes.size());
                        LV("1");
                        break;
                    case 0:
                        LV("0");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void LV(String i) {
        new HttpUtil()
                .sendResurltToken("/prod-api/api/lawyer-consultation/legal-advice/list?state=" + i, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_zxlb_lvs = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_ZXLB_LV>>() {
                            }.getType());
                            for (S_ZXLB_LV s_zxlb_lv : s_zxlb_lvs) {
                                new HttpUtil()
                                        .sendResurltToken("/prod-api/api/lawyer-consultation/lawyer/" + s_zxlb_lv.getLawyerId(), "", "GET", new Callback() {
                                            @Override
                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                            }

                                            @Override
                                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                String s = response.body().string();
                                                try {
                                                    JSONObject jsonObject1 = new JSONObject(s);
                                                    JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
                                                    s_zxlb_txes.add(new S_ZXLB_TX(jsonObject2.getString("avatarUrl")));
                                                } catch (JSONException e) {
                                                    throw new RuntimeException(e);
                                                }
                                                if (s_zxlb_lv.getId().equals(s_zxlb_lvs.get(s_zxlb_lvs.size() - 2).getId())) {
                                                    adapter_zxlb_lv = new Adapter_ZXLB_LV(ZXLB.this, s_zxlb_lvs, s_zxlb_txes);
                                                    runOnUiThread(() -> {
                                                        listview.setAdapter(adapter_zxlb_lv);
                                                        listview.setOnItemClickListener((parent, view, position, id) -> {
                                                            Intent intent = new Intent(ZXLB.this, ZXXQ.class);
                                                            intent.putExtra("id", s_zxlb_lvs.get(position).getId());
                                                            intent.putExtra("Id", s_zxlb_lvs.get(position).getLawyerId());
                                                            System.out.println("123"+s_zxlb_lvs.get(position).getId());
                                                            startActivity(intent);
                                                        });
                                                    });
                                                }
                                            }
                                        });
                            }
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
