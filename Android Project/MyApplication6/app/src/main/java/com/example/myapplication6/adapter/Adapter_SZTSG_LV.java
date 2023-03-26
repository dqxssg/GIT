package com.example.myapplication6.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication6.R;
import com.example.myapplication6.bean.S_SZTSG_LV;

import java.util.ArrayList;

public class Adapter_SZTSG_LV extends ArrayAdapter<S_SZTSG_LV> {
    public Adapter_SZTSG_LV(@NonNull Context context, ArrayList<S_SZTSG_LV> s_sztsg_lvs) {
        super(context, 0, s_sztsg_lvs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHodelr viewHodelr = new ViewHodelr();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sztsg_lv, parent, false);
            viewHodelr.mc = convertView.findViewById(R.id.mc);
            viewHodelr.dz = convertView.findViewById(R.id.dz);
            viewHodelr.sj = convertView.findViewById(R.id.sj);
            viewHodelr.zt = convertView.findViewById(R.id.zt);
            convertView.setTag(viewHodelr);
        } else {
            viewHodelr = (ViewHodelr) convertView.getTag();
        }
        S_SZTSG_LV s_sztsg_lv = getItem(position);
        viewHodelr.mc.setText(s_sztsg_lv.getName());
        viewHodelr.dz.setText(s_sztsg_lv.getAddress());
        viewHodelr.sj.setText(s_sztsg_lv.getBusinessHours());
        if (s_sztsg_lv.getBusinessState().equals("1")) {
            viewHodelr.zt.setText("营业中");
        } else {
            viewHodelr.zt.setText("未营业");
        }
        return convertView;
    }

    private class ViewHodelr {
        TextView mc;
        TextView dz;
        TextView sj;
        TextView zt;
    }
}
