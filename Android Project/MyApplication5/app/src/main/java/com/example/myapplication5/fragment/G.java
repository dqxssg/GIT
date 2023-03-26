package com.example.myapplication5.fragment;

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

import com.example.myapplication5.R;
import com.example.myapplication5.adapter.Adapter_G_LV;
import com.example.myapplication5.bean.S_G_LV;
import com.example.myapplication5.ui.MFZX;
import com.example.myapplication5.util.HttpUtil;
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

public class G extends Fragment {
    private ArrayList<S_G_LV> s_g_lvArrayList = new ArrayList<>();
    private Adapter_G_LV adapter_g_lv;
    private int id;
    private ListView listview;
    private TextView mfzx;

    public G(int id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.g, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        new HttpUtil()
                .sendResurltToken("/prod-api/api/lawyer-consultation/lawyer/list-evaluate?lawyerId=" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_g_lvArrayList = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_G_LV>>() {
                            }.getType());
                            adapter_g_lv = new Adapter_G_LV(getActivity(), s_g_lvArrayList);
                            getActivity().runOnUiThread(() -> {
                                listview.setAdapter(adapter_g_lv);
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        mfzx.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MFZX.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });
    }

    private void initView() {
        listview = (ListView)getView(). findViewById(R.id.listview);
        mfzx = (TextView) getView().findViewById(R.id.mfzx);
    }
}
