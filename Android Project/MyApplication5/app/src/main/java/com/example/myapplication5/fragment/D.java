package com.example.myapplication5.fragment;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication5.R;
import com.example.myapplication5.adapter.Adapter_D_LV1;
import com.example.myapplication5.adapter.Adapter_D_LV2;
import com.example.myapplication5.adapter.Adapter_D_RV;
import com.example.myapplication5.adapter.Adapter_D_SS;
import com.example.myapplication5.bean.S_D_LV1;
import com.example.myapplication5.bean.S_D_LV2;
import com.example.myapplication5.bean.S_D_RV;
import com.example.myapplication5.ui.D_SS;
import com.example.myapplication5.ui.LSLB;
import com.example.myapplication5.ui.XS_XQ;
import com.example.myapplication5.ui.ZXLB;
import com.example.myapplication5.util.App;
import com.example.myapplication5.util.HttpUtil;
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

public class D extends Fragment {
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<S_D_LV1> s_d_lv1ArrayList = new ArrayList<>();
    private Adapter_D_LV1 adapter_d_lv1;
    private ArrayList<S_D_LV2> s_d_lv2ArrayList = new ArrayList<>();
    private Adapter_D_LV2 adapter_d_lv2;
    private ArrayList<S_D_RV> s_d_rvArrayList = new ArrayList<>();
    private Adapter_D_RV adapter_d_rv;
    private TextView header;
    private EditText ss;
    private Banner banner;
    private ListView listview1;
    private ListView listview2;
    private RecyclerView recyclerview;
    private TextView ckgd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.d, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        header.setText("法律咨询主页");
        //搜索
        SS();
        //轮播图
        LBT();
        //法律专长
        RV();
        //我的咨询图片
        LV1();
        //本月上榜优选律师
        LV2();
        ckgd.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LSLB.class);
            intent.putExtra("id", "");
            startActivity(intent);
        });
    }

    private void SS() {
        ss.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Intent intent = new Intent(getActivity(), D_SS.class);
                intent.putExtra("name", ss.getText().toString());
                startActivity(intent);
                return true;
            }
            return false;
        });


    }

    private void LV2() {
        new HttpUtil()
                .sendResurltToken("/prod-api/api/lawyer-consultation/lawyer/list-top10", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_d_lv2ArrayList = new Gson().fromJson(jsonObject.getJSONArray("data").toString(), new TypeToken<List<S_D_LV2>>() {
                            }.getType());
                            adapter_d_lv2 = new Adapter_D_LV2(getActivity(), s_d_lv2ArrayList);
                            getActivity().runOnUiThread(() -> {
                                listview2.setAdapter(adapter_d_lv2);
                                adapter_d_lv2.setOnItemListener(new Adapter_D_SS.onItemListener() {
                                    @Override
                                    public void onClick(int i) {
                                        Intent intent = new Intent(getActivity(), XS_XQ.class);
                                        intent.putExtra("id", s_d_lv2ArrayList.get(i).getId());
                                        startActivity(intent);
                                    }
                                });
                                ListAdapter listAdapter = listview2.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < s_d_lv2ArrayList.size(); i++) {
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
    }

    private void LV1() {
        new HttpUtil()
                .sendResurltToken("/prod-api/api/lawyer-consultation/legal-advice/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_d_lv1ArrayList = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_D_LV1>>() {
                            }.getType());
                            adapter_d_lv1 = new Adapter_D_LV1(getActivity(), s_d_lv1ArrayList);
                            getActivity().runOnUiThread(() -> {
                                listview1.setAdapter(adapter_d_lv1);
                                listview1.setOnItemClickListener((parent, view, position, id) -> {
                                    getActivity().startActivity(new Intent(getActivity(), ZXLB.class));
                                });
                                ListAdapter listAdapter = listview1.getAdapter();
                                if (listAdapter == null) {
                                    return;
                                }
                                int h = 0;
                                for (int i = 0; i < s_d_lv1ArrayList.size(); i++) {
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
    }

    private void RV() {
        new HttpUtil()
                .sendResurltToken("/prod-api/api/lawyer-consultation/legal-expertise/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_d_rvArrayList = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_D_RV>>() {
                            }.getType());
                            s_d_rvArrayList.sort(new Comparator<S_D_RV>() {
                                @Override
                                public int compare(S_D_RV o1, S_D_RV o2) {
                                    return o2.getId() - o1.getId();
                                }
                            });
                            adapter_d_rv = new Adapter_D_RV(getActivity().getApplicationContext(), s_d_rvArrayList);
                            getActivity().runOnUiThread(() -> {
                                recyclerview.setAdapter(adapter_d_rv);
                                GridLayoutManager manager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
                                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                recyclerview.setLayoutManager(manager);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });

    }

    private void LBT() {
        new HttpUtil()
                .sendResurltToken("/prod-api/api/lawyer-consultation/ad-banner/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                img.add(App.url + jsonArray.getJSONObject(i).getString("imgUrl"));
                            }
                            getActivity().runOnUiThread(() -> {
                                banner.setAdapter(new BannerImageAdapter<String>(img) {
                                    @Override
                                    public void onBindView(BannerImageHolder bannerImageHolder, String s, int i, int i1) {
                                        Glide.with(bannerImageHolder.imageView)
                                                .load(s)
                                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                                .into(bannerImageHolder.imageView);
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

    private void initView() {
        header = (TextView) getView().findViewById(R.id.header);
        ss = (EditText) getView().findViewById(R.id.ss);
        banner = (Banner) getView().findViewById(R.id.banner);
        listview1 = (ListView) getView().findViewById(R.id.listview1);
        listview2 = (ListView) getView().findViewById(R.id.listview2);
        recyclerview = (RecyclerView) getView().findViewById(R.id.recyclerview);
        ckgd = (TextView) getView().findViewById(R.id.ckgd);
    }
}
