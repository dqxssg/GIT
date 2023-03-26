package com.example.myapplication9.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication9.R;
import com.example.myapplication9.adapter.Adapter_WZCX_LB_LV;
import com.example.myapplication9.bean.S_WZCX_LB_LV;
import com.example.myapplication9.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WZCX_XQ extends AppCompatActivity {
    private ArrayList<S_WZCX_LB_LV> s_wzcx_lb_lvs = new ArrayList<>();
    private TextView back;
    private TextView header;
    private TextView wfsj;
    private TextView wfdd;
    private TextView wfxw;
    private TextView tzsh;
    private TextView wzkf;
    private TextView fkje;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wzcx_xq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        String name = bundle.getString("name");
        initView();
        header.setText("违章详情");
        back.setOnClickListener(v -> {
            finish();
        });
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("carid", name);
            new HttpUtil()
                    .sendResUtil("getViolationsByCarId", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                s_wzcx_lb_lvs = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_WZCX_LB_LV>>() {
                                }.getType());
                                for (S_WZCX_LB_LV s_wzcx_lb_lv : s_wzcx_lb_lvs) {
                                    if (Objects.equals(s_wzcx_lb_lv.getId(), id)) {
                                        wfsj.setText("违法时间：" + s_wzcx_lb_lv.getTime());
                                        wfdd.setText("违法地点：" + s_wzcx_lb_lv.getPlace());
                                        wfxw.setText("违法行为：" + s_wzcx_lb_lv.getDescription());
                                        tzsh.setText("通知书号：" + s_wzcx_lb_lv.getNotification());
                                        wzkf.setText("违章扣分：" + s_wzcx_lb_lv.getDeductPoints());
                                        fkje.setText("罚款金额：" + s_wzcx_lb_lv.getCost());
                                        break;
                                    }
                                }
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
        wfsj = (TextView) findViewById(R.id.wfsj);
        wfdd = (TextView) findViewById(R.id.wfdd);
        wfxw = (TextView) findViewById(R.id.wfxw);
        tzsh = (TextView) findViewById(R.id.tzsh);
        wzkf = (TextView) findViewById(R.id.wzkf);
        fkje = (TextView) findViewById(R.id.fkje);
    }
}
