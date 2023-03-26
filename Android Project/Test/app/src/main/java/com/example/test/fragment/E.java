package com.example.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test.R;
import com.example.test.ui.DDLB;
import com.example.test.ui.GRZX;
import com.example.test.ui.Login;
import com.example.test.ui.XGMM;
import com.example.test.ui.YJFK;
import com.example.test.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class E extends Fragment {
    private TextView header;
    private TextView nc;
    private LinearLayout grxx;
    private LinearLayout ddlb;
    private LinearLayout xgmm;
    private LinearLayout yjfk;
    private TextView tcdl;

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
        //跳转界面
        TZJM();
        new HttpUtil()
                .sendResulToken("/prod-api/api/common/user/getInfo", "", "GET", new Callback() {
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

    private void TZJM() {
        grxx.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), GRZX.class));
        });
        ddlb.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), DDLB.class));
        });
        xgmm.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), XGMM.class));
        });
        yjfk.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), YJFK.class));
        });
        tcdl.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), Login.class));
        });
    }

    private void initView() {
        header = getView().findViewById(R.id.header);
        nc = getView().findViewById(R.id.nc);
        grxx = getView().findViewById(R.id.grxx);
        ddlb = getView().findViewById(R.id.ddlb);
        xgmm = getView().findViewById(R.id.xgmm);
        yjfk = getView().findViewById(R.id.yjfk);
        tcdl = getView().findViewById(R.id.tcdl);
    }
}
