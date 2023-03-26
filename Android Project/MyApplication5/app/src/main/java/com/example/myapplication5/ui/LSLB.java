package com.example.myapplication5.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication5.R;
import com.example.myapplication5.adapter.Adapter_D_SSS;
import com.example.myapplication5.bean.S_D_SS;
import com.example.myapplication5.fragment.Dialog_FLZC;
import com.example.myapplication5.util.HttpUtil;
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

public class LSLB extends AppCompatActivity {
    private static ArrayList<S_D_SS> s_d_sses = new ArrayList<>();
    private static Adapter_D_SSS adapter_d_ss;
    private TextView back;
    private TextView header;
    private Spinner spinner;
    private TextView sx;
    private static ListView listview;
    static Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lslb);
        initView();
        header.setText("律师列表");
        back.setOnClickListener(v -> {
            finish();
        });
        Bundle bundle = this.getIntent().getExtras();
        int idd = bundle.getInt("id");
        System.out.println(idd);
        sx.setOnClickListener(v -> {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            Dialog_FLZC dialog_flzc = new Dialog_FLZC();
            transaction.add(dialog_flzc, "dialog-tag");
            transaction.show(dialog_flzc);
            transaction.commit();
        });
        if (idd == 0) {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String srot = "";
                    if (position == 0) {
                        srot = "";
                    } else if (position == 1) {
                        srot = "workStartAt";
                    } else if (position == 2) {
                        srot = "serviceTimes";
                    } else if (position == 3) {
                        srot = "favorableRate";
                    }
                    i2(srot);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String srot = "";
                    if (position == 0) {
                        srot = "";
                    } else if (position == 1) {
                        srot = "workStartAt";
                    } else if (position == 2) {
                        srot = "serviceTimes";
                    } else if (position == 3) {
                        srot = "favorableRate";
                    }
                    i1(srot, idd);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }


    public void i1(String srot, int id) {
        new HttpUtil()
                .sendResurltToken("/prod-api/api/lawyer-consultation/lawyer/list?sort=" + srot + "&legalExpertiseId=" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        s_d_sses.clear();
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_d_sses = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_D_SS>>() {
                            }.getType());
                            adapter_d_ss = new Adapter_D_SSS(s_d_sses);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_d_ss);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    public static void i2(String srot) {
        new HttpUtil()
                .sendResurltToken("/prod-api/api/lawyer-consultation/lawyer/list?sort=" + srot, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        s_d_sses.clear();
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_d_sses = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_D_SS>>() {
                            }.getType());
                            adapter_d_ss = new Adapter_D_SSS(s_d_sses);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listview.setAdapter(adapter_d_ss);
                                }
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    public static void i3(int id) {
        new HttpUtil()
                .sendResurltToken("/prod-api/api/lawyer-consultation/lawyer/list?sort=" , "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        s_d_sses.clear();
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_d_sses = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_D_SS>>() {
                            }.getType());
                            adapter_d_ss = new Adapter_D_SSS(s_d_sses);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listview.setAdapter(adapter_d_ss);
                                }
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
        spinner = (Spinner) findViewById(R.id.spinner);
        sx = (TextView) findViewById(R.id.sx);
        listview = (ListView) findViewById(R.id.listview);
    }
}
