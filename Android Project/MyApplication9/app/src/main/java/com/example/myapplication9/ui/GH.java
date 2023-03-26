package com.example.myapplication9.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication9.R;
import com.example.myapplication9.adapter.Adapter_GH_LV;
import com.example.myapplication9.bean.S_GH_LV;
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

public class GH extends AppCompatActivity {
    private ArrayList<S_GH_LV> s_gh_lvArrayList = new ArrayList<>();
    private Adapter_GH_LV adapter_gh_lv;
    private TextView back;
    private TextView header;
    private TextView pt;
    private TextView zj;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gh);
        Bundle bundle = this.getIntent().getExtras();
        String id = bundle.getString("id");
        String deptid = bundle.getString("deptid");
        String deptname = bundle.getString("deptname");
        String ID = bundle.getString("ID");
        String name = bundle.getString("name");
        String tel = bundle.getString("tel");
        initView();
        header.setText("挂号");
        back.setOnClickListener(v -> {
            finish();
        });
        TextColor(pt, zj);
        listview.setVisibility(View.VISIBLE);
        pt.setOnClickListener(v -> {
            TextColor(pt, zj);
            listview.setVisibility(View.VISIBLE);
        });
        zj.setOnClickListener(v -> {
            TextColor(zj, pt);
            listview.setVisibility(View.INVISIBLE);
        });
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("hospitalId", id);
            jsonObject.put("departmentId", deptid);
            new HttpUtil()
                    .sendResUtil("getDutyByDepartmentId", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                s_gh_lvArrayList = new Gson().fromJson(jsonObject1.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_GH_LV>>() {
                                }.getType());
                                adapter_gh_lv=new Adapter_GH_LV(GH.this,s_gh_lvArrayList);
                                runOnUiThread(()->{
                                    listview.setAdapter(adapter_gh_lv);
                                    listview.setOnItemClickListener((parent, view, position, id) -> {
                                        Intent intent=new Intent(GH.this,YYCG.class);
                                        intent.putExtra("time",s_gh_lvArrayList.get(position).getTime());
                                        intent.putExtra("deptid",deptid);
                                        intent.putExtra("deptname",deptname);
                                        intent.putExtra("ID",ID);
                                        intent.putExtra("tel",tel);
                                        intent.putExtra("name",name);
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
        pt = (TextView) findViewById(R.id.pt);
        zj = (TextView) findViewById(R.id.zj);
        listview = (ListView) findViewById(R.id.listview);
    }

    public void TextColor(TextView t1, TextView t2) {
        t1.setTextColor(Color.parseColor("#333333"));
        t2.setTextColor(Color.parseColor("#999999"));
    }
}
