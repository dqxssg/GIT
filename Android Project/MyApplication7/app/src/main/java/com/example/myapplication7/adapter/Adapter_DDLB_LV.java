package com.example.myapplication7.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication7.R;
import com.example.myapplication7.bean.S_DDLB_LV;

import java.util.ArrayList;

public class Adapter_DDLB_LV extends ArrayAdapter<S_DDLB_LV> {
    public Adapter_DDLB_LV(@NonNull Context context, ArrayList<S_DDLB_LV> s_ddlb_lvs) {
        super(context, 0, s_ddlb_lvs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHodoler viewHodoler = new ViewHodoler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ddlb_lv, parent, false);
            viewHodoler.ddh = convertView.findViewById(R.id.ddh);
            viewHodoler.ddlx = convertView.findViewById(R.id.ddlx);
            viewHodoler.sj = convertView.findViewById(R.id.sj);
            convertView.setTag(viewHodoler);
        } else {
            viewHodoler = (ViewHodoler) convertView.getTag();
        }
        S_DDLB_LV s_ddlb_lv = getItem(position);
        viewHodoler.ddh.setText("订单号："+s_ddlb_lv.getOrderNo());
        viewHodoler.ddlx.setText("订单类型："+s_ddlb_lv.getOrderType());
        viewHodoler.sj.setText(s_ddlb_lv.getCreateTime()+"");
        return convertView;
    }

    private class ViewHodoler {
        TextView ddh;
        TextView ddlx;
        TextView sj;
    }
}
