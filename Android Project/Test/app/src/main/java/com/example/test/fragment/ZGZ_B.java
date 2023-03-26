package com.example.test.fragment;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.R;
import com.example.test.bean.ShuJv_ZGZ_B_ListView;
import com.example.test.adapter.ZGZ_B_ListView;
import com.example.test.util.HttpUtil;
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

public class ZGZ_B extends Fragment {
    private ArrayList<ShuJv_ZGZ_B_ListView> shuJv_zgz_b_listViews = new ArrayList<>();
    private ArrayList<ShuJv_ZGZ_B_ListView> shuJv_zgz_b_listViews1 = new ArrayList<>();
    private ZGZ_B_ListView zgz_b_listView;
    private ListView listview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zgz_b, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        new HttpUtil()
                .sendResulToken("/prod-api/api/job/deliver/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        System.out.println("123456789");
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            System.out.println(jsonObject);
//                            1117214
                            shuJv_zgz_b_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_ZGZ_B_ListView>>() {
                            }.getType());
                            zgz_b_listView = new ZGZ_B_ListView(getActivity(), shuJv_zgz_b_listViews);
                            getActivity().runOnUiThread(() -> {
                                listview.setAdapter(zgz_b_listView);
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void initView() {
        listview = getActivity().findViewById(R.id.listview);
    }
}
