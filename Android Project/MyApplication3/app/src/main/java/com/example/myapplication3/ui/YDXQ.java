package com.example.myapplication3.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication3.R;
import com.example.myapplication3.adapter.YDXQ_ListView;
import com.example.myapplication3.bean.ShuJv_YDXQ_ListView;
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

public class YDXQ extends AppCompatActivity {
    private ArrayList<ShuJv_YDXQ_ListView> shuJv_ydxq_listViews = new ArrayList<>();
    private YDXQ_ListView ydxq_listView;
    private ImageView back;
    private TextView header;
    private ImageView img;
    private TextView name1;
    private ListView listview;
    private TextView ts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ydxq);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        initView();
        header.setText("运单详情");
        back.setOnClickListener(v -> {
            finish();
        });
        ts.setOnClickListener(v -> {
            Intent intent = new Intent(YDXQ.this, TS.class);
            intent.putExtra("name", name);
            startActivity(intent);
        });
        new HttpUtil()
                .sendResltToken("/prod-api/api/logistics-inquiry/logistics_info/" + name, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("company");
                            runOnUiThread(() -> {
                                try {
                                    name1.setText(jsonObject2.getString("name"));
                                    Glide.with(YDXQ.this)
                                            .load(App.url + jsonObject2.getString("imgUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(img);
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                            shuJv_ydxq_listViews = new Gson().fromJson(jsonObject.getJSONArray("stepList").toString(), new TypeToken<List<ShuJv_YDXQ_ListView>>() {
                            }.getType());
                            ydxq_listView = new YDXQ_ListView(YDXQ.this, shuJv_ydxq_listViews);
                            runOnUiThread(() -> {
                                listview.setAdapter(ydxq_listView);
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < shuJv_ydxq_listViews.size(); i++) {
                                    View item = listAdapter.getView(i, null, listview);
                                    item.measure(1, 1);
                                    h += item.getMeasuredHeight();
                                }
                                ViewGroup.LayoutParams params = listview.getLayoutParams();
                                params.height = h + (listview.getDividerHeight() * (listAdapter.getCount() - 1));
                                listview.setLayoutParams(params);
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
        img = (ImageView) findViewById(R.id.img);
        name1 = (TextView) findViewById(R.id.name);
        listview = (ListView) findViewById(R.id.listview);
        ts = (TextView) findViewById(R.id.ts);
    }
}
