package com.example.myapplication2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;
import com.example.myapplication2.bean.ShuJv_TNEJL_ListView;

import java.util.ArrayList;

public class Adapter_TNEJL_ListView extends ArrayAdapter<ShuJv_TNEJL_ListView> {
    int i;

    public Adapter_TNEJL_ListView(@NonNull Context context, ArrayList<ShuJv_TNEJL_ListView> shuJv_tnejl_listViews, int i) {
        super(context, 0, shuJv_tnejl_listViews);
        this.i = i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tnejl_listview, parent, false);
            viewHolder.cph = convertView.findViewById(R.id.cph);
            viewHolder.sfje = convertView.findViewById(R.id.sfje);
            viewHolder.tccmc = convertView.findViewById(R.id.tccmc);
            viewHolder.crcsj = convertView.findViewById(R.id.crcsj);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_TNEJL_ListView shuJv_tnejl_listView = getItem(position);
        if (position < i) {
            viewHolder.cph.setText("车牌号：" + shuJv_tnejl_listView.getPlateNumber());
            viewHolder.sfje.setText("收费金额：" + shuJv_tnejl_listView.getMonetary());
            viewHolder.tccmc.setText("停车场名称：" + shuJv_tnejl_listView.getParkName());
            viewHolder.crcsj.setText("出入场时间：" + shuJv_tnejl_listView.getEntryTime() + "--" + shuJv_tnejl_listView.getOutTime());
        }
        return convertView;
    }

    class ViewHolder {
        TextView cph;
        TextView sfje;
        TextView tccmc;
        TextView crcsj;
    }
}
