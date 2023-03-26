package com.example.zhcs.fragement;

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
import com.example.zhcs.R;
import com.example.zhcs.adapter.Adapter_A_GV;
import com.example.zhcs.adapter.Adapter_A_LV;
import com.example.zhcs.bean.S_A_GV;
import com.example.zhcs.bean.S_A_LV;
import com.example.zhcs.ui.A_SS;
import com.example.zhcs.ui.CSDT;
import com.example.zhcs.ui.CWYY;
import com.example.zhcs.ui.FW;
import com.example.zhcs.ui.F_A_XQ;
import com.example.zhcs.ui.MainActivity;
import com.example.zhcs.ui.WLCX;
import com.example.zhcs.util.App;
import com.example.zhcs.util.HttpUtil;
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

public class F_A extends Fragment {
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<S_A_LV> s_a_lvArrayList = new ArrayList<>();
    private Adapter_A_LV adapter_a_lv;
    private ArrayList<S_A_GV> s_a_gvArrayList = new ArrayList<>();
    private Adapter_A_GV adapter_a_gv;
    private TextView header;
    private EditText ss;
    private Banner banner;
    private GridView gridview;
    private LinearLayout line1;
    private ImageView image1;
    private TextView text1;
    private LinearLayout line2;
    private ImageView image2;
    private TextView text2;
    private TabLayout tablayout;
    private ListView listview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_a, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        header.setText("智慧城市");
        //搜索
        SS();
        //banner
        B();
        //gridview
        GV();
        //热门主题
        RMZT();
        //tablayout
        TL();
        //listview
        LV("9");
    }

    private void SS() {
        ss.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Intent intent = new Intent(getActivity(), A_SS.class);
                intent.putExtra("name", ss.getText().toString());
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    private void B() {
        new HttpUtil()
                .sendResultToken("/prod-api/api/rotation/list", "", "GET", new Callback() {
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
                                            Intent intent = new Intent(getActivity(), F_A_XQ.class);
                                            intent.putExtra("s", s);
                                            intent.putExtra("name", "轮播图");
                                            startActivity(intent);
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
                .sendResultToken("/prod-api/api/service/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_a_gvArrayList = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_A_GV>>() {
                            }.getType());
                            s_a_gvArrayList.sort(new Comparator<S_A_GV>() {
                                @Override
                                public int compare(S_A_GV o1, S_A_GV o2) {
                                    return o2.getId() - o1.getId();
                                }
                            });
                            adapter_a_gv = new Adapter_A_GV(getActivity(), s_a_gvArrayList);
                            getActivity().runOnUiThread(() -> {
                                gridview.setAdapter(adapter_a_gv);
                                gridview.setOnItemClickListener((parent, view, position, id) -> {
                                    switch (position) {
                                        case 5:
                                            startActivity(new Intent(getActivity(), WLCX.class));
                                            break;
                                        case 6:
                                            startActivity(new Intent(getActivity(), CWYY.class));
                                            break;
                                        case 9:
                                            MainActivity.TZ();
                                            break;
                                        default:
                                            Intent intent = new Intent(getActivity(), FW.class);
                                            intent.putExtra("name", s_a_gvArrayList.get(position).getServiceName());
                                            startActivity(intent);
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

    private void RMZT() {
        new HttpUtil()
                .sendResultToken("/prod-api/press/press/list?hot=y", "", "GET", new Callback() {
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
                                    text2.setText(jsonArray.getJSONObject(1).getString("title"));
                                    Glide.with(getActivity())
                                            .load(App.url + jsonArray.getJSONObject(0).getString("cover"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(image1);
                                    Glide.with(getActivity())
                                            .load(App.url + jsonArray.getJSONObject(1).getString("cover"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(image2);
                                    line1.setOnClickListener(v -> {
                                        try {
                                            Intent intent = new Intent(getActivity(), F_A_XQ.class);
                                            intent.putExtra("s", App.url + jsonArray.getJSONObject(0).getString("cover"));
                                            intent.putExtra("name", "热门主题一");
                                            startActivity(intent);
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });
                                    line2.setOnClickListener(v -> {
                                        try {
                                            Intent intent = new Intent(getActivity(), F_A_XQ.class);
                                            intent.putExtra("s", App.url + jsonArray.getJSONObject(1).getString("cover"));
                                            intent.putExtra("name", "热门主题二");
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

    private void TL() {
        new HttpUtil()
                .sendResultToken("/prod-api/press/category/list", "", "GET", new Callback() {
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
                                try {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        tablayout.addTab(tablayout.newTab().setText(jsonArray.getJSONObject(i).getString("name")));
                                    }
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            });
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
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void LV(String id) {
        new HttpUtil()
                .sendResultToken("/prod-api/press/press/list?type=" + id, "", "GET", new Callback() {
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
                            adapter_a_lv = new Adapter_A_LV(getActivity(), s_a_lvArrayList);
                            getActivity().runOnUiThread(() -> {
                                listview.setAdapter(adapter_a_lv);
                                ListAdapter listAdapter = listview.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < s_a_lvArrayList.size(); i++) {
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
        ss = (EditText) getView().findViewById(R.id.ss);
        banner = (Banner) getView().findViewById(R.id.banner);
        gridview = (GridView) getView().findViewById(R.id.gridview);
        line1 = (LinearLayout) getView().findViewById(R.id.line1);
        image1 = (ImageView) getView().findViewById(R.id.image1);
        text1 = (TextView) getView().findViewById(R.id.text1);
        line2 = (LinearLayout) getView().findViewById(R.id.line2);
        image2 = (ImageView) getView().findViewById(R.id.image2);
        text2 = (TextView) getView().findViewById(R.id.text2);
        tablayout = (TabLayout) getView().findViewById(R.id.tablayout);
        listview = (ListView) getView().findViewById(R.id.listview);
    }
}
