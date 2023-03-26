package com.example.myapplication.ui;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
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
import com.example.myapplication.R;
import com.example.myapplication.adapter.Adapter_KDY_ListView;
import com.example.myapplication.bean.ShuJv_KDY_ListView;
import com.example.myapplication.util.App;
import com.example.myapplication.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class KDY extends AppCompatActivity {
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<ShuJv_KDY_ListView> shuJv_kdy_listViews = new ArrayList<>();
    private Adapter_KDY_ListView adapter_kdy_listView;
    private TextView header;
    private EditText sr;
    private Banner banner;
    private ListView listview;
    private TextView ckgd;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kdy);
        initView();
        sr.clearFocus();
        header.setText("看电影");
        back.setOnClickListener(v -> {
            finish();
        });
        //显示
        XS("", 5);
        sr.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    XS(sr.getText().toString(), 2);
                    ckgd.setVisibility(View.INVISIBLE);
                    return true;
                }
                return false;
            }
        });
        //轮播图
        LBT();
        ckgd.setOnClickListener(v -> {
            ckgd.setVisibility(View.INVISIBLE);
            XS("", shuJv_kdy_listViews.size());
        });
    }

    private void LBT() {
        new HttpUtil()
                .sendResyltToken("/prod-api/api/movie/rotation/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("rows");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                img.add(App.url + jsonArray.getJSONObject(i).getString("advImg"));
                            }
                            runOnUiThread(() -> {
                                banner.setAdapter(new BannerImageAdapter<String>(img) {
                                    @Override
                                    public void onBindView(BannerImageHolder bannerImageHolder, String s, int i, int i1) {
                                        runOnUiThread(() -> {
                                            Glide.with(bannerImageHolder.imageView)
                                                    .load(s)
                                                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                                    .into(bannerImageHolder.imageView);
                                            bannerImageHolder.imageView.setOutlineProvider(new ViewOutlineProvider() {
                                                @Override
                                                public void getOutline(View view, Outline outline) {
                                                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 20);
                                                }
                                            });
                                            bannerImageHolder.imageView.setClipToOutline(true);
                                        });
                                    }
                                });
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });
    }

    private void XS(String name, int num) {
        shuJv_kdy_listViews.clear();
        new HttpUtil()
                .sendResyltToken("/prod-api/api/movie/film/list?name=" + name, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_kdy_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_KDY_ListView>>() {
                            }.getType());
                            adapter_kdy_listView = new Adapter_KDY_ListView(KDY.this, shuJv_kdy_listViews, num);
                            runOnUiThread(() -> {
                                listview.setAdapter(adapter_kdy_listView);
                                listview.setOnItemClickListener((parent, view, position, id) -> {
                                    Intent intent = new Intent(KDY.this, KDYXQ.class);
                                    intent.putExtra("id", shuJv_kdy_listViews.get(position).getId());
                                    startActivity(intent);
                                });

                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < num - 1; i++) {
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
        header = (TextView) findViewById(R.id.header);
        sr = (EditText) findViewById(R.id.sr);
        banner = (Banner) findViewById(R.id.banner);
        listview = (ListView) findViewById(R.id.listview);
        ckgd = (TextView) findViewById(R.id.ckgd);
        back = (ImageView) findViewById(R.id.back);
    }
}
