package com.example.myapplication6.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication6.R;
import com.example.myapplication6.adapter.Adapter_MZYY_LV;
import com.example.myapplication6.bean.S_MZYY_LV;
import com.example.myapplication6.util.HttpUtil;
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

public class MZYY extends AppCompatActivity {
    private ArrayList<S_MZYY_LV> s_mzyy_lvs = new ArrayList<>();
    private Adapter_MZYY_LV adapter_mzyy_lv;
    private TextView back;
    private TextView header;
    private ListView listview;
    private EditText ss;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mzyy);
        initView();
        header.setText("门诊预约");
        back.setOnClickListener(v -> {
            finish();
        });
        //listview
        LV("");
        ss.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                LV(ss.getText().toString());
                return true;
            }
            return false;
        });
    }

    private void LV(String name) {
        new HttpUtil()
                .sendResurltToken("/prod-api/api/hospital/hospital/list?hospitalName" + name, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_mzyy_lvs = new Gson().fromJson(jsonObject.getString("rows"), new TypeToken<List<S_MZYY_LV>>() {
                            }.getType());
                            adapter_mzyy_lv = new Adapter_MZYY_LV(MZYY.this, s_mzyy_lvs);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_mzyy_lv);
                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(MZYY.this, YYJJ.class);
                                    intent.putExtra("id", s_mzyy_lvs.get(position).getId());
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
        listview = (ListView) findViewById(R.id.listview);
        ss = (EditText) findViewById(R.id.ss);
    }
}
