package com.example.myapplication4.fragment;

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

import com.example.myapplication4.R;
import com.example.myapplication4.adapter.Adapter_F_A_GV;
import com.example.myapplication4.adapter.Adapter_F_B_GV;
import com.example.myapplication4.bean.S_F_A_GV;
import com.example.myapplication4.ui.HDGL;
import com.example.myapplication4.ui.KDY;
import com.example.myapplication4.ui.MainActivity;
import com.example.myapplication4.ui.ZHBS;
import com.example.myapplication4.ui.ZHJG;
import com.example.myapplication4.util.Httputil;
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
    private ArrayList<S_F_A_GV> s_f_a_gvArrayList = new ArrayList<>();
    private Adapter_F_B_GV adapter_f_b_gv;
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
        new Httputil()
                .sendResultToken("/prod-api/api/service/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_f_a_gvArrayList = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_F_A_GV>>() {
                            }.getType());
                            s_f_a_gvArrayList.sort(new Comparator<S_F_A_GV>() {
                                @Override
                                public int compare(S_F_A_GV o1, S_F_A_GV o2) {
                                    return o2.getId() - o1.getId();
                                }
                            });
                            s_f_a_gvArrayList.add(new S_F_A_GV().setServiceName("预约检车"));
                            adapter_f_b_gv = new Adapter_F_B_GV(getActivity(), s_f_a_gvArrayList);
                            getActivity().runOnUiThread(() -> {
                                gridview.setAdapter(adapter_f_b_gv);
                                gridview.setOnItemClickListener((parent, view, position, id) -> {
                                    switch (position) {
                                        case 7:
                                            startActivity(new Intent(getActivity(), HDGL.class));
                                            break;
                                        case 11:
                                            startActivity(new Intent(getActivity(), KDY.class));
                                            break;
                                        case 16:
                                            startActivity(new Intent(getActivity(), ZHBS.class));
                                            break;
                                        case 18:
                                            startActivity(new Intent(getActivity(), ZHJG.class));
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
