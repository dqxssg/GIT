package com.example.myapplication4.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication4.R;
import com.example.myapplication4.util.Httputil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class XGCL extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private EditText cph;
    private EditText cjh;
    private EditText cllx;
    private TextView tj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xgcl);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        String PlateNo = bundle.getString("PlateNo");
        initView();
        header.setText("修改车辆信息");
        back.setOnClickListener(v -> {
            finish();
        });
        new Httputil()
                .sendResultToken("/prod-api/api/traffic/car/list?plateNo=" + PlateNo, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("rows");
                            runOnUiThread(() -> {
                                try {
                                    cph.setText(jsonArray.getJSONObject(0).getString("plateNo"));
                                    cjh.setText(jsonArray.getJSONObject(0).getString("engineNo"));
                                    cllx.setText(jsonArray.getJSONObject(0).getString("type"));
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
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
        cph = (EditText) findViewById(R.id.cph);
        cjh = (EditText) findViewById(R.id.cjh);
        cllx = (EditText) findViewById(R.id.cllx);
        tj = (TextView) findViewById(R.id.tj);
    }
}
