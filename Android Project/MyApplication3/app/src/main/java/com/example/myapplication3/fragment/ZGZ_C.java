package com.example.myapplication3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication3.R;
import com.example.myapplication3.ui.BJJL;
import com.example.myapplication3.ui.CXJL;
import com.example.myapplication3.ui.XZJL;

public class ZGZ_C extends Fragment {
    private TextView xzjl;
    private TextView cxjl;
    private TextView bjjl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zgz_c, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        xzjl.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), CXJL.class));
        });
        cxjl.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), XZJL.class));
        });
        bjjl.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), BJJL.class));
        });

    }

    private void initView() {
        xzjl = (TextView) getView().findViewById(R.id.xzjl);
        cxjl = (TextView) getView().findViewById(R.id.cxjl);
        bjjl = (TextView) getView().findViewById(R.id.bjjl);
    }
}
