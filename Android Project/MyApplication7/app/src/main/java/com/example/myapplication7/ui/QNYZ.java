package com.example.myapplication7.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication7.R;
import com.example.myapplication7.adapter.Adapter_QNYZ_ELV;
import com.example.myapplication7.bean.S_QNYZ_ELV;
import com.example.myapplication7.util.App;
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

public class QNYZ extends AppCompatActivity {
    private ArrayList<S_QNYZ_ELV> s_qnyz_elvs = new ArrayList<>();
    private Adapter_QNYZ_ELV adapter_qnyz_elv;
    private TextView back;
    private TextView header;
    private ImageView image;
    private TextView js;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qnyz);
        initView();
        header.setText("青年驿站");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResuiltToken("/prod-api/api/youth-inn/youth-inn/list?pageNum=1&pageSize=1", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("rows");
                            runOnUiThread(() -> {
                                try {
                                    Glide.with(QNYZ.this)
                                            .load(App.url + jsonArray.getJSONObject(0).getString("coverImgUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(image);
                                    js.setText(jsonArray.getJSONObject(0).getString("introduce"));
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        new HttpUtil()
                .sendResuiltToken("/prod-api/api/youth-inn/talent-policy-area/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            runOnUiThread(() -> {
                                try {
                                    Glide.with(QNYZ.this)
                                            .load(App.url + jsonArray.getJSONObject(0).getString("imgUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(image1);
                                    Glide.with(QNYZ.this)
                                            .load(App.url + jsonArray.getJSONObject(1).getString("imgUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(image2);
                                    Glide.with(QNYZ.this)
                                            .load(App.url + jsonArray.getJSONObject(2).getString("imgUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(image3);
                                    image1.setOnClickListener(v -> {
                                        try {
                                            Intent intent = new Intent(QNYZ.this,RCZC.class);
                                            intent.putExtra("id", jsonArray.getJSONObject(0).getInt("id"));
                                            startActivity(intent);
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });
                                    image2.setOnClickListener(v -> {
                                        try {
                                            Intent intent = new Intent(QNYZ.this,RCZC.class);
                                            intent.putExtra("id", jsonArray.getJSONObject(1).getInt("id"));
                                            startActivity(intent);
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });
                                    image3.setOnClickListener(v -> {
                                        try {
                                            Intent intent = new Intent(QNYZ.this,RCZC.class);
                                            intent.putExtra("id", jsonArray.getJSONObject(2).getInt("id"));
                                            startActivity(intent);
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        new HttpUtil()
                .sendResuiltToken("/prod-api/api/youth-inn/youth-inn/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_qnyz_elvs = new Gson().fromJson(jsonObject.getString("rows"), new TypeToken<List<S_QNYZ_ELV>>() {
                            }.getType());
                            adapter_qnyz_elv = new Adapter_QNYZ_ELV(s_qnyz_elvs);
                            runOnUiThread(() -> {
                                expandableListView.setAdapter(adapter_qnyz_elv);
                                ListAdapter listAdapter = expandableListView.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < s_qnyz_elvs.size(); i++) {
                                    View item = listAdapter.getView(i, null, expandableListView);
                                    item.measure(1, 1);
                                    h += item.getMeasuredHeight();
                                }
                                ViewGroup.LayoutParams params = expandableListView.getLayoutParams();
                                params.height = h + expandableListView.getDividerHeight() * (listAdapter.getCount() - 1);
                                expandableListView.setLayoutParams(params);
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
        js = (TextView) findViewById(R.id.js);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
    }
}
