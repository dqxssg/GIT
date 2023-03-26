package com.example.myapplication2.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;
import com.example.myapplication2.adapter.Adapter_TNEJL_ListView;
import com.example.myapplication2.bean.ShuJv_TNEJL_ListView;
import com.example.myapplication2.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TNEJL extends AppCompatActivity {
    private ArrayList<ShuJv_TNEJL_ListView> shuJv_tnejl_listViews = new ArrayList<>();
    private Adapter_TNEJL_ListView adapter_tnejl_listView;
    private TextView back;
    private TextView header;
    private TextView rc;
    private TextView cc;
    private TextView cx;
    private ListView listview;
    private TextView ckgd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tnejl);
        initView();
        header.setText("停车记录");
        back.setOnClickListener(v -> {
            finish();
        });
        //停车记录
        TCJL("", "",5);
        ckgd.setOnClickListener(v -> {
            ckgd.setVisibility(View.INVISIBLE);
            TCJL("", "",10);
        });
        rc.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(TNEJL.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    new TimePickerDialog(TNEJL.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            rc.setText(year + "-" + month + "-" + dayOfMonth + " " + hourOfDay + ":" + minute + ":00");
                        }
                    }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();

                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        cc.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(TNEJL.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    new TimePickerDialog(TNEJL.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            cc.setText(year + "-" + month + "-" + dayOfMonth + " " + hourOfDay + ":" + minute + ":00");
                        }
                    }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();

                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        cx.setOnClickListener(v -> {
            TCJL(rc.getText().toString(), cc.getText().toString(), 10);
        });
    }

    private void TCJL(String RC, String CC,int i) {
        shuJv_tnejl_listViews.clear();
        new HttpUtil()
                .sendResultToken("/prod-api/api/park/lot/record/list?entryTime=" + RC + "&outTime=" + CC, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_tnejl_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_TNEJL_ListView>>() {
                            }.getType());
                            adapter_tnejl_listView = new Adapter_TNEJL_ListView(TNEJL.this, shuJv_tnejl_listViews,i);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_tnejl_listView);
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int ii = 0; ii < i; ii++) {
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
        rc = (TextView) findViewById(R.id.rc);
        cc = (TextView) findViewById(R.id.cc);
        cx = (TextView) findViewById(R.id.cx);
        listview = (ListView) findViewById(R.id.listview);
        ckgd = (TextView) findViewById(R.id.ckgd);
    }
}
