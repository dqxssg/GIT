package com.example.myapplication5.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication5.R;
import com.example.myapplication5.adapter.Adapter_QBFW_GV;
import com.example.myapplication5.bean.S_A_GV;
import com.example.myapplication5.util.HttpUtil;
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

public class QBFW extends AppCompatActivity {
    private ArrayList<S_A_GV> s_a_gvArrayList = new ArrayList<>();
    private Adapter_QBFW_GV adapter_a_gv;
    private TextView back;
    private TextView header;
    private GridView gridview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qbfw);
        initView();
        header.setText("全部服务");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResurltToken("/prod-api/api/service/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_a_gvArrayList = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_A_GV>>() {
                            }.getType());
                            s_a_gvArrayList.sort(new Comparator<S_A_GV>() {
                                @Override
                                public int compare(S_A_GV o1, S_A_GV o2) {
                                    return o2.getId() - o1.getId();
                                }
                            });
                            s_a_gvArrayList.add(new S_A_GV().setServiceName("垃圾分类"));
                            s_a_gvArrayList.add(new S_A_GV().setServiceName("律师咨询主页"));
                            adapter_a_gv = new Adapter_QBFW_GV(QBFW.this, s_a_gvArrayList);
                            runOnUiThread(() -> {
                                gridview.setAdapter(adapter_a_gv);
                                gridview.setOnItemClickListener((parent, view, position, id) -> {
                                    switch (position) {
                                        case 2:
                                            finish();
                                            MainActivity.ZT_C();
                                            break;
                                        case 18:
                                            finish();
                                            MainActivity.ZT_B();
                                            break;
                                        case 19:
                                            finish();
                                            MainActivity.ZT_D();
                                            break;
                                        default:
                                            Intent intent = new Intent(QBFW.this, FW.class);
                                            intent.putExtra("name", s_a_gvArrayList.get(position).getServiceName());
                                            startActivity(intent);
                                            break;
                                    }
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
        gridview = (GridView) findViewById(R.id.gridview);
    }
}
