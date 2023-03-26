package com.example.myapplication6.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication6.R;
import com.example.myapplication6.adapter.Adapter_SZTSG_LV;
import com.example.myapplication6.bean.S_SZTSG_LV;
import com.example.myapplication6.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SZTSG extends AppCompatActivity {
    private ArrayList<S_SZTSG_LV> s_sztsg_lvs = new ArrayList<>();
    private Adapter_SZTSG_LV adapter_sztsg_lv;
    private TextView back;
    private TextView header;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sztsg);
        initView();
        header.setText("数字图书馆");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResurltToken("/prod-api/api/digital-library/library/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_sztsg_lvs = new Gson().fromJson(jsonObject.getString("rows"), new TypeToken<List<S_SZTSG_LV>>() {
                            }.getType());
                            s_sztsg_lvs.sort(new Comparator<S_SZTSG_LV>() {
                                @Override
                                public int compare(S_SZTSG_LV o1, S_SZTSG_LV o2) {
                                    return o2.getBusinessState().compareTo(o1.getBusinessState());
                                }
                            });
                            adapter_sztsg_lv = new Adapter_SZTSG_LV(SZTSG.this, s_sztsg_lvs);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_sztsg_lv);
                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(SZTSG.this,TSGXQ.class);
                                    intent.putExtra("id", s_sztsg_lvs.get(position).getId());
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
    }
}
