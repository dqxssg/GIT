package com.example.myapplication3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication3.R;
import com.example.myapplication3.adapter.ZGZ_B_ListView;
import com.example.myapplication3.bean.ShuJv_ZGZ_B_ListView;
import com.example.myapplication3.util.HttpUtil;
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
                .sendResltToken("/prod-api/api/job/deliver/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            shuJv_zgz_b_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_ZGZ_B_ListView>>() {
                            }.getType());
//                            for (ShuJv_ZGZ_B_ListView shuJv_zgz_b_listView : shuJv_zgz_b_listViews) {
//                                if (shuJv_zgz_b_listView.){
//                                    shuJv_zgz_b_listViews1.add(shuJv_zgz_b_listView)
//                                }
//                            }
//                            zgz_b_listView = new ZGZ_B_ListView(getActivity(), shuJv_zgz_b_listViews1);
//                            getActivity().runOnUiThread(() -> {
//                                listview.setAdapter(zgz_b_listView);
//                            });0
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }
                });
    }

    private void initView() {
        listview = (ListView) getView().findViewById(R.id.listview);
    }
}
