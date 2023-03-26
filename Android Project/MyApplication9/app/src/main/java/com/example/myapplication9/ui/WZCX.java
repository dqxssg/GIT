package com.example.myapplication9.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication9.R;
import com.example.myapplication9.bean.S_WZCX_Spinner1;
import com.example.myapplication9.bean.S_WZCX_Spinner2;
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

public class WZCX extends AppCompatActivity {
    private ArrayList<S_WZCX_Spinner1> s_wzcx_spinner1s = new ArrayList<>();
    private ArrayList<S_WZCX_Spinner2> s_wzcx_spinner2s = new ArrayList<>();
    private ArrayList<String> s_spinner1s = new ArrayList<>();
    private ArrayList<String> s_spinner2s = new ArrayList<>();
    private ArrayAdapter<String> adapter_spinner1;
    private ArrayAdapter<String> adapter_spinner2;
    private TextView back;
    private TextView header;
    private Spinner spinner1 = null;
    private Spinner spinner2 = null;
    private EditText sr;
    private TextView cx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wzcx);
        initView();
        header.setText("违章查询");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResUtil("getAllCarType", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_wzcx_spinner1s = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_WZCX_Spinner1>>() {
                            }.getType());
                            for (S_WZCX_Spinner1 s_wzcx_spinner1 : s_wzcx_spinner1s) {
                                s_spinner1s.add(s_wzcx_spinner1.getName());
                            }
                            adapter_spinner1 = new ArrayAdapter<String>(WZCX.this, android.R.layout.simple_spinner_item, s_spinner1s);
                            adapter_spinner1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            runOnUiThread(() -> {
                                spinner1.setAdapter(adapter_spinner1);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        new HttpUtil()
                .sendResUtil("getCarPlace", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_wzcx_spinner2s = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_WZCX_Spinner2>>() {
                            }.getType());
                            for (S_WZCX_Spinner2 s_wzcx_spinner2 : s_wzcx_spinner2s) {
                                s_spinner2s.add(s_wzcx_spinner2.getName());
                            }
                            adapter_spinner2 = new ArrayAdapter<String>(WZCX.this, android.R.layout.simple_spinner_item, s_spinner2s);
                            adapter_spinner2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            runOnUiThread(() -> {
                                spinner2.setAdapter(adapter_spinner2);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        cx.setOnClickListener(v -> {
            Intent intent = new Intent(WZCX.this,WZCX_LB.class);
            intent.putExtra("name", spinner2.getSelectedItem().toString() + sr.getText().toString());
            startActivity(intent);
        });

    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        sr = (EditText) findViewById(R.id.sr);
        cx = (TextView) findViewById(R.id.cx);
    }
}
