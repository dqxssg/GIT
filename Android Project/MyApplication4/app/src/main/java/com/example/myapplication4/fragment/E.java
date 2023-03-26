package com.example.myapplication4.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication4.R;
import com.example.myapplication4.ui.DDLB;
import com.example.myapplication4.ui.GRXX;
import com.example.myapplication4.ui.XGMM;
import com.example.myapplication4.ui.YJFK;
import com.example.myapplication4.util.Httputil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class E extends Fragment {
    private TextView header;
    private TextView nc;
    private TextView grxx;
    private TextView ddlb;
    private TextView xgmm;
    private TextView yjfk;
    private TextView tc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.e, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        header.setText("个人中心");
        //跳转
        TZ();
        //显示
        XS();
    }

    private void XS() {
        new Httputil()
                .sendResultToken("/prod-api/api/common/user/getInfo", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("user");
                            getActivity().runOnUiThread(() -> {
                                try {
                                    nc.setText(jsonObject1.getString("nickName"));
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

    private void TZ() {
        grxx.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), GRXX.class));
        });
        ddlb.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), DDLB.class));
        });
        xgmm.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), XGMM.class));
        });
        yjfk.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), YJFK.class));
        });
//        tc.setOnClickListener(v -> {
//            startActivity(new Intent(getActivity(), Login.class));
//        });
    }

    private void initView() {
        header = (TextView) getView().findViewById(R.id.header);
        nc = (TextView) getView().findViewById(R.id.nc);
        grxx = (TextView) getView().findViewById(R.id.grxx);
        ddlb = (TextView) getView().findViewById(R.id.ddlb);
        xgmm = (TextView) getView().findViewById(R.id.xgmm);
        yjfk = (TextView) getView().findViewById(R.id.yjfk);
        tc = (TextView) getView().findViewById(R.id.tc);
    }
}