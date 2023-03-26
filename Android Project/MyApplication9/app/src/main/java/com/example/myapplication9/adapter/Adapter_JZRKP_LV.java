package com.example.myapplication9.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication9.R;
import com.example.myapplication9.bean.S_JZRKP_LV;

import java.util.ArrayList;

public class Adapter_JZRKP_LV extends ArrayAdapter<S_JZRKP_LV> {
    public Adapter_JZRKP_LV(@NonNull Context context, ArrayList<S_JZRKP_LV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        VIewhODler vIewhODler = new VIewhODler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_jzrkp_lv, parent, false);
            vIewhODler.xm = convertView.findViewById(R.id.xm);
            vIewhODler.xb = convertView.findViewById(R.id.xb);
            vIewhODler.sfzh = convertView.findViewById(R.id.sfzh);
            vIewhODler.csrq = convertView.findViewById(R.id.csrq);
            vIewhODler.sjh = convertView.findViewById(R.id.sjh);
            vIewhODler.dz = convertView.findViewById(R.id.dz);
            vIewhODler.tz = convertView.findViewById(R.id.tz);
            convertView.setTag(vIewhODler);
        } else {
            vIewhODler = (VIewhODler) convertView.getTag();
        }
        S_JZRKP_LV s_jzrkp_lv = getItem(position);
        vIewhODler.xm.setText("姓名：" + s_jzrkp_lv.getName());
        vIewhODler.xb.setText("性别：" + s_jzrkp_lv.getSex());
        vIewhODler.sfzh.setText("身份证号：" + s_jzrkp_lv.getID());
        vIewhODler.csrq.setText("出生日期：" + s_jzrkp_lv.getBirthday());
        vIewhODler.sjh.setText("手机号：" + s_jzrkp_lv.getTel());
        vIewhODler.dz.setText("地址：" + s_jzrkp_lv.getAddress());
        vIewhODler.tz.setOnClickListener(v -> {
            setOnItemClick.onItemclick(position);
        });
        return convertView;
    }

    private class VIewhODler {
        TextView tz;
        TextView xm;
        TextView xb;
        TextView sfzh;
        TextView csrq;
        TextView sjh;
        TextView dz;
    }

    public interface setOnItemClick {
        void onItemclick(int position);
    }

    public setOnItemClick setOnItemClick;

    public void setItemOnclick(setOnItemClick setOnItemClick) {
        this.setOnItemClick = setOnItemClick;
    }
}
