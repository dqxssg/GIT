package com.example.myapplication7.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication7.R;
import com.example.myapplication7.adapter.Adapter_ZFZ_LV;
import com.example.myapplication7.bean.S_ZFZ_LV;
import com.example.myapplication7.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZFZ extends AppCompatActivity {
    private ArrayList<S_ZFZ_LV> s_zfz_lvs = new ArrayList<>();
    private Adapter_ZFZ_LV adapter_zfz_lv;
    private TextView back;
    private TextView header;
    private EditText ss;
    private LinearLayout line1;
    private LinearLayout line2;
    private LinearLayout line3;
    private LinearLayout line4;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zfz);
        initView();
        header.setText("找房子");
        back.setOnClickListener(v -> {
            finish();
        });
        //房源展示
        FYZS("", "");
        ss.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                FYZS("", ss.getText().toString());
                return true;
            }
            return false;
        });
        line1.setOnClickListener(v -> {
            FYZS("二手", "");
        });
        line2.setOnClickListener(v -> {
            FYZS("租房", "");
        });
        line3.setOnClickListener(v -> {
            FYZS("楼盘", "");
        });
        line4.setOnClickListener(v -> {
            FYZS("中介", "");
        });
    }

    private void FYZS(String type, String name) {
        s_zfz_lvs.clear();
        new HttpUtil()
                .sendResuiltToken("/prod-api/api/house/housing/list?sourceName=" + name + "&houseType=" + type, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_zfz_lvs = new Gson().fromJson(jsonObject.getString("rows"), new TypeToken<List<S_ZFZ_LV>>() {
                            }.getType());
                            adapter_zfz_lv = new Adapter_ZFZ_LV(ZFZ.this, s_zfz_lvs);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_zfz_lv);
                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(ZFZ.this,ZFZ_XQ.class);
                                    intent.putExtra("id", s_zfz_lvs.get(position).getId());
                                    startActivity(intent);
                                });
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < s_zfz_lvs.size(); i++) {
                                    View item = listAdapter.getView(i, null, listview);
                                    item.measure(1, 1);
                                    h += item.getMeasuredHeight();
                                }
                                ViewGroup.LayoutParams params = listview.getLayoutParams();
                                params.height = h + listview.getDividerHeight() * (listAdapter.getCount() - 1);
                                listview.setLayoutParams(params);
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
        ss = (EditText) findViewById(R.id.ss);
        line1 = (LinearLayout) findViewById(R.id.line1);
        line2 = (LinearLayout) findViewById(R.id.line2);
        line3 = (LinearLayout) findViewById(R.id.line3);
        line4 = (LinearLayout) findViewById(R.id.line4);
        listview = (ListView) findViewById(R.id.listview);
    }
}
