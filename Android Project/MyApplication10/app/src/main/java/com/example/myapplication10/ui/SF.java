package com.example.myapplication10.ui;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication10.R;
import com.example.myapplication10.adapter.Adapter_DF_LV;
import com.example.myapplication10.adapter.Adapter_SH_LV;
import com.example.myapplication10.bean.S_SH_LV;
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

public class SF extends AppCompatActivity {
    private ArrayList<S_SH_LV> s_sh_lvs = new ArrayList<>();
    private Adapter_SH_LV adapter_sh_lv;
    private TextView back;
    private TextView header;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sf);
        initView();
        header.setText("水费");
        back.setOnClickListener(v -> {
            finish();
        });
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userid", "2");
            jsonObject.put("type", "1");
            new HttpUtil()
                    .sendResUtil("chargeList", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                s_sh_lvs = new Gson().fromJson(jsonObject1.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_SH_LV>>() {
                                }.getType());

                                adapter_sh_lv = new Adapter_SH_LV(SF.this, s_sh_lvs);
                                runOnUiThread(() -> {
                                    listview.setAdapter(adapter_sh_lv);
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
