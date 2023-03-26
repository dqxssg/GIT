package com.example.myapplication2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;
import com.example.myapplication2.adapter.Ad_L_LV2;
import com.example.myapplication2.adapter.Adapter_LV_ListView2;
import com.example.myapplication2.bean.SJ_L_LV2;
import com.example.myapplication2.util.HttpUtil;
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

public class L_SS extends AppCompatActivity {
    private ArrayList<SJ_L_LV2> sj_l_lv2s = new ArrayList<>();
    private Ad_L_LV2 ad_l_lv2;
    private TextView back;
    private TextView header;
    private ListView listview2;
    private Spinner spinner;
    private TextView sx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_ss);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        String id = bundle.getString("id");
        initView();
        header.setText("律师列表");
        back.setOnClickListener(v -> {
            finish();
        });
        XS(name, id);
    }

    private void XS(String name, String id) {
        new HttpUtil().sendResultToken("/prod-api/api/lawyer-consultation/lawyer/list?legalExpertiseId=" + id + "&name=" + name, "", "GET", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    sj_l_lv2s = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<SJ_L_LV2>>() {
                    }.getType());
                    PX();
                    ad_l_lv2 = new Ad_L_LV2(L_SS.this, sj_l_lv2s);
                    ad_l_lv2.setOnItemListener(new Adapter_LV_ListView2.onItemListener() {
                        @Override
                        public void onClick(int i) {
                            Intent intent = new Intent(L_SS.this, LXQ.class);
                            intent.putExtra("id", sj_l_lv2s.get(i).getId());
                            startActivity(intent);
                        }
                    });
                    runOnUiThread(() -> {
                        listview2.setAdapter(ad_l_lv2);
                        ListAdapter listAdapter = listview2.getAdapter();
                        if (listAdapter == null) {
                            return;
                        }
                        int h = 0;
                        for (int i = 0; i < sj_l_lv2s.size(); i++) {
                            View item = listAdapter.getView(i, null, listview2);
                            item.measure(1, 1);
                            h += item.getMeasuredHeight();
                        }
                        ViewGroup.LayoutParams params = listview2.getLayoutParams();
                        params.height = h + listview2.getDividerHeight() * (listAdapter.getCount() - 1);
                        listview2.setLayoutParams(params);
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }

    private void PX() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (L_SS.this.getResources().getStringArray(R.array.province)[position]) {
                    case "从业年限升序":
                        sj_l_lv2s.sort(new Comparator<SJ_L_LV2>() {
                            @Override
                            public int compare(SJ_L_LV2 o1, SJ_L_LV2 o2) {
                                return o1.getWorkStartAt().compareTo(o2.getWorkStartAt());
                            }
                        });
                        break;
                    case "从业年限降序":
                        sj_l_lv2s.sort(new Comparator<SJ_L_LV2>() {
                            @Override
                            public int compare(SJ_L_LV2 o1, SJ_L_LV2 o2) {
                                return o2.getWorkStartAt().compareTo(o1.getWorkStartAt());
                            }
                        });
                        break;
                    case "服务人数升序":
                        sj_l_lv2s.sort(new Comparator<SJ_L_LV2>() {
                            @Override
                            public int compare(SJ_L_LV2 o1, SJ_L_LV2 o2) {
                                return o1.getServiceTimes() - o2.getServiceTimes();
                            }
                        });
                        break;
                    case "服务人数降序":
                        sj_l_lv2s.sort(new Comparator<SJ_L_LV2>() {
                            @Override
                            public int compare(SJ_L_LV2 o1, SJ_L_LV2 o2) {
                                return o2.getServiceTimes() - o1.getServiceTimes();
                            }
                        });
                        break;
                    case "好评率升序":
                        sj_l_lv2s.sort(new Comparator<SJ_L_LV2>() {
                            @Override
                            public int compare(SJ_L_LV2 o1, SJ_L_LV2 o2) {
                                return o1.getFavorableRate() - o2.getFavorableRate();
                            }
                        });
                        break;
                    case "好评率降序":
                        sj_l_lv2s.sort(new Comparator<SJ_L_LV2>() {
                            @Override
                            public int compare(SJ_L_LV2 o1, SJ_L_LV2 o2) {
                                return o2.getFavorableRate() - o1.getFavorableRate();
                            }
                        });
                        break;
                    default:
                        sj_l_lv2s.sort(new Comparator<SJ_L_LV2>() {
                            @Override
                            public int compare(SJ_L_LV2 o1, SJ_L_LV2 o2) {
                                return o2.getId() - o1.getId();
                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        listview2 = (ListView) findViewById(R.id.listview2);
        spinner = (Spinner) findViewById(R.id.spinner);
        sx = (TextView) findViewById(R.id.sx);
    }
}
