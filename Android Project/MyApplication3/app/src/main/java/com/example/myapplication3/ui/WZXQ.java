package com.example.myapplication3.ui;

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
import com.example.myapplication3.R;
import com.example.myapplication3.adapter.WZXQ_ListView;
import com.example.myapplication3.bean.ShuJv_CWYY_ListView1;
import com.example.myapplication3.bean.ShuJv_WZXQ_ListView;
import com.example.myapplication3.util.App;
import com.example.myapplication3.util.HttpUtil;
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

public class WZXQ extends AppCompatActivity {
    private ArrayList<ShuJv_CWYY_ListView1> shuJv_cwyy_listView1s = new ArrayList<>();
    private ArrayList<ShuJv_WZXQ_ListView> shuJv_wzxq_listViews = new ArrayList<>();
    private WZXQ_ListView w;
    private ImageView img;
    private TextView xm;
    private TextView zc;
    private TextView xh;
    private TextView ms;
    private ListView listview;
    private EditText sr;
    private TextView zw;
    private ImageView back;
    private TextView header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wzxq);
        Bundle bundle = this.getIntent().getExtras();
        int i = bundle.getInt("i");
        int id = bundle.getInt("id");
        initView();
        header.setText("问诊详情");
        back.setOnClickListener(v -> {
            finish();
        });
        //显示
        XS(i);
        //追问
        ZW(id);
        //显示listview
        XSLV(id);
    }

    private void XSLV(int id) {
        shuJv_wzxq_listViews.clear();
        new HttpUtil()
                .sendResltToken("/prod-api/api/pet-hospital/inquiry-message/list?inquiryId=" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_wzxq_listViews.clear();
                            shuJv_wzxq_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_WZXQ_ListView>>() {
                            }.getType());
                            w = new WZXQ_ListView(WZXQ.this, shuJv_wzxq_listViews);
                            runOnUiThread(() -> {
                                listview.setAdapter(w);
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < shuJv_wzxq_listViews.size(); i++) {
                                    View item = listAdapter.getView(i, null, listview);
                                    item.measure(1, 1);
                                    h += item.getMeasuredHeight();
                                }
                                ViewGroup.LayoutParams params = listview.getLayoutParams();
                                params.height = h + (listview.getMeasuredHeight() * (listAdapter.getCount() - 1));
                                listview.setLayoutParams(params);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void ZW(int i) {
        zw.setOnClickListener(v -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("inquiryId", i);
                jsonObject.put("content", sr.getText().toString());
                new HttpUtil()
                        .sendResltToken("/prod-api/api/pet-hospital/inquiry-message", jsonObject.toString(), "POST", new Callback() {
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
                                            Toast.makeText(WZXQ.this, "追问成功", Toast.LENGTH_SHORT).show();
                                            XSLV(i);
                                        });
                                    } else {
                                        runOnUiThread(() -> {
                                            Toast.makeText(WZXQ.this, "追问成功", Toast.LENGTH_SHORT).show();
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

    private void XS(int id) {

        new HttpUtil()
                .sendResltToken("/prod-api/api/pet-hospital/inquiry/my-list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_cwyy_listView1s = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_CWYY_ListView1>>() {
                            }.getType());
                            for (ShuJv_CWYY_ListView1 shuJv_cwyy_listView1 : shuJv_cwyy_listView1s) {
                                if (shuJv_cwyy_listView1.getId() == id) {
                                    runOnUiThread(() -> {
                                        Glide.with(WZXQ.this)
                                                .load(App.url + shuJv_cwyy_listView1.getDoctor().getAvatar())
                                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                                .into(img);
                                        xm.setText(shuJv_cwyy_listView1.getDoctor().getName());
                                        zc.setText(shuJv_cwyy_listView1.getDoctor().getJobName());
                                        xh.setText(shuJv_cwyy_listView1.getDoctor().getPracticeNo());
                                        ms.setText(shuJv_cwyy_listView1.getQuestion());
                                    });
                                }
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        xm = (TextView) findViewById(R.id.xm);
        zc = (TextView) findViewById(R.id.zc);
        xh = (TextView) findViewById(R.id.xh);
        ms = (TextView) findViewById(R.id.ms);
        listview = (ListView) findViewById(R.id.listview);
        sr = (EditText) findViewById(R.id.sr);
        zw = (TextView) findViewById(R.id.zw);
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
    }
}
