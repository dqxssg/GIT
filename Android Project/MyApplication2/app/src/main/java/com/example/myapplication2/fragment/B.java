package com.example.myapplication2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication2.R;
import com.example.myapplication2.adapter.Adapter_A_GridView;
import com.example.myapplication2.adapter.Adapter_B_GridView;
import com.example.myapplication2.bean.ShuJv_A_GridView;
import com.example.myapplication2.ui.AXJZ;
import com.example.myapplication2.ui.FY;
import com.example.myapplication2.ui.L;
import com.example.myapplication2.ui.LS;
import com.example.myapplication2.ui.MainActivity;
import com.example.myapplication2.ui.TNE;
import com.example.myapplication2.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class B extends Fragment {
    private ArrayList<ShuJv_A_GridView> shuJv_a_gridViews = new ArrayList<>();
    private Adapter_B_GridView adapter_b_gridView;
    private TextView header;
    private GridView gridview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.b, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        header.setText("全部服务");
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
                            shuJv_a_gridViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_A_GridView>>() {
                            }.getType());
                            shuJv_a_gridViews.sort(new Comparator<ShuJv_A_GridView>() {
                                @Override
                                public int compare(ShuJv_A_GridView o1, ShuJv_A_GridView o2) {
                                    return o1.getId() + o2.getId();
                                }
                            });
                            adapter_b_gridView = new Adapter_B_GridView(getActivity(), shuJv_a_gridViews);
                            getActivity().runOnUiThread(() -> {
                                gridview.setAdapter(adapter_b_gridView);
                                gridview.setOnItemClickListener((parent, view, position, id) -> {
                                    switch (position) {
                                        case 0:
                                            startActivity(new Intent(getActivity(), LS.class));
                                            break;
                                        case 1:
                                            startActivity(new Intent(getActivity(), L.class));
                                            break;
                                        case 2:
                                            startActivity(new Intent(getActivity(), TNE.class));
                                            break;
                                        case 10:
                                            startActivity(new Intent(getActivity(), FY.class));
                                            break;
                                        case 15:
                                            startActivity(new Intent(getActivity(), AXJZ.class));
                                            break;
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
        gridview = (GridView) getView().findViewById(R.id.gridview);
    }
}
