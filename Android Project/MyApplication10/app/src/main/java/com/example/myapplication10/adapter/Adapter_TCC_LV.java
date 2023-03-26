package com.example.myapplication10.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication10.R;
import com.example.myapplication10.bean.S_TCC_LV;
import com.example.myapplication10.ui.TCC;

import java.util.ArrayList;

public class Adapter_TCC_LV extends ArrayAdapter<S_TCC_LV> {
    int i;

    public Adapter_TCC_LV(@NonNull Context context, ArrayList<S_TCC_LV> resource, int i) {
        super(context, 0, resource);
        this.i = i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tcc_lv, parent, false);
            viewHolder.tccm = convertView.findViewById(R.id.tccm);
            viewHolder.kws = convertView.findViewById(R.id.kws);
            viewHolder.dz = convertView.findViewById(R.id.dz);
            viewHolder.sf = convertView.findViewById(R.id.sf);
            viewHolder.jl = convertView.findViewById(R.id.jl);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_TCC_LV s_tcc_lv = getItem(position);
        if (position < i) {
            viewHolder.tccm.setText("停车场名：" + s_tcc_lv.getParkName());
            viewHolder.kws.setText("空位数：" + s_tcc_lv.getSpaceNum());
            viewHolder.dz.setText("地址：" + s_tcc_lv.getAddress());
            viewHolder.sf.setText("收费标准：" + s_tcc_lv.getRateRefer());
            viewHolder.jl.setText("距离：" + s_tcc_lv.getSurCarPort() + "米");
        }
        return convertView;
    }

    private class ViewHolder {
        TextView tccm;
        TextView kws;
        TextView dz;
        TextView sf;
        TextView jl;
    }
}
