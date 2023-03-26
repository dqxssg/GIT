package com.example.myapplication3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication3.R;
import com.example.myapplication3.bean.ShuJv_TS_ListView;

import java.util.ArrayList;

public class TS_ListView extends ArrayAdapter<ShuJv_TS_ListView> {

    public TS_ListView(@NonNull Context context, ArrayList<ShuJv_TS_ListView>shuJv_ts_listViews) {
        super(context, 0,shuJv_ts_listViews);
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
        ShuJv_TS_ListView shuJv_ts1 = getItem(position);
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
