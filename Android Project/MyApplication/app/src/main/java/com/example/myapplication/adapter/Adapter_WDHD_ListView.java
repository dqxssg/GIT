package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.bean.ShuJv_WDHD_ListView;

import java.util.ArrayList;

public class Adapter_WDHD_ListView extends ArrayAdapter<ShuJv_WDHD_ListView> {
    public Adapter_WDHD_ListView(@NonNull Context context, ArrayList<ShuJv_WDHD_ListView> shuJv_wdhd_listViews) {
        super(context, 0, shuJv_wdhd_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_wdhd_listview, parent, false);
            viewHolder.title = convertView.findViewById(R.id.title);
            viewHolder.cbdw = convertView.findViewById(R.id.cbdw);
            viewHolder.ryyq = convertView.findViewById(R.id.ryyq);
            viewHolder.hdkssj = convertView.findViewById(R.id.hdkssj);
            viewHolder.bm = convertView.findViewById(R.id.bm);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_WDHD_ListView shuJv_wdhd_listView = getItem(position);
        viewHolder.title.setText(shuJv_wdhd_listView.getTitle());
        viewHolder.cbdw.setText("承办单位：" + shuJv_wdhd_listView.getUndertaker());
        viewHolder.ryyq.setText("人员要求：" + shuJv_wdhd_listView.getRequireText());
        viewHolder.hdkssj.setText("开始时间：" + shuJv_wdhd_listView.getStartAt());
        viewHolder.bm.setOnClickListener(v -> {
            onItemListener.onClick(position);
        });
        return convertView;
    }

    class ViewHolder {
        TextView title;
        TextView cbdw;
        TextView ryyq;
        TextView hdkssj;
        TextView bm;
    }

    public interface onItemListener {
        void onClick(int i);
    }

    public onItemListener onItemListener;

    public void setOnItemListener(onItemListener onItemListener) {
        this.onItemListener = onItemListener;

    }
}
