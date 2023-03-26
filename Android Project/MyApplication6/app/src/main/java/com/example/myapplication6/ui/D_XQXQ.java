package com.example.myapplication6.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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
import com.example.myapplication6.R;
import com.example.myapplication6.adapter.Adapter_D_LV;
import com.example.myapplication6.adapter.Adapter_D_XWXQ_LV;
import com.example.myapplication6.adapter.Adapter_D_XWXQ_LV1;
import com.example.myapplication6.bean.S_A_LV;
import com.example.myapplication6.bean.S_D_XWXQ_LV;
import com.example.myapplication6.util.App;
import com.example.myapplication6.util.HttpUtil;
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

public class D_XQXQ extends AppCompatActivity {
    private ArrayList<S_A_LV> s_a_lvArrayList = new ArrayList<>();
    private Adapter_D_XWXQ_LV1 adapter_a_lv;
    private ArrayList<S_D_XWXQ_LV> s_d_xwxq_lvs = new ArrayList<>();
    private Adapter_D_XWXQ_LV adapter_d_xwxq_lv;
    private TextView back;
    private TextView header;
    private TextView nr;
    private ListView listview1;
    private ListView listview2;
    private EditText plsr;
    private TextView plan;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_xwxq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResurltToken("/prod-api/press/press/list?top=y", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_a_lvArrayList = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_A_LV>>() {
                            }.getType());
                            s_a_lvArrayList.sort(new Comparator<S_A_LV>() {
                                @Override
                                public int compare(S_A_LV o1, S_A_LV o2) {
                                    return o1.getCreateTime().compareTo(o2.getCreateTime());
                                }
                            });
                            adapter_a_lv = new Adapter_D_XWXQ_LV1(D_XQXQ.this, s_a_lvArrayList);
                         runOnUiThread(() -> {
                                listview2.setAdapter(adapter_a_lv);
                                ListAdapter listAdapter = listview2.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < s_a_lvArrayList.size(); i++) {
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
        new HttpUtil().sendResurltToken("/prod-api/press/press/" + id, "", "GET", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    runOnUiThread(() -> {
                        try {
                            header.setText(jsonObject1.getString("title"));
                            Glide.with(D_XQXQ.this)
                                    .load(App.url + jsonObject1.getString("cover"))
                                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                    .into(image);
                            nr.setText(Html.fromHtml(jsonObject1.getString("content")));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        new HttpUtil().sendResurltToken("/prod-api/press/comments/list?newsId=" + id, "", "GET", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    s_d_xwxq_lvs = new Gson().fromJson(jsonObject.getString("rows"), new TypeToken<List<S_D_XWXQ_LV>>() {
                    }.getType());
                    adapter_d_xwxq_lv = new Adapter_D_XWXQ_LV(D_XQXQ.this, s_d_xwxq_lvs);
                    runOnUiThread(() -> {
                        listview1.setAdapter(adapter_d_xwxq_lv);
                        ListAdapter listAdapter = listview1.getAdapter();
                        if (listAdapter == null) {
                            return;
                        }
                        int h = 0;
                        for (int i = 0; i < s_d_xwxq_lvs.size(); i++) {
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
        plan.setOnClickListener(v -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("newsId", id);
                jsonObject.put("content", plsr.getText().toString());
                new HttpUtil()
                        .sendResurltToken("/prod-api/press/pressComment", jsonObject.toString(), "POST", new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                String s = response.body().string();
                                try {
                                    JSONObject jsonObject1 = new JSONObject(s);
                                    if (jsonObject1.getString("code").equals("200")) {
                                        runOnUiThread(() -> {
                                            Toast.makeText(D_XQXQ.this, "评论成功", Toast.LENGTH_SHORT).show();
                                            new HttpUtil().sendResurltToken("/prod-api/press/comments/list?newsId=" + id, "", "GET", new Callback() {
                                                @Override
                                                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                                }

                                                @Override
                                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                    String s = response.body().string();
                                                    try {
                                                        JSONObject jsonObject = new JSONObject(s);
                                                        s_d_xwxq_lvs = new Gson().fromJson(jsonObject.getString("rows"), new TypeToken<List<S_D_XWXQ_LV>>() {
                                                        }.getType());
                                                        adapter_d_xwxq_lv = new Adapter_D_XWXQ_LV(D_XQXQ.this, s_d_xwxq_lvs);
                                                        runOnUiThread(() -> {
                                                            listview1.setAdapter(adapter_d_xwxq_lv);
                                                            ListAdapter listAdapter = listview1.getAdapter();
                                                            if (listAdapter == null) {
                                                                return;
                                                            }
                                                            int h = 0;
                                                            for (int i = 0; i < s_d_xwxq_lvs.size(); i++) {
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
                                        });
                                    } else {
                                        runOnUiThread(() -> {
                                            try {
                                                Toast.makeText(D_XQXQ.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
                                            } catch (JSONException e) {
                                                throw new RuntimeException(e);
                                            }
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

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        nr = (TextView) findViewById(R.id.nr);
        listview1 = (ListView) findViewById(R.id.listview1);
        listview2 = (ListView) findViewById(R.id.listview2);
        plsr = (EditText) findViewById(R.id.plsr);
        plan = (TextView) findViewById(R.id.plan);
        image = (ImageView) findViewById(R.id.image);
    }
}
