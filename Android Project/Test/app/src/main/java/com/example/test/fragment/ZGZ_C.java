package com.example.test.fragment;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.ui.BJGRJL;
import com.example.test.ui.CXGRJL;
import com.example.test.ui.XZGRJL;

public class ZGZ_C extends Fragment {
    private TextView cx;
    private TextView xz;
    private TextView bj;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zgz_c, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        cx.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), CXGRJL.class));
        });
        xz.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), XZGRJL.class));
        });
        bj.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), BJGRJL.class));
        });
    }

    private void initView() {
        cx = getActivity().findViewById(R.id.cx);
        xz = getActivity().findViewById(R.id.xz);
        bj = getActivity().findViewById(R.id.bj);
    }
}
