package com.example.myapplication2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication2.R;
import com.example.myapplication2.adapter.Ad_LXQ_B_LV;
import com.example.myapplication2.bean.SJ_LXQ_B_LV;
import com.example.myapplication2.ui.MZX;
import com.example.myapplication2.util.HttpUtil;
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

public class LXQ_B extends Fragment {
    private ArrayList<SJ_LXQ_B_LV> sj_lxq_b_lvs = new ArrayList<>();
    private Ad_LXQ_B_LV ad_lxq_b_lv;
    private int id;
    private ListView listview;
    private TextView mfzx;

    public LXQ_B(int id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lxq_b, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        new HttpUtil()
                .sendResultToken("/prod-api/api/lawyer-consultation/lawyer/list-evaluate?lawyerId=" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            sj_lxq_b_lvs = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<SJ_LXQ_B_LV>>() {
                            }.getType());
                            ad_lxq_b_lv = new Ad_LXQ_B_LV(getActivity(), sj_lxq_b_lvs);
                            getActivity().runOnUiThread(() -> {
                                listview.setAdapter(ad_lxq_b_lv);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        mfzx.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MZX.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });
    }

    private void initView() {
        listview = (ListView) getView().findViewById(R.id.listview);
        mfzx = (TextView) getView().findViewById(R.id.mfzx);
    }
}
