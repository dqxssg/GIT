package com.example.myapplication8.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication8.R;
import com.example.myapplication8.adapter.Adapter_A_LV;
import com.example.myapplication8.adapter.Adapter_D_XWXQ_LV;
import com.example.myapplication8.bean.S_A_LV;
import com.example.myapplication8.bean.S_A_LV_QT;
import com.example.myapplication8.bean.S_D_XWXQ_LV;
import com.example.myapplication8.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class D_XWXQ extends AppCompatActivity {
    private ArrayList<S_A_LV_QT> s_a_lv_qtArrayList1 = new ArrayList<>();
    private ArrayList<S_A_LV_QT> s_a_lv_qtArrayList2 = new ArrayList<>();
    private ArrayList<S_A_LV> s_a_lvArrayList1 = new ArrayList<>();
    private ArrayList<S_A_LV> s_a_lvArrayList2 = new ArrayList<>();
    private Adapter_A_LV adapter_a_lv;
    private ArrayList<S_D_XWXQ_LV> s_d_xwxq_lvs = new ArrayList<>();
    private Adapter_D_XWXQ_LV adapter_d_xwxq_lv;
    private TextView back;
    private TextView header;
    private ImageView image;
    private ListView listview;
    private ListView listview1;
    private EditText sr;
    private TextView pl;
    private TextView nr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_xwxq);
        Bundle bundle = this.getIntent().getExtras();
        String id = bundle.getString("id");
        initView();
        //评论列表
        PLLB(id);
        new HttpUtil()
                .sendResuilt("getNEWsList", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_a_lvArrayList1 = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_A_LV>>() {
                            }.getType());
                            for (S_A_LV s_a_lv : s_a_lvArrayList1) {
                                if (Objects.equals(s_a_lv.getNewsType(), "时政")) {
                                    s_a_lvArrayList2.add(s_a_lv);
                                }
                            }
                            for (S_A_LV s_a_lv : s_a_lvArrayList2) {
                                try {
                                    jsonObject.put("newsid", s_a_lv.getNewsid());
                                    new HttpUtil()
                                            .sendResuilt("getNewsInfoById", jsonObject.toString(), "POST", new Callback() {
                                                @Override
                                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                                }

                                                @Override
                                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                    String s = response.body().string();
                                                    try {
                                                        JSONObject jsonObject1 = new JSONObject(s);
                                                        s_a_lv_qtArrayList1 = new Gson().fromJson(jsonObject1.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_A_LV_QT>>() {
                                                        }.getType());
                                                        for (S_A_LV_QT s_a_lv_qt : s_a_lv_qtArrayList1) {
                                                            s_a_lv_qtArrayList2.add(s_a_lv_qt);
                                                        }
                                                        if (s_a_lvArrayList2.get(s_a_lvArrayList2.size() - 1).getNewsid() == s_a_lv.getNewsid()) {
                                                            adapter_a_lv = new Adapter_A_LV(D_XWXQ.this, s_a_lvArrayList2, s_a_lv_qtArrayList2);
                                                            runOnUiThread(() -> {
                                                                listview1.setAdapter(adapter_a_lv);
                                                                listview1.setOnItemClickListener((parent, view, position, id) -> {
                                                                    Intent intent = new Intent(D_XWXQ.this, D_XWXQ.class);
                                                                    intent.putExtra("id", s_a_lvArrayList2.get(position).getNewsid());
                                                                    startActivity(intent);
                                                                });
                                                                ListAdapter listAdapter = listview1.getAdapter();
                                                                if (listAdapter == null) {
                                                                    return;
                                                                }
                                                                int h = 0;
                                                                for (int i = 0; i < 2; i++) {
                                                                    View item = listAdapter.getView(i, null, listview1);
                                                                    item.measure(1, 1);
                                                                    h += item.getMeasuredHeight();
                                                                }
                                                                ViewGroup.LayoutParams params = listview1.getLayoutParams();
                                                                params.height = h + listview1.getDividerHeight() * (listAdapter.getCount() - 1);
                                                                listview1.setLayoutParams(params);
                                                            });
                                                        }
                                                    } catch (
                                                            JSONException e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                }
                                            });
                                } catch (
                                        JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        back.setOnClickListener(v -> {
            finish();
        });
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("newsid", id);
            new HttpUtil()
                    .sendResuilt("getNEWSContent", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                JSONArray jsonArray = jsonObject1.getJSONArray("ROWS_DETAIL");
                                runOnUiThread(() -> {
                                    try {
                                        header.setText(jsonArray.getJSONObject(0).getString("title"));
                                        Glide.with(D_XWXQ.this)
                                                .load(jsonArray.getJSONObject(0).getString("picture"))
                                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                                .into(image);
                                        nr.setText(Html.fromHtml(jsonArray.getJSONObject(0).getString("abstract")));
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        pl.setOnClickListener(v -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            JSONObject jsonObject1 = new JSONObject();
            try {
                jsonObject1.put("username", "tom");
                jsonObject1.put("newsid", id);
                jsonObject1.put("commit", sr.getText().toString());
                jsonObject1.put("commitTime", simpleDateFormat.format(date));
                new HttpUtil()
                        .sendResuilt("publicComit", jsonObject1.toString(), "POST", new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                String s = response.body().string();
                                try {
                                    JSONObject jsonObject2 = new JSONObject(s);
                                    if (Objects.equals(jsonObject2.getString("RESULT"), "S")) {
                                        runOnUiThread(() -> {
                                            Toast.makeText(D_XWXQ.this, "评论成功", Toast.LENGTH_SHORT).show();
                                            sr.setText("");
                                            PLLB(id);
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
        });
    }

    private void PLLB(String id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            new HttpUtil()
                    .sendResuilt("getCommitById", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                s_d_xwxq_lvs = new Gson().fromJson(jsonObject1.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_D_XWXQ_LV>>() {
                                }.getType());
                                adapter_d_xwxq_lv = new Adapter_D_XWXQ_LV(D_XWXQ.this, s_d_xwxq_lvs);
                                runOnUiThread(() -> {
                                    listview.setAdapter(adapter_d_xwxq_lv);
                                    ListAdapter listAdapter = listview.getAdapter();
                                    if (listAdapter == null) {
                                        return;
                                    }
                                    int h = 0;
                                    for (int i = 0; i < s_d_xwxq_lvs.size(); i++) {
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
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        image = (ImageView) findViewById(R.id.image);
        listview = (ListView) findViewById(R.id.listview);
        listview1 = (ListView) findViewById(R.id.listview1);
        sr = (EditText) findViewById(R.id.sr);
        pl = (TextView) findViewById(R.id.pl);
        nr = (TextView) findViewById(R.id.nr);
    }
}
