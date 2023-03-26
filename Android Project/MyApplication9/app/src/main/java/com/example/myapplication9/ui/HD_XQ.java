package com.example.myapplication9.ui;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.myapplication9.R;
import com.example.myapplication9.adapter.Adapter_A_LV;
import com.example.myapplication9.adapter.Adapter_HD_LV;
import com.example.myapplication9.adapter.Adapter_HD_XQ_LV1;
import com.example.myapplication9.adapter.Adapter_HD_XQ_LV2;
import com.example.myapplication9.bean.S_HD_LV;
import com.example.myapplication9.bean.S_HD_XQ_LV1;
import com.example.myapplication9.bean.S_HD_XQ_LV2;
import com.example.myapplication9.util.HttpUtil;
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

public class HD_XQ extends AppCompatActivity {
    private ArrayList<S_HD_XQ_LV1> s_hd_xq_lv1ArrayList = new ArrayList<>();
    private Adapter_HD_XQ_LV1 adapter_hd_xq_lv1;
    private ArrayList<S_HD_XQ_LV2> s_hd_xq_lv2ArrayList = new ArrayList<>();
    private Adapter_HD_XQ_LV2 adapter_hd_xq_lv2;
    private TextView back;
    private TextView header;
    private ImageView image;
    private TextView nr;
    private TextView bm;
    private ListView listview1;
    private ListView listview2;
    private EditText sr;
    private TextView pl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hd_xq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        back.setOnClickListener(v -> {
            finish();
        });
        pl.setOnClickListener(v -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("id", id);
                jsonObject.put("userid", "2");
                jsonObject.put("commitTime", simpleDateFormat.format(date));
                jsonObject.put("commitContent", sr.getText().toString());
                new HttpUtil()
                        .sendResUtil("publicActionCommit", jsonObject.toString(), "POST", new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                String s = response.body().string();
                                JSONObject jsonObject1 = null;
                                try {
                                    jsonObject1 = new JSONObject(s);
                                    if (Objects.equals(jsonObject1.getString("RESULT"), "S")) {
                                        runOnUiThread(() -> {
                                            sr.setText("");
                                            Toast.makeText(HD_XQ.this, "评论成功", Toast.LENGTH_SHORT).show();
                                            JSONObject jsonObject15 = new JSONObject();
                                            try {
                                                jsonObject15.put("id", id);
                                                new HttpUtil()
                                                        .sendResUtil("getActionCommitById", jsonObject15.toString(), "POST", new Callback() {
                                                            @Override
                                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                                            }

                                                            @Override
                                                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                                String s = response.body().string();
                                                                try {
                                                                    JSONObject jsonObject2 = new JSONObject(s);
                                                                    s_hd_xq_lv1ArrayList = new Gson().fromJson(jsonObject2.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_HD_XQ_LV1>>() {
                                                                    }.getType());
                                                                    adapter_hd_xq_lv1 = new Adapter_HD_XQ_LV1(HD_XQ.this, s_hd_xq_lv1ArrayList);
                                                                    runOnUiThread(() -> {
                                                                        listview1.setAdapter(adapter_hd_xq_lv1);
                                                                        ListAdapter listAdapter = listview1.getAdapter();
                                                                        if (listAdapter == null) {
                                                                            return;
                                                                        }
                                                                        int h = 0;
                                                                        for (int i = 0; i < s_hd_xq_lv1ArrayList.size(); i++) {
                                                                            View item = listAdapter.getView(i, null, listview1);
                                                                            item.measure(1, 1);
                                                                            h += item.getMeasuredHeight();
                                                                        }
                                                                        ViewGroup.LayoutParams params = listview1.getLayoutParams();
                                                                        params.height = h + listview1.getDividerHeight() * (listAdapter.getCount() - 1);
                                                                        listview1.setLayoutParams(params);
                                                                    });
                                                                } catch (JSONException e) {
                                                                    throw new RuntimeException(e);
                                                                }

                                                            }
                                                        });
                                            } catch (JSONException e) {
                                                throw new RuntimeException(e);
                                            }
                                        });
                                    } else {
                                        runOnUiThread(() -> {
                                            Toast.makeText(HD_XQ.this, "评论失败", Toast.LENGTH_SHORT).show();
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
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            new HttpUtil()
                    .sendResUtil("getActionById", jsonObject.toString(), "POST", new Callback() {
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
                                        Glide.with(HD_XQ.this)
                                                .load(jsonArray.getJSONObject(0).getString("image"))
                                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                                .into(image);
                                        header.setText(jsonArray.getJSONObject(0).getString("name"));
                                        nr.setText(jsonArray.getJSONObject(0).getString("description"));
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
        bm.setOnClickListener(v -> {
            JSONObject jsonObject1 = new JSONObject();
            try {
                jsonObject1.put("id", id);
                jsonObject1.put("userid", "2");
                new HttpUtil()
                        .sendResUtil("signUpAction", jsonObject1.toString(), "POST", new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                String s = response.body().string();
                                try {
                                    JSONObject jsonObject2 = new JSONObject(s);
                                    if (Objects.equals("S", jsonObject2.getString("RESULT"))) {
                                        runOnUiThread(() -> {
                                            JSONObject jsonObject3 = new JSONObject();
                                            try {
                                                jsonObject3.put("id", id);
                                                new HttpUtil()
                                                        .sendResUtil("setActionSignUpCount", jsonObject3.toString(), "POST", new Callback() {
                                                            @Override
                                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                                            }

                                                            @Override
                                                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                                String s = response.body().string();
                                                                try {
                                                                    JSONObject jsonObject4 = new JSONObject(s);
                                                                    if (Objects.equals(jsonObject4.getString("RESULT"), "S")) {
                                                                        runOnUiThread(() -> {
                                                                            Toast.makeText(HD_XQ.this, "提交成功", Toast.LENGTH_SHORT).show();
                                                                        });
                                                                    } else {
                                                                        runOnUiThread(() -> {
                                                                            Toast.makeText(HD_XQ.this, "提交失败", Toast.LENGTH_SHORT).show();
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
                                    } else {
                                        runOnUiThread(() -> {
                                            Toast.makeText(HD_XQ.this, "提交失败", Toast.LENGTH_SHORT).show();
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
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("id", id);
            new HttpUtil()
                    .sendResUtil("getActionCommitById", jsonObject1.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject2 = new JSONObject(s);
                                s_hd_xq_lv1ArrayList = new Gson().fromJson(jsonObject2.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_HD_XQ_LV1>>() {
                                }.getType());
                                adapter_hd_xq_lv1 = new Adapter_HD_XQ_LV1(HD_XQ.this, s_hd_xq_lv1ArrayList);
                                runOnUiThread(() -> {
                                    listview1.setAdapter(adapter_hd_xq_lv1);
                                    ListAdapter listAdapter = listview1.getAdapter();
                                    if (listAdapter == null) {
                                        return;
                                    }
                                    int h = 0;
                                    for (int i = 0; i < s_hd_xq_lv1ArrayList.size(); i++) {
                                        View item = listAdapter.getView(i, null, listview1);
                                        item.measure(1, 1);
                                        h += item.getMeasuredHeight();
                                    }
                                    ViewGroup.LayoutParams params = listview1.getLayoutParams();
                                    params.height = h + listview1.getDividerHeight() * (listAdapter.getCount() - 1);
                                    listview1.setLayoutParams(params);
                                });
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        new HttpUtil()
                .sendResUtil("getAllActions", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_hd_xq_lv2ArrayList = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_HD_XQ_LV2>>() {
                            }.getType());
                            adapter_hd_xq_lv2 = new Adapter_HD_XQ_LV2(HD_XQ.this, s_hd_xq_lv2ArrayList);
                            runOnUiThread(() -> {
                                listview2.setAdapter(adapter_hd_xq_lv2);
                                ListAdapter listAdapter = listview2.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < 2; i++) {
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

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        image = (ImageView) findViewById(R.id.image);
        nr = (TextView) findViewById(R.id.nr);
        bm = (TextView) findViewById(R.id.bm);
        listview1 = (ListView) findViewById(R.id.listview1);
        listview2 = (ListView) findViewById(R.id.listview2);
        sr = (EditText) findViewById(R.id.sr);
        pl = (TextView) findViewById(R.id.pl);
    }
}
