package com.example.myapplication4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication4.R;
import com.example.myapplication4.bean.S_LJYY_LV;

import java.util.ArrayList;

public class Adapter_CLGL_LV extends ArrayAdapter<S_LJYY_LV> {
    public Adapter_CLGL_LV(@NonNull Context context, ArrayList<S_LJYY_LV> s_ljyy_lvs) {
        super(context, 0, s_ljyy_lvs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_clgl_lv, parent, false);
            viewHolder.cph = convertView.findViewById(R.id.cph);
            viewHolder.cjh = convertView.findViewById(R.id.cjh);
            viewHolder.cllx = convertView.findViewById(R.id.cllx);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_LJYY_LV s_ljyy_lv = getItem(position);
        viewHolder.cph.setText("车牌号：" + s_ljyy_lv.getPlateNo());
        viewHolder.cjh.setText("车架号：" + s_ljyy_lv.getEngineNo());
        viewHolder.cllx.setText("车辆类型：" + s_ljyy_lv.getType());
        return convertView;
    }

    private class ViewHolder {
        TextView cph;
        TextView cjh;
        TextView cllx;
    }
}
