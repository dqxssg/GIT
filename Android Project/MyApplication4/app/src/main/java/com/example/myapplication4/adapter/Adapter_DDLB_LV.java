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
import com.example.myapplication4.bean.S_DDLB_LV;

import java.util.ArrayList;

public class Adapter_DDLB_LV extends ArrayAdapter<S_DDLB_LV> {
    public Adapter_DDLB_LV(@NonNull Context context, ArrayList<S_DDLB_LV> s_ddlb_lvs) {
        super(context, 0, s_ddlb_lvs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ddlb_lv, parent, false);
            viewHolder.ddh = convertView.findViewById(R.id.ddh);
            viewHolder.ddlx = convertView.findViewById(R.id.ddlx);
            viewHolder.sj = convertView.findViewById(R.id.sj);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_DDLB_LV s_ddlb_lv = getItem(position);
        viewHolder.ddh.setText("订单号："+s_ddlb_lv.getOrderNo());
        viewHolder.ddlx.setText("订单类型："+s_ddlb_lv.getOrderType());
        viewHolder.sj.setText("订单时间："+s_ddlb_lv.getPayTime());
        return convertView;
    }

    private class ViewHolder {
        TextView ddh;
        TextView ddlx;
        TextView sj;
    }
}
