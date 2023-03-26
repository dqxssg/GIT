package com.example.zhcs.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zhcs.R;
import com.example.zhcs.bean.S_TS;

import java.util.ArrayList;

public class Adapter_TS extends ArrayAdapter<S_TS> {
    public Adapter_TS(@NonNull Context context, ArrayList<S_TS> s) {
        super(context, 0, s);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ts, parent, false);
            viewHolder.ddh = convertView.findViewById(R.id.ddh);
            viewHolder.nr = convertView.findViewById(R.id.nr);
            viewHolder.sj = convertView.findViewById(R.id.sj);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_TS s_ts = getItem(position);
        viewHolder.ddh.setText(s_ts.getInfoNo());
        viewHolder.nr.setText(s_ts.getDescription());
        viewHolder.sj.setText(s_ts.getCreateTime());
        return convertView;
    }

    class ViewHolder {
        TextView ddh;
        TextView nr;
        TextView sj;
    }
}

