package com.example.myapplication7.ui;

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
import com.example.myapplication7.R;
import com.example.myapplication7.adapter.Adapter_RCZC_LV;
import com.example.myapplication7.bean.S_RCZC_LV;
import com.example.myapplication7.util.App;
import com.example.myapplication7.util.HttpUtil;
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

public class RCZC extends AppCompatActivity {
    private ArrayList<S_RCZC_LV> s_rczc_lvs = new ArrayList<>();
    private Adapter_RCZC_LV adapter_rczc_lv;
    private TextView back;
    private TextView header;
    private ImageView image;
    private TextView jj;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rczc);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        System.out.println("123+"+id);
        initView();
        header.setText("人才政策");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResuiltToken("/prod-api/api/youth-inn/talent-policy-area/" + id, "", "GET", new Callback() {
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
                                    Glide.with(RCZC.this)
                                            .load(App.url + jsonObject1.getString("imgUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(image);
                                    jj.setText(jsonObject1.getString("introduce"));
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
                .sendResuiltToken("/prod-api/api/youth-inn/talent-policy/list?areaId=" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_rczc_lvs = new Gson().fromJson(jsonObject.getString("data"), new TypeToken<List<S_RCZC_LV>>() {
                            }.getType());
                            adapter_rczc_lv = new Adapter_RCZC_LV(RCZC.this, s_rczc_lvs);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_rczc_lv);
                                listview.setOnItemClickListener((parent, view, position, id1) -> {
                                    Intent intent=new Intent(RCZC.this,RCZC_XQ.class);
                                    intent.putExtra("id",s_rczc_lvs.get(position).getId());
                                    startActivity(intent);
                                });
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < s_rczc_lvs.size(); i++) {
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
        image = (ImageView) findViewById(R.id.image);
        jj = (TextView) findViewById(R.id.jj);
        listview = (ListView) findViewById(R.id.listview);
    }
}
