package com.example.myapplication4.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication4.R;
import com.example.myapplication4.bean.S_ZHBS_ER;
import com.example.myapplication4.util.Httputil;
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

public class DSB extends AppCompatActivity {
    public static Activity sactivity;
    private ArrayAdapter<String> adapter_SC;
    private ArrayList<String> sc=new ArrayList<>();
    private ArrayAdapter<String> adapter_XC;
    private ArrayList<String> xc=new ArrayList<>();
    private ArrayList<S_ZHBS_ER> s_zhbs_ers = new ArrayList<>();
    private TextView back;
    private TextView header;
    private TextView qd;
    private TextView zd;
    private EditText ckxx;
    private EditText sjhm;
    private Spinner scdd;
    private Spinner xcdd;
    private TextView xyb;
    private TextView fh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dsb);
        sactivity=this;
        Bundle bundle = this.getIntent().getExtras();
        int i = bundle.getInt("i");
        int id = bundle.getInt("id");
        String data = bundle.getString("data");
        initView();
        header.setText("智慧巴士");
        back.setOnClickListener(v -> {
            finish();
        });
        fh.setOnClickListener(v -> {
            finish();
        });
        new Httputil()
                .sendResultToken("/prod-api/api/bus/stop/list?linesId=" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_zhbs_ers = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_ZHBS_ER>>() {
                            }.getType());
                            for (S_ZHBS_ER s_zhbs_er : s_zhbs_ers) {
                                sc.add(s_zhbs_er.getName());
                                xc.add(s_zhbs_er.getName());
                            }
                            adapter_SC = new ArrayAdapter<String>(DSB.this, android.R.layout.simple_spinner_item, sc);
                            adapter_XC = new ArrayAdapter<String>(DSB.this, android.R.layout.simple_spinner_item, xc);
                            adapter_SC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            adapter_XC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            runOnUiThread(() -> {
                                qd.setText(s_zhbs_ers.get(0).getName());
                                zd.setText(s_zhbs_ers.get(s_zhbs_ers.size() - 1).getName());
                                scdd.setAdapter(adapter_SC);
                                xcdd.setAdapter(adapter_XC);
                                xyb.setOnClickListener(v -> {
                                    Intent intent = new Intent(DSB.this, DSIB.class);
                                    intent.putExtra("ckxm", ckxx.getText().toString());
                                    intent.putExtra("sjhm", sjhm.getText().toString());
                                    intent.putExtra("scdd", scdd.getSelectedItem().toString());
                                    intent.putExtra("xcdd", xcdd.getSelectedItem().toString());
                                    intent.putExtra("i", i);
                                    intent.putExtra("id", id);
                                    intent.putExtra("data", data);
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
        qd = (TextView) findViewById(R.id.qd);
        zd = (TextView) findViewById(R.id.zd);
        ckxx = (EditText) findViewById(R.id.ckxx);
        sjhm = (EditText) findViewById(R.id.sjhm);
        scdd = (Spinner) findViewById(R.id.scdd);
        xcdd = (Spinner) findViewById(R.id.xcdd);
        xyb = (TextView) findViewById(R.id.xyb);
        fh = (TextView) findViewById(R.id.fh);
    }
}
