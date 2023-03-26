package com.example.myapplication7.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication7.R;
import com.example.myapplication7.ui.Main;

public class E extends Fragment {
    private TextView wlsz;
    private TextView jrzy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.e, null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initView();
        wlsz.setOnClickListener(v -> {
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            Dialog_WLSZ dialog_flzc = new Dialog_WLSZ();
            transaction.add(dialog_flzc, "dialog-tag");
            transaction.show(dialog_flzc);
            transaction.commit();
        });
        jrzy.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), Main.class));
        });
    }

    private void initView() {
        wlsz = (TextView) getView().findViewById(R.id.wlsz);
        jrzy = (TextView) getView().findViewById(R.id.jrzy);
    }
}
