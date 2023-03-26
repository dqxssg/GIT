package com.example.myapplication10.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication10.R;
import com.example.myapplication10.adapter.Adapter_B_GV;
import com.example.myapplication10.bean.S_A_GV;
import com.example.myapplication10.ui.A_FW;
import com.example.myapplication10.ui.SHJF;
import com.example.myapplication10.ui.TCC;
import com.example.myapplication10.ui.ZHBS;
import com.example.myapplication10.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class B extends Fragment {
    private ArrayList<String> FW = new ArrayList<>();
    private ArrayList<S_A_GV> s_a_gvArrayList1 = new ArrayList<>();
    private ArrayList<S_A_GV> s_a_gvArrayList2 = new ArrayList<>();
    private Adapter_B_GV adapter_b_gv;
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
                .sendResUtil("getAllServiceType", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = new JSONObject();
                                jsonObject1.put("serviceType", jsonArray.getString(i));
                                new HttpUtil()
                                        .sendResUtil("getServiceByType", jsonObject1.toString(), "POST", new Callback() {
                                            @Override
                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                            }

                                            @Override
                                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                String s = response.body().string();
                                                try {
                                                    JSONObject jsonObject2 = new JSONObject(s);
                                                    s_a_gvArrayList1 = new Gson().fromJson(jsonObject2.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_A_GV>>() {
                                                    }.getType());
                                                    for (S_A_GV s_a_gv : s_a_gvArrayList1) {
                                                        s_a_gvArrayList2.add(s_a_gv);
                                                    }
                                                    if (jsonArray.length() == s_a_gvArrayList2.size()) {
                                                        adapter_b_gv = new Adapter_B_GV(getActivity(), s_a_gvArrayList2);
                                                        getActivity().runOnUiThread(() -> {
                                                            gridview.setAdapter(adapter_b_gv);
                                                            gridview.setOnItemClickListener((parent, view, position, id) -> {
                                                                switch (s_a_gvArrayList2.get(position).getServiceName()) {
                                                                    case "停车场":
                                                                        startActivity(new Intent(getActivity(), TCC.class));
                                                                        break;
                                                                    case "智慧巴士":
                                                                        startActivity(new Intent(getActivity(), ZHBS.class));
                                                                        break;
                                                                    case "生活缴费":
                                                                        startActivity(new Intent(getActivity(), SHJF.class));
                                                                        break;
                                                                    default:
                                                                        Intent intent = new Intent(getActivity(), A_FW.class);
                                                                        intent.putExtra("name", s_a_gvArrayList2.get(position).getServiceName());
                                                                        startActivity(intent);
                                                                        break;
                                                                }
                                                            });
                                                        });
                                                    }
                                                } catch (JSONException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                        });
                            }
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