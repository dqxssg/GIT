package com.example.myapplication10.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication10.R;
import com.example.myapplication10.adapter.Adapter_TCJL_LV;
import com.example.myapplication10.bean.S_TCJL_LV;
import com.example.myapplication10.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TCJL extends AppCompatActivity {
    private ArrayList<String> tcc = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ArrayList<S_TCJL_LV> s_tcjl_lvs = new ArrayList<>();
    private Adapter_TCJL_LV adapter_tcjl_lv;
    private TextView back;
    private TextView header;
    private TextView rc;
    private TextView cc;
    private ListView listview;
    private TextView ckgd;
    private TextView cx;
    private Spinner spinner = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tcjl);
        initView();
        header.setText("停车记录");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResUtil("getParkInfor", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                tcc.add(jsonArray.getJSONObject(i).getString("parkName"));
                            }
                            adapter = new ArrayAdapter<String>(TCJL.this, android.R.layout.simple_spinner_item, tcc);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            runOnUiThread(() -> {
                                spinner.setAdapter(adapter);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });
        ckgd.setOnClickListener(v -> {
            ckgd.setVisibility(View.INVISIBLE);
            LV(s_tcjl_lvs.size());
        });
        rc.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(TCJL.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    rc.setText(year + "-" + month + "-" + dayOfMonth);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        cc.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(TCJL.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    cc.setText(year + "-" + month + "-" + dayOfMonth);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        cx.setOnClickListener(v -> {
            if (!Objects.equals("入场时间", rc.getText().toString()) && !Objects.equals("出场时间", cc.getText().toString())) {
                ckgd.setVisibility(View.INVISIBLE);
                //listview_rc_cc
                LVRC(rc.getText().toString(), cc.getText().toString());
            } else if (Objects.equals("入场时间", rc.getText().toString()) && !Objects.equals(cc.getText().toString(), "出场时间")) {
                ckgd.setVisibility(View.INVISIBLE);
                //listview_cc
                LVC(cc.getText().toString());
            } else if (Objects.equals("出场时间", cc.getText().toString()) && !Objects.equals(rc.getText().toString(), "入场时间")) {
                ckgd.setVisibility(View.INVISIBLE);
                //listview_rc
                LVR(rc.getText().toString());
            } else {
                LV(s_tcjl_lvs.size());
            }
        });
        //listview
        LV(5);
    }

    private void LVRC(String time1, String time2) {
        int id = 0;
        switch (spinner.getSelectedItem().toString()) {
            case "德百停车场":
                id = 1;
                break;
            case "天衢停车场":
                id = 2;
                break;
            case "奥德乐停车场":
                id = 3;
                break;
            case "银座大学路店停车场":
                id = 4;
                break;
            case "中心广场停车场":
                id = 5;
                break;
            case "人民医院停车场":
                id = 6;
                break;
            case "大剧院停车场":
                id = 7;
                break;
            case "长河公园停车场":
                id = 8;
                break;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("parkingid", id);
            jsonObject.put("inTime1", time1);
            jsonObject.put("inTime2", time2);
            new HttpUtil()
                    .sendResUtil("getParkingHistoryByIdAndInTime", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                s_tcjl_lvs.clear();
                                s_tcjl_lvs = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_TCJL_LV>>() {
                                }.getType());
                                if (s_tcjl_lvs != null && !s_tcjl_lvs.isEmpty()) {
                                    adapter_tcjl_lv = new Adapter_TCJL_LV(TCJL.this, s_tcjl_lvs, s_tcjl_lvs.size());
                                    runOnUiThread(() -> {
                                        listview.setAdapter(adapter_tcjl_lv);
                                        ListAdapter listAdapter = listview.getAdapter();
                                        if (listAdapter == null) {
                                            return;
                                        }
                                        int h = 0;
                                        for (int i = 0; i < s_tcjl_lvs.size(); i++) {
                                            View item = listAdapter.getView(i, null, listview);
                                            item.measure(1, 1);
                                            h += item.getMeasuredHeight();
                                        }
                                        ViewGroup.LayoutParams params = listview.getLayoutParams();
                                        params.height = h + listview.getDividerHeight() * (listAdapter.getCount() - 1);
                                        listview.setLayoutParams(params);
                                    });
                                } else {
                                    runOnUiThread(() -> {
                                        s_tcjl_lvs.clear();
                                        Toast.makeText(TCJL.this, "未查到", Toast.LENGTH_SHORT).show();
                                        adapter_tcjl_lv = new Adapter_TCJL_LV(TCJL.this, s_tcjl_lvs, s_tcjl_lvs.size());
                                        listview.setAdapter(adapter_tcjl_lv);
                                        ListAdapter listAdapter = listview.getAdapter();
                                        if (listAdapter == null) {
                                            return;
                                        }
                                        int h = 0;
                                        for (int i = 0; i < s_tcjl_lvs.size(); i++) {
                                            View item = listAdapter.getView(i, null, listview);
                                            item.measure(1, 1);
                                            h += item.getMeasuredHeight();
                                        }
                                        ViewGroup.LayoutParams params = listview.getLayoutParams();
                                        params.height = h + listview.getDividerHeight() * (listAdapter.getCount() - 1);
                                        listview.setLayoutParams(params);
                                    });
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

    private void LVR(String time) {
        int id = 0;
        switch (spinner.getSelectedItem().toString()) {
            case "德百停车场":
                id = 1;
                break;
            case "天衢停车场":
                id = 2;
                break;
            case "奥德乐停车场":
                id = 3;
                break;
            case "银座大学路店停车场":
                id = 4;
                break;
            case "中心广场停车场":
                id = 5;
                break;
            case "人民医院停车场":
                id = 6;
                break;
            case "大剧院停车场":
                id = 7;
                break;
            case "长河公园停车场":
                id = 8;
                break;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("parkingid", id);
            jsonObject.put("inTime1", time);
            jsonObject.put("inTime2", "");
            new HttpUtil()
                    .sendResUtil("getParkingHistoryByIdAndInTime", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                s_tcjl_lvs.clear();
                                s_tcjl_lvs = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_TCJL_LV>>() {
                                }.getType());
                                if (s_tcjl_lvs != null && !s_tcjl_lvs.isEmpty()) {
                                    adapter_tcjl_lv = new Adapter_TCJL_LV(TCJL.this, s_tcjl_lvs, s_tcjl_lvs.size());
                                    runOnUiThread(() -> {
                                        listview.setAdapter(adapter_tcjl_lv);
                                        ListAdapter listAdapter = listview.getAdapter();
                                        if (listAdapter == null) {
                                            return;
                                        }
                                        int h = 0;
                                        for (int i = 0; i < s_tcjl_lvs.size(); i++) {
                                            View item = listAdapter.getView(i, null, listview);
                                            item.measure(1, 1);
                                            h += item.getMeasuredHeight();
                                        }
                                        ViewGroup.LayoutParams params = listview.getLayoutParams();
                                        params.height = h + listview.getDividerHeight() * (listAdapter.getCount() - 1);
                                        listview.setLayoutParams(params);
                                    });
                                } else {
                                    runOnUiThread(() -> {
                                        s_tcjl_lvs.clear();
                                        Toast.makeText(TCJL.this, "未查到", Toast.LENGTH_SHORT).show();
                                        adapter_tcjl_lv = new Adapter_TCJL_LV(TCJL.this, s_tcjl_lvs, s_tcjl_lvs.size());
                                        listview.setAdapter(adapter_tcjl_lv);
                                        ListAdapter listAdapter = listview.getAdapter();
                                        if (listAdapter == null) {
                                            return;
                                        }
                                        int h = 0;
                                        for (int i = 0; i < s_tcjl_lvs.size(); i++) {
                                            View item = listAdapter.getView(i, null, listview);
                                            item.measure(1, 1);
                                            h += item.getMeasuredHeight();
                                        }
                                        ViewGroup.LayoutParams params = listview.getLayoutParams();
                                        params.height = h + listview.getDividerHeight() * (listAdapter.getCount() - 1);
                                        listview.setLayoutParams(params);
                                    });
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

    private void LVC(String time) {
        int id = 0;
        switch (spinner.getSelectedItem().toString()) {
            case "德百停车场":
                id = 1;
                break;
            case "天衢停车场":
                id = 2;
                break;
            case "奥德乐停车场":
                id = 3;
                break;
            case "银座大学路店停车场":
                id = 4;
                break;
            case "中心广场停车场":
                id = 5;
                break;
            case "人民医院停车场":
                id = 6;
                break;
            case "大剧院停车场":
                id = 7;
                break;
            case "长河公园停车场":
                id = 8;
                break;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("parkingid", id);
            jsonObject.put("inTime1", time);
            jsonObject.put("inTime2", "");
            new HttpUtil()
                    .sendResUtil("getParkingHistoryByIdAndInTime", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                s_tcjl_lvs.clear();
                                s_tcjl_lvs = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_TCJL_LV>>() {
                                }.getType());
                                if (s_tcjl_lvs != null && !s_tcjl_lvs.isEmpty()) {
                                    adapter_tcjl_lv = new Adapter_TCJL_LV(TCJL.this, s_tcjl_lvs, s_tcjl_lvs.size());
                                    runOnUiThread(() -> {
                                        listview.setAdapter(adapter_tcjl_lv);
                                        ListAdapter listAdapter = listview.getAdapter();
                                        if (listAdapter == null) {
                                            return;
                                        }
                                        int h = 0;
                                        for (int i = 0; i < s_tcjl_lvs.size(); i++) {
                                            View item = listAdapter.getView(i, null, listview);
                                            item.measure(1, 1);
                                            h += item.getMeasuredHeight();
                                        }
                                        ViewGroup.LayoutParams params = listview.getLayoutParams();
                                        params.height = h + listview.getDividerHeight() * (listAdapter.getCount() - 1);
                                        listview.setLayoutParams(params);
                                    });
                                } else {
                                    runOnUiThread(() -> {
                                        s_tcjl_lvs.clear();
                                        Toast.makeText(TCJL.this, "未查到", Toast.LENGTH_SHORT).show();
                                        adapter_tcjl_lv = new Adapter_TCJL_LV(TCJL.this, s_tcjl_lvs, s_tcjl_lvs.size());
                                        listview.setAdapter(adapter_tcjl_lv);
                                        ListAdapter listAdapter = listview.getAdapter();
                                        if (listAdapter == null) {
                                            return;
                                        }
                                        int h = 0;
                                        for (int i = 0; i < s_tcjl_lvs.size(); i++) {
                                            View item = listAdapter.getView(i, null, listview);
                                            item.measure(1, 1);
                                            h += item.getMeasuredHeight();
                                        }
                                        ViewGroup.LayoutParams params = listview.getLayoutParams();
                                        params.height = h + listview.getDividerHeight() * (listAdapter.getCount() - 1);
                                        listview.setLayoutParams(params);
                                    });
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

    private void LV(int i1) {
        new HttpUtil()
                .sendResUtil("getParkingHistory", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_tcjl_lvs.clear();
                            s_tcjl_lvs = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_TCJL_LV>>() {
                            }.getType());
                            if (s_tcjl_lvs != null && !s_tcjl_lvs.isEmpty()) {
                                adapter_tcjl_lv = new Adapter_TCJL_LV(TCJL.this, s_tcjl_lvs, i1);
                                runOnUiThread(() -> {
                                    listview.setAdapter(adapter_tcjl_lv);
                                    ListAdapter listAdapter = listview.getAdapter();
                                    if (listAdapter == null) {
                                        return;
                                    }
                                    int h = 0;
                                    for (int i = 0; i < i1; i++) {
                                        View item = listAdapter.getView(i, null, listview);
                                        item.measure(1, 1);
                                        h += item.getMeasuredHeight();
                                    }
                                    ViewGroup.LayoutParams params = listview.getLayoutParams();
                                    params.height = h + listview.getDividerHeight() * (listAdapter.getCount() - 1);
                                    listview.setLayoutParams(params);
                                });
                            } else {
                                runOnUiThread(() -> {
                                    s_tcjl_lvs.clear();
                                    Toast.makeText(TCJL.this, "未查到", Toast.LENGTH_SHORT).show();
                                    adapter_tcjl_lv = new Adapter_TCJL_LV(TCJL.this, s_tcjl_lvs, s_tcjl_lvs.size());
                                    listview.setAdapter(adapter_tcjl_lv);
                                    ListAdapter listAdapter = listview.getAdapter();
                                    if (listAdapter == null) {
                                        return;
                                    }
                                    int h = 0;
                                    for (int i = 0; i < s_tcjl_lvs.size(); i++) {
                                        View item = listAdapter.getView(i, null, listview);
                                        item.measure(1, 1);
                                        h += item.getMeasuredHeight();
                                    }
                                    ViewGroup.LayoutParams params = listview.getLayoutParams();
                                    params.height = h + listview.getDividerHeight() * (listAdapter.getCount() - 1);
                                    listview.setLayoutParams(params);
                                });
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        rc = (TextView) findViewById(R.id.rc);
        cc = (TextView) findViewById(R.id.cc);
        listview = (ListView) findViewById(R.id.listview);
        ckgd = (TextView) findViewById(R.id.ckgd);
        cx = (TextView) findViewById(R.id.cx);
        spinner = (Spinner) findViewById(R.id.spinner);
    }
}
