package com.example.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.R;
import com.example.test.bean.ShuJv_TS1;

import java.util.ArrayList;

public class TS1 extends ArrayAdapter<ShuJv_TS1> {
    public TS1(@NonNull Context context, ArrayList<ShuJv_TS1> s) {
        super(context, 0, s);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ts1, parent, false);
            viewHolder.ddh = convertView.findViewById(R.id.ddh);
            viewHolder.nr = convertView.findViewById(R.id.nr);
            viewHolder.sj = convertView.findViewById(R.id.sj);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_TS1 shuJv_ts1 = getItem(position);
        viewHolder.ddh.setText(shuJv_ts1.getInfoNo());
        viewHolder.nr.setText(shuJv_ts1.getDescription());
        viewHolder.sj.setText(shuJv_ts1.getCreateTime());
        return convertView;
    }


    class ViewHolder {
        TextView ddh;
        TextView nr;
        TextView sj;
    }
}
