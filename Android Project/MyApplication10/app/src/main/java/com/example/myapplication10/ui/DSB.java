package com.example.myapplication10.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication10.R;
import com.example.myapplication10.bean.S_ZHBS_E;
import com.example.myapplication10.util.HttpUtil;
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
    private ArrayList<S_ZHBS_E> s_zhbs_es = new ArrayList<>();
    private ArrayAdapter<String> adapter_SC;
    private ArrayList<String> sc = new ArrayList<>();
    private ArrayAdapter<String> adapter_XC;
    private ArrayList<String> xc = new ArrayList<>();
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
        Bundle bundle = this.getIntent().getExtras();
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

        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("busid", id);
            new HttpUtil()
                    .sendResUtil("busStationById", jsonObject1.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s1 = response.body().string();
                            try {
                                JSONObject jsonObject2 = new JSONObject(s1);
                                s_zhbs_es = new Gson().fromJson(jsonObject2.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_ZHBS_E>>() {
                                }.getType());
                                for (S_ZHBS_E s_zhbs_e : s_zhbs_es) {
                                    sc.add(s_zhbs_e.getSiteName());
                                    xc.add(s_zhbs_e.getSiteName());
                                }
                                adapter_SC = new ArrayAdapter<String>(DSB.this, android.R.layout.simple_spinner_item, sc);
                                adapter_XC = new ArrayAdapter<String>(DSB.this, android.R.layout.simple_spinner_item, xc);
                                adapter_SC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                adapter_XC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                runOnUiThread(() -> {
                                    qd.setText(s_zhbs_es.get(0).getSiteName());
                                    zd.setText(s_zhbs_es.get(s_zhbs_es.size() - 1).getSiteName());
                                    scdd.setAdapter(adapter_SC);
                                    xcdd.setAdapter(adapter_XC);
                                    xyb.setOnClickListener(v -> {
                                        Intent intent = new Intent(DSB.this, DSIB.class);
                                        intent.putExtra("ckxm", ckxx.getText().toString());
                                        intent.putExtra("sjhm", sjhm.getText().toString());
                                        intent.putExtra("scdd", scdd.getSelectedItem().toString());
                                        intent.putExtra("xcdd", xcdd.getSelectedItem().toString());
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
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
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
