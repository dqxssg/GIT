package com.example.myapplication4.adapter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication4.R;
import com.example.myapplication4.bean.S_Dialog;
import com.example.myapplication4.ui.WDYY;
import com.example.myapplication4.util.Httputil;
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

public class Adapter_LJYY_Dialog extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private ArrayList<String> DD = new ArrayList<>();
    private ArrayList<S_Dialog> s_dialogs = new ArrayList<>();
    private TextView sj;
    private Spinner dd = null;
    private TextView yy;
    private TextView back;
    private TextView header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter_ljyy_dialog);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("立即预约");
        back.setOnClickListener(v -> {
            finish();
        });
        sj.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(Adapter_LJYY_Dialog.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    new TimePickerDialog(Adapter_LJYY_Dialog.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            runOnUiThread(() -> {
                                sj.setText(year + "-" + month + "-" + dayOfMonth + " " + hourOfDay + ":" + minute + ":00");
                            });
                        }
                    }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        new

                Httputil()
                .

                sendResultToken("/prod-api/api/traffic/checkCar/place/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_dialogs = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_Dialog>>() {
                            }.getType());
                            for (S_Dialog s_dialog : s_dialogs) {
                                DD.add(s_dialog.getPlaceName());
                            }
                            adapter = new ArrayAdapter<String>(Adapter_LJYY_Dialog.this, android.R.layout.simple_spinner_item, DD);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            runOnUiThread(() -> {
                                dd.setAdapter(adapter);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });
        new

                Httputil()
                .

                sendResultToken("/prod-api/api/traffic/checkCar/place/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_dialogs = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_Dialog>>() {
                            }.getType());
                            runOnUiThread(() -> {
                                yy.setOnClickListener(v -> {
                                    for (S_Dialog s_dialog : s_dialogs) {
                                        if (dd.getSelectedItem().equals(s_dialog.getPlaceName())) {
                                            JSONObject jsonObject1 = new JSONObject();
                                            try {
                                                jsonObject1.put("userId", "1117214");
                                                jsonObject1.put("carId", id);
                                                jsonObject1.put("aptTime", sj.getText().toString());
                                                jsonObject1.put("addressId", s_dialog.getId());
                                                new Httputil()
                                                        .sendResultToken("/prod-api/api/traffic/checkCar/apply", jsonObject1.toString(), "POST", new Callback() {
                                                            @Override
                                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                                            }

                                                            @Override
                                                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                                String s = response.body().string();
                                                                try {
                                                                    JSONObject jsonObject2 = new JSONObject(s);
                                                                    if (jsonObject2.getString("code").equals("200")) {
                                                                        startActivity(new Intent(Adapter_LJYY_Dialog.this, WDYY.class));
                                                                    }
                                                                } catch (JSONException e) {
                                                                    throw new RuntimeException(e);
                                                                }

                                                            }
                                                        });
                                            } catch (JSONException e) {
                                                throw new RuntimeException(e);
                                            }
                                            break;
                                        }
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
        sj = (TextView) findViewById(R.id.sj);
        dd = (Spinner) findViewById(R.id.dd);
        yy = (TextView) findViewById(R.id.vue);
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
    }
}
