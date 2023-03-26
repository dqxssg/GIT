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
import com.example.myapplication3.bean.ShuJv_YDXQ_ListView;

import java.util.ArrayList;

public class YDXQ_ListView extends ArrayAdapter<ShuJv_YDXQ_ListView> {
    public YDXQ_ListView(@NonNull Context context, ArrayList<ShuJv_YDXQ_ListView> shuJv_ydxq_listViews) {
        super(context, 0, shuJv_ydxq_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ydxq_listview, parent, false);
            viewHolder.sj = convertView.findViewById(R.id.sj);
            viewHolder.text1 = convertView.findViewById(R.id.text1);
            viewHolder.text2 = convertView.findViewById(R.id.text2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_YDXQ_ListView shuJv_ddxq_yd = getItem(position);
        viewHolder.sj.setText(shuJv_ddxq_yd.getEventAt());
        viewHolder.text1.setText(shuJv_ddxq_yd.getStateName());
        viewHolder.text2.setText(shuJv_ddxq_yd.getAddressAt());
        return convertView;
    }

    class ViewHolder {
        TextView sj;
        TextView text1;
        TextView text2;
    }
}
