package com.example.test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.adapter.CWYY_GridView;
import com.example.test.adapter.CWYY_ListView;
import com.example.test.adapter.CWYY_ListView1;
import com.example.test.bean.ShuJv_CWYY_GridView;
import com.example.test.bean.ShuJv_CWYY_ListView;
import com.example.test.bean.ShuJv_CWYY_ListView1;
import com.example.test.util.HttpUtil;
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

public class CWYY extends AppCompatActivity {
    private ArrayList<ShuJv_CWYY_GridView> shuJv_cwyy_gridViews = new ArrayList<>();
    private CWYY_GridView cwyy_gridView;
    private ArrayList<ShuJv_CWYY_ListView> shuJv_cwyy_listViews = new ArrayList<>();
    private CWYY_ListView cwyy_listView;
    private ArrayList<ShuJv_CWYY_ListView1> shuJv_cwyy_listView1s = new ArrayList<>();
    private ArrayList<ShuJv_CWYY_ListView1> shuJv_cwyy_listView1s1 = new ArrayList<>();
    private CWYY_ListView1 cwyy_listView1;
    private ImageView back;
    private TextView header;
    private GridView gridview;
    private ListView listview;
    private ListView listview1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cwyy);
        initView();
        header.setText("宠物医院");
        back.setOnClickListener(view -> {
            finish();
        });
        new HttpUtil()
                .sendResulToken("/prod-api/api/pet-hospital/pet-type/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_cwyy_gridViews = new Gson().fromJson(jsonObject.getJSONArray("data").toString(), new TypeToken<List<ShuJv_CWYY_GridView>>() {
                            }.getType());
                            cwyy_gridView = new CWYY_GridView(CWYY.this, shuJv_cwyy_gridViews);
                            runOnUiThread(() -> {
                                gridview.setAdapter(cwyy_gridView);
                                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        startActivity(new Intent(CWYY.this, ZYS.class));
                                    }
                                });
                                ListAdapter listAdapter = gridview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < shuJv_cwyy_gridViews.size() / 5; i++) {
                                    View item = listAdapter.getView(i, null, gridview);
                                    item.measure(1, 1);
                                    h += item.getMeasuredHeight();
                                }
                                ViewGroup.LayoutParams params = gridview.getLayoutParams();
                                params.height = h + (gridview.getMeasuredHeight() * (listAdapter.getCount() - 1));
                                gridview.setLayoutParams(params);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        new HttpUtil()
                .sendResulToken("/prod-api/api/pet-hospital/inquiry/my-list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_cwyy_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_CWYY_ListView>>() {
                            }.getType());
                            cwyy_listView = new CWYY_ListView(CWYY.this, shuJv_cwyy_listViews);
                            runOnUiThread(() -> {
                                listview.setAdapter(cwyy_listView);
                                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(CWYY.this, WZXQ.class);
                                        intent.putExtra("id", shuJv_cwyy_listViews.get(position).getId());
                                        intent.putExtra("i", shuJv_cwyy_listViews.get(position).getDoctor().getId());
                                        startActivity(intent);
                                    }
                                });
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < shuJv_cwyy_listViews.size(); i++) {
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
        new HttpUtil()
                .sendResulToken("/prod-api/api/pet-hospital/inquiry/list?pageNum=1&pageSize=10", "", "GET", new Callback() {
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
                                if (shuJv_cwyy_listView1.getDoctor() != null) {
                                    shuJv_cwyy_listView1s1.add(shuJv_cwyy_listView1);
                                }
                            }
                            cwyy_listView1 = new CWYY_ListView1(CWYY.this, shuJv_cwyy_listView1s1);
                            runOnUiThread(() -> {
                                listview1.setAdapter(cwyy_listView1);
                                listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(CWYY.this, WZ.class);
                                        intent.putExtra("name", shuJv_cwyy_listView1s1.get(position).getDoctor().getName());
                                        startActivity(intent);
                                    }
                                });
                                ListAdapter listAdapter = listview1.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < shuJv_cwyy_listView1s1.size(); i++) {
                                    View item = listAdapter.getView(i, null, listview1);
                                    item.measure(1, 1);
                                    h += item.getMeasuredHeight();
                                }
                                ViewGroup.LayoutParams params = listview1.getLayoutParams();
                                params.height = h + (listview1.getMeasuredHeight() * (listAdapter.getCount() - 1));
                                listview1.setLayoutParams(params);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        gridview = (GridView) findViewById(R.id.gridview);
        listview = (ListView) findViewById(R.id.listview);
        listview1 = (ListView) findViewById(R.id.listview1);
    }
}
