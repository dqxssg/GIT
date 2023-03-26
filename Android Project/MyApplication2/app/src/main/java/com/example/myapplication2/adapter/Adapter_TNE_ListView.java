package com.example.myapplication2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication2.R;
import com.example.myapplication2.bean.ShuJv_TNE_ListView;

import java.util.ArrayList;

public class Adapter_TNE_ListView extends ArrayAdapter<ShuJv_TNE_ListView> {
    private int i;

    public Adapter_TNE_ListView(@NonNull Context context, ArrayList<ShuJv_TNE_ListView> shuJv_tne_listViews, int i) {
        super(context, 0, shuJv_tne_listViews);
        this.i = i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tne_listview, parent, false);
            viewHolder.tccmz = convertView.findViewById(R.id.tccmz);
            viewHolder.wz = convertView.findViewById(R.id.wz);
            viewHolder.kw = convertView.findViewById(R.id.kw);
            viewHolder.sfjg = convertView.findViewById(R.id.sfjg);
            viewHolder.jl = convertView.findViewById(R.id.jl);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_TNE_ListView shuJv_tne_listView = getItem(position);
        if (position <= i) {
            viewHolder.tccmz.setText(shuJv_tne_listView.getParkName());
            viewHolder.wz.setText("位置：" + shuJv_tne_listView.getAddress());
            viewHolder.kw.setText("空位数量：" + shuJv_tne_listView.getVacancy());
            viewHolder.sfjg.setText("收费价格：" + shuJv_tne_listView.getRates()+"元");
            viewHolder.jl.setText("距离：" + shuJv_tne_listView.getDistance()+"km");
        }
        return convertView;
    }

    class ViewHolder {
        TextView tccmz;
        TextView wz;
        TextView kw;
        TextView sfjg;
        TextView jl;
    }
}
