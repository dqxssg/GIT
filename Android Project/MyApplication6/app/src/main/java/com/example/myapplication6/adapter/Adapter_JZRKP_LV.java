package com.example.myapplication6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication6.R;
import com.example.myapplication6.bean.S_JZRKP_LV;
import com.example.myapplication6.ui.MZKSFZ;

import java.util.ArrayList;

public class Adapter_JZRKP_LV extends ArrayAdapter<S_JZRKP_LV> {

    public Adapter_JZRKP_LV(@NonNull Context context, ArrayList<S_JZRKP_LV> s_jzrkp_lvs) {
        super(context, 0, s_jzrkp_lvs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHOdler viewHOdler = new ViewHOdler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_jzrkp_lv, parent, false);
            viewHOdler.xm = (TextView) convertView.findViewById(R.id.xm);
            viewHOdler.xb = (TextView) convertView.findViewById(R.id.xb);
            viewHOdler.sfzh = (TextView) convertView.findViewById(R.id.sfzh);
            viewHOdler.csrq = (TextView) convertView.findViewById(R.id.csrq);
            viewHOdler.sjh = (TextView) convertView.findViewById(R.id.sjh);
            viewHOdler.dz = (TextView) convertView.findViewById(R.id.dz);
            viewHOdler.tz = (TextView) convertView.findViewById(R.id.tz);
            convertView.setTag(viewHOdler);
        } else {
            viewHOdler = (ViewHOdler) convertView.getTag();
        }
        S_JZRKP_LV s_jzrkp_lv = getItem(position);
        viewHOdler.xm.setText(s_jzrkp_lv.getName());
        if (s_jzrkp_lv.getSex().equals("0")) {
            viewHOdler.xb.setText("男");
        } else {
            viewHOdler.xb.setText("女");
        }
        viewHOdler.sfzh.setText(s_jzrkp_lv.getCardId() + "");
        viewHOdler.csrq.setText("出生日期：" + s_jzrkp_lv.getBirthday());
        viewHOdler.sjh.setText("手机号：" + s_jzrkp_lv.getTel());
        viewHOdler.dz.setText("地址：" + s_jzrkp_lv.getAddress());
        viewHOdler.tz.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MZKSFZ.class);
            intent.putExtra("ID", s_jzrkp_lv.getId());
            intent.putExtra("mmz", s_jzrkp_lv.getName());
            getContext().startActivity(intent);
        });
        return convertView;
    }

    private class ViewHOdler {
        private TextView xm;
        private TextView xb;
        private TextView sfzh;
        private TextView csrq;
        private TextView sjh;
        private TextView dz;
        private TextView tz;
    }
}
