package com.example.myapplication7.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication7.R;

public class Dialog_WLSZ extends DialogFragment {
    private TextView wlsz;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_wlsz, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        wlsz.setOnClickListener(v -> {
            dismiss();
        });
    }

    private void initView() {
        wlsz = (TextView) getView().findViewById(R.id.wlsz);
    }
}
