package com.example.myapplication7.fragment;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
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
import com.example.myapplication7.R;
import com.example.myapplication7.adapter.Adapter_AA_GV;
import com.example.myapplication7.adapter.Adapter_AA_LV;
import com.example.myapplication7.bean.S_AA_GV;
import com.example.myapplication7.bean.S_AA_LV;
import com.example.myapplication7.ui.AA_SS;
import com.example.myapplication7.ui.AA_XQ;
import com.example.myapplication7.ui.Main;
import com.example.myapplication7.ui.QNYZ;
import com.example.myapplication7.ui.ZFFWRX;
import com.example.myapplication7.ui.ZFZ;
import com.example.myapplication7.util.App;
import com.example.myapplication7.util.HttpUtil;
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
import okhttp3.Response;

public class AA extends Fragment {
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<S_AA_LV> s_aa_lvArrayList = new ArrayList<>();
    private Adapter_AA_LV adapter_aa_lv;
    private ArrayList<S_AA_GV> s_aa_gvArrayList = new ArrayList<>();
    private Adapter_AA_GV adapter_aa_gv;
    private TextView header;
    private EditText ss;
    private Banner banner;
    private GridView gridview;
    private LinearLayout line0;
    private ImageView image0;
    private TextView text0;
    private LinearLayout line1;
    private ImageView image1;
    private TextView text1;
    private TabLayout tablayout;
    private ListView listview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.aa, null);
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
        //gridview
        GV();
        //热门主题
        RMZT();
        //tablayout
        TL();
        //listview
        LV("9");
    }

    private void LV(String id) {
        new HttpUtil().sendResuiltToken("/prod-api/press/press/list?type=" + id, "", "GET", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    s_aa_lvArrayList = new Gson().fromJson(jsonObject.getString("rows"), new TypeToken<List<S_AA_LV>>() {
                    }.getType());
                    adapter_aa_lv = new Adapter_AA_LV(getActivity(), s_aa_lvArrayList);
                    getActivity().runOnUiThread(() -> {
                        listview.setAdapter(adapter_aa_lv);
                        ListAdapter listAdapter = listview.getAdapter();
                        if (listAdapter == null) {
                            return;
                        }
                        int h = 0;
                        for (int i = 0; i < s_aa_lvArrayList.size(); i++) {
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

    private void TL() {
        new HttpUtil().sendResuiltToken("/prod-api/press/category/list", "", "GET", new Callback() {
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
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }

    private void RMZT() {
        new HttpUtil().sendResuiltToken("/prod-api/press/press/list?hot=y", "", "GET", new Callback() {
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
                            Glide.with(getActivity()).load(App.url + jsonArray.getJSONObject(0).getString("cover")).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(image0);
                            Glide.with(getActivity()).load(App.url + jsonArray.getJSONObject(1).getString("cover")).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(image1);
                            text0.setText(jsonArray.getJSONObject(0).getString("title"));
                            text1.setText(jsonArray.getJSONObject(1).getString("title"));
                            line0.setOnClickListener(v -> {
                                try {
                                    Intent intent = new Intent(getActivity(), AA_XQ.class);
                                    intent.putExtra("img", App.url + jsonArray.getJSONObject(0).getString("cover"));
                                    intent.putExtra("name", "热门主题");
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                            line1.setOnClickListener(v -> {
                                try {
                                    Intent intent = new Intent(getActivity(), AA_XQ.class);
                                    intent.putExtra("img", App.url + jsonArray.getJSONObject(1).getString(""));
                                    intent.putExtra("name", "热门主题");
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

    private void GV() {
        new HttpUtil().sendResuiltToken("/prod-api/api/service/list", "", "GET", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    s_aa_gvArrayList = new Gson().fromJson(jsonObject.getString("rows"), new TypeToken<List<S_AA_GV>>() {
                    }.getType());
                    s_aa_gvArrayList.sort(new Comparator<S_AA_GV>() {
                        @Override
                        public int compare(S_AA_GV o1, S_AA_GV o2) {
                            return o2.getId() - o1.getId();
                        }
                    });
                    adapter_aa_gv = new Adapter_AA_GV(getActivity(), s_aa_gvArrayList);
                    getActivity().runOnUiThread(() -> {
                        gridview.setAdapter(adapter_aa_gv);
                        gridview.setOnItemClickListener((parent, view, position, id) -> {
                            switch (position) {
                                case 0:
                                    startActivity(new Intent(getActivity(), ZFFWRX.class));
                                    break;
                                case 3:
                                    startActivity(new Intent(getActivity(), QNYZ.class));
                                    break;
                                case 9:
                                    Main.TZ_BB();
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
                        params.height = h + gridview.getMeasuredHeight() * (listAdapter.getCount() - 1);
                        gridview.setLayoutParams(params);
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void LBT() {
        new HttpUtil().sendResuiltToken("/prod-api/api/rotation/list", "", "GET", new Callback() {
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
                    System.out.println(img);
                    getActivity().runOnUiThread(() -> {
                        banner.setAdapter(new BannerImageAdapter<String>(img) {
                            @Override
                            public void onBindView(BannerImageHolder bannerImageHolder, String s, int i, int i1) {
                                Glide.with(bannerImageHolder.imageView).load(s).apply(RequestOptions.bitmapTransform(new RoundedCorners(30))).into(bannerImageHolder.imageView);
                                bannerImageHolder.imageView.setOnClickListener(v -> {
                                    Intent intent = new Intent(getActivity(), AA_XQ.class);
                                    intent.putExtra("img", s);
                                    intent.putExtra("name", "轮播图");
                                    startActivity(intent);
                                });
                                bannerImageHolder.imageView.setOutlineProvider(new ViewOutlineProvider() {
                                    @Override
                                    public void getOutline(View view, Outline outline) {
                                        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 20);
                                    }
                                });
                                bannerImageHolder.imageView.setClipToOutline(true);
                            }
                        });
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void SS() {
        ss.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Intent intent = new Intent(getActivity(), AA_SS.class);
                intent.putExtra("title", ss.getText().toString());
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    private void initView() {
        header = (TextView) getView().findViewById(R.id.header);
        ss = (EditText) getView().findViewById(R.id.ss);
        banner = (Banner) getView().findViewById(R.id.banner);
        gridview = (GridView) getView().findViewById(R.id.gridview);
        line0 = (LinearLayout) getView().findViewById(R.id.line0);
        image0 = (ImageView) getView().findViewById(R.id.image0);
        text0 = (TextView) getView().findViewById(R.id.text0);
        line1 = (LinearLayout) getView().findViewById(R.id.line1);
        image1 = (ImageView) getView().findViewById(R.id.image1);
        text1 = (TextView) getView().findViewById(R.id.text1);
        tablayout = (TabLayout) getView().findViewById(R.id.tablayout);
        listview = (ListView) getView().findViewById(R.id.listview);
    }
}
