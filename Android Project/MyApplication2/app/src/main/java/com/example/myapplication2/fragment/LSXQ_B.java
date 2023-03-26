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
import com.example.myapplication2.adapter.Adapter_LSXQ_ListView;
import com.example.myapplication2.bean.ShuJv_LSXQ_ListView;
import com.example.myapplication2.ui.ZX;
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

public class LSXQ_B extends Fragment {
    private ArrayList<ShuJv_LSXQ_ListView> shuJv_lsxq_listViews = new ArrayList<>();
    private Adapter_LSXQ_ListView adapter_lsxq_listView;
    int id;
    private ListView listview;
    private TextView mfzx;

    public LSXQ_B(int id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lsxq_b, null);
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
                            shuJv_lsxq_listViews = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_LSXQ_ListView>>() {
                            }.getType());
                            adapter_lsxq_listView = new Adapter_LSXQ_ListView(getActivity(), shuJv_lsxq_listViews);
                            getActivity().runOnUiThread(() -> {
                                listview.setAdapter(adapter_lsxq_listView);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        mfzx.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ZX.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });
    }

    private void initView() {
        listview = (ListView) getView().findViewById(R.id.listview);
        mfzx = (TextView) getView().findViewById(R.id.mfzx);
    }
}
