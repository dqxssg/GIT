package com.example.myapplication4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication4.R;
import com.example.myapplication4.bean.S_LJYY_LV;

import java.util.ArrayList;

public class Adapter_LJYY_LV extends ArrayAdapter<S_LJYY_LV> {
    public Adapter_LJYY_LV(@NonNull Context context, ArrayList<S_LJYY_LV> s_ljyy_lvs) {
        super(context, 0, s_ljyy_lvs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHoldedr viewHoldedr = new ViewHoldedr();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ljyy_lv, parent, false);
            viewHoldedr.cph = convertView.findViewById(R.id.cph);
            viewHoldedr.radio = convertView.findViewById(R.id.radio);
            convertView.setTag(viewHoldedr);
        } else {
            viewHoldedr = (ViewHoldedr) convertView.getTag();
        }
        S_LJYY_LV s_ljyy_lv = getItem(position);
        viewHoldedr.cph.setText("车牌号：" + s_ljyy_lv.getPlateNo());
        viewHoldedr.radio.setOnClickListener(v -> {
            onItemListener.onClick(position);
        });
        return convertView;
    }

    private class ViewHoldedr {
        TextView cph;
        RadioButton radio;
    }

    public interface onItemListener {
        void onClick(int i);
    }

    public onItemListener onItemListener;

    public void setOnItemListener(onItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
}
