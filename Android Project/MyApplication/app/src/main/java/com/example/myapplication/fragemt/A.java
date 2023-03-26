package com.example.myapplication.fragemt;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.adapter.Adapter_A_GridView;
import com.example.myapplication.adapter.Adapter_A_ListView;
import com.example.myapplication.bean.ShuJv_A_GridView;
import com.example.myapplication.bean.ShuJv_A_ListView;
import com.example.myapplication.ui.A_LBT;
import com.example.myapplication.ui.A_SS;
import com.example.myapplication.ui.HDGL;
import com.example.myapplication.ui.KDY;
import com.example.myapplication.ui.MainActivity;
import com.example.myapplication.ui.SHJF;
import com.example.myapplication.ui.ZHJG;
import com.example.myapplication.util.App;
import com.example.myapplication.util.HttpUtil;
import com.google.android.material.tabs.TabLayout;
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
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class A extends Fragment {
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<ShuJv_A_ListView> shuJv_a_listViews = new ArrayList<>();
    private Adapter_A_ListView adapter_a_listView;
    private ArrayList<ShuJv_A_GridView> shuJv_a_gridViews = new ArrayList<>();
    private Adapter_A_GridView adapter_a_gridView;
    private TextView header;
    private EditText sr;
    private Banner banner;
    private GridView gridview;
    private LinearLayout line1;
    private ImageView img1;
    private TextView text1;
    private LinearLayout line2;
    private ImageView img2;
    private TextView text2;
    private TabLayout tablayout;
    private ListView listview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.a, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        header.setText("智慧城市");
        //搜索
        SS();
        //轮播图
        LBT();
        //GridView
        GV();
        //Tablayout
        T();
        //ListView
        LV("");
        //热门主题
        RMZT();
    }

    private void RMZT() {
        new HttpUtil()
                .sendResyltToken("/prod-api/press/press/list?hot=Y", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("rows");
                            getActivity().runOnUiThread(() -> {
                                try {
                                    text1.setText(jsonArray.getJSONObject(0).getString("title"));
                                    Glide.with(getActivity())
                                            .load(App.url + jsonArray.getJSONObject(0).getString("cover"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(img1);
                                    line1.setOnClickListener(v -> {
                                        try {
                                            Intent intent = new Intent(getActivity(), A_LBT.class);
                                            intent.putExtra("image", App.url + jsonArray.getJSONObject(0).getString("cover"));
                                            startActivity(intent);
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });
                                    text2.setText(jsonArray.getJSONObject(1).getString("title"));
                                    Glide.with(getActivity())
                                            .load(App.url + jsonArray.getJSONObject(1).getString("cover"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(img2);
                                    line2.setOnClickListener(v -> {
                                        try {
                                            Intent intent = new Intent(getActivity(), A_LBT.class);
                                            intent.putExtra("image", App.url + jsonArray.getJSONObject(1).getString("cover"));
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
    }

    private void SS() {
        sr.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Intent intent = new Intent(getActivity(), A_SS.class);
                    intent.putExtra("name", sr.getText().toString());
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    private void LBT() {
        new HttpUtil()
                .sendResyltToken("/prod-api/api/rotation/list", "", "GET", new Callback() {
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
                            getActivity().runOnUiThread(() -> {

                                banner.setAdapter(new BannerImageAdapter<String>(img) {
                                    @Override
                                    public void onBindView(BannerImageHolder bannerImageHolder, String s, int i, int i1) {
                                        getActivity().runOnUiThread(() -> {
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
                                            bannerImageHolder.imageView.setOnClickListener(v -> {
                                                Intent intent = new Intent(getActivity(), A_LBT.class);
                                                intent.putExtra("image", s);
                                                startActivity(intent);
                                            });
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

    private void GV() {
        new HttpUtil()
                .sendResyltToken("/prod-api/api/service/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_a_gridViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_A_GridView>>() {
                            }.getType());
                            shuJv_a_gridViews.sort(new Comparator<ShuJv_A_GridView>() {
                                @Override
                                public int compare(ShuJv_A_GridView o1, ShuJv_A_GridView o2) {
                                    return o1.getId() - o2.getId();
                                }
                            });
                            adapter_a_gridView = new Adapter_A_GridView(getActivity(), shuJv_a_gridViews);
                            getActivity().runOnUiThread(() -> {
                                gridview.setAdapter(adapter_a_gridView);
                                gridview.setOnItemClickListener((parent, view, position, id) -> {
                                    switch (position) {
                                        case 3:
                                            startActivity(new Intent(getActivity(), SHJF.class));
                                            break;
                                        case 4:
                                            startActivity(new Intent(getActivity(), ZHJG.class));
                                            break;
                                        case 6:
                                            startActivity(new Intent(getActivity(), KDY.class));
                                            break;
                                        case 9:
                                            MainActivity.TZ_B();
                                            break;
                                        case 11:
                                            startActivity(new Intent(getActivity(), HDGL.class));
                                            break;
                                    }
                                });
                                ListAdapter listAdapter = gridview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < 2; i++) {
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

    }

    private void T() {
        new HttpUtil()
                .sendResyltToken("/prod-api/press/category/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            getActivity().runOnUiThread(() -> {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    try {
                                        tablayout.addTab(tablayout.newTab().setText(jsonArray.getJSONObject(i).getString("name")));
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                    tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                        @Override
                                        public void onTabSelected(TabLayout.Tab tab) {
                                            switch (tab.getText().toString()) {
                                                case "今日要闻":
                                                    LV("9");
                                                    break;
                                                case "专题聚焦":
                                                    LV("17");
                                                    break;
                                                case "政策解读":
                                                    LV("19");
                                                    break;
                                                case "经济发展":
                                                    LV("20");
                                                    break;
                                                case "文化旅游":
                                                    LV("21");
                                                    break;
                                                case "科技创新":
                                                    LV("22");
                                                    break;
                                            }
                                        }

                                        @Override
                                        public void onTabUnselected(TabLayout.Tab tab) {

                                        }

                                        @Override
                                        public void onTabReselected(TabLayout.Tab tab) {

                                        }
                                    });

                                }
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });

    }

    private void LV(String id) {
        new HttpUtil()
                .sendResyltToken("/prod-api/press/press/list?type=" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_a_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_A_ListView>>() {
                            }.getType());
                            adapter_a_listView = new Adapter_A_ListView(getActivity(), shuJv_a_listViews);
                            getActivity().runOnUiThread(() -> {
                                listview.setAdapter(adapter_a_listView);
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < shuJv_a_listViews.size(); i++) {
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
        header = (TextView) getView().findViewById(R.id.header);
        sr = (EditText) getView().findViewById(R.id.sr);
        banner = (Banner) getView().findViewById(R.id.banner);
        gridview = (GridView) getView().findViewById(R.id.gridview);
        line1 = (LinearLayout) getView().findViewById(R.id.line1);
        img1 = (ImageView) getView().findViewById(R.id.img1);
        text1 = (TextView) getView().findViewById(R.id.text1);
        line2 = (LinearLayout) getView().findViewById(R.id.line2);
        img2 = (ImageView) getView().findViewById(R.id.img2);
        text2 = (TextView) getView().findViewById(R.id.text2);
        tablayout = (TabLayout) getView().findViewById(R.id.tablayout);
        listview = (ListView) getView().findViewById(R.id.listview);
    }
}
