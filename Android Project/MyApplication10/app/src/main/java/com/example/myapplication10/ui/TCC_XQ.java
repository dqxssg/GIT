package com.example.myapplication10.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication10.R;
import com.example.myapplication10.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TCC_XQ extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView tccmc;
    private TextView dz;
    private TextView jl;
    private TextView kf;
    private TextView fy;
    private TextView sycw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tcc_xq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        back.setOnClickListener(v -> {
            finish();
        });
        header.setText("停车详细信息");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("parkingid", id);
            new HttpUtil()
                    .sendResUtil("getParkInforById", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            JSONObject jsonObject1 = null;
                            try {
                                jsonObject1 = new JSONObject(s);
                                JSONArray jsonArray = jsonObject1.getJSONArray("ROWS_DETAIL");
                                runOnUiThread(() -> {
                                    try {
                                        tccmc.setText("停车场名称：" + jsonArray.getJSONObject(0).getString("parkName"));
                                        dz.setText("地址：" + jsonArray.getJSONObject(0).getString("address"));
                                        jl.setText("距离：" + jsonArray.getJSONObject(0).getString("surCarPort") + "米");
                                        if (Objects.equals(jsonArray.getJSONObject(0).getString("isOpen"), "Y")) {
                                            kf.setText("对外开放");
                                        } else {
                                            kf.setText("不对外开放");
                                        }
                                        fy.setText("收费：" + jsonArray.getJSONObject(0).getString("rateRefer"));
                                        sycw.setText("剩余车位：" + jsonArray.getJSONObject(0).getString("spaceNum"));
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
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
        tccmc = (TextView) findViewById(R.id.tccmc);
        dz = (TextView) findViewById(R.id.dz);
        jl = (TextView) findViewById(R.id.jl);
        kf = (TextView) findViewById(R.id.kf);
        fy = (TextView) findViewById(R.id.fy);
        sycw = (TextView) findViewById(R.id.sycw);
    }
}
