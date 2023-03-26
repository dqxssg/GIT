package com.example.myapplication9.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication9.R;
import com.example.myapplication9.adapter.Adapter_MZKSFL_LV;
import com.example.myapplication9.bean.S_MZKSFL_LV;
import com.example.myapplication9.util.HttpUtil;
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

public class MZKSFL extends AppCompatActivity {
    private ArrayList<S_MZKSFL_LV> s_mzksfl_lvs = new ArrayList<>();
    private Adapter_MZKSFL_LV adapter_mzksfl_lv;
    private TextView back;
    private TextView header;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zksfl);
        Bundle bundle = this.getIntent().getExtras();
        String id = bundle.getString("id");
        String ID = bundle.getString("ID");
        String tel = bundle.getString("tel");
        String name = bundle.getString("name");
        initView();
        header.setText("门诊科室分诊");
        back.setOnClickListener(v -> {
            finish();
        });
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("hospitalId", id);
            new HttpUtil()
                    .sendResUtil("deptList", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            JSONObject jsonObject1 = null;
                            try {
                                jsonObject1 = new JSONObject(s);
                                s_mzksfl_lvs = new Gson().fromJson(jsonObject1.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_MZKSFL_LV>>() {
                                }.getType());
                                adapter_mzksfl_lv = new Adapter_MZKSFL_LV(MZKSFL.this, s_mzksfl_lvs);
                                runOnUiThread(() -> {
                                    listview.setAdapter(adapter_mzksfl_lv);
                                    listview.setOnItemClickListener((parent, view, position, id1) -> {
                                        Intent intent = new Intent(MZKSFL.this, GH.class);
                                        intent.putExtra("id", id);
                                        intent.putExtra("deptid", s_mzksfl_lvs.get(position).getDeptId());
                                        intent.putExtra("deptname", s_mzksfl_lvs.get(position).getDeptName());
                                        intent.putExtra("ID", ID);
                                        intent.putExtra("tel", tel);
                                        intent.putExtra("name", name);
                                        startActivity(intent);
                                    });
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

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        listview = (ListView) findViewById(R.id.listview);
    }
}
