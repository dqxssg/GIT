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
import com.example.test.bean.ShuJv_ZGZ_B_ListView;

import java.util.ArrayList;

public class ZGZ_B_ListView extends ArrayAdapter<ShuJv_ZGZ_B_ListView> {

    public ZGZ_B_ListView(@NonNull Context context, ArrayList<ShuJv_ZGZ_B_ListView> shuJv_zgz_b_listViews) {
        super(context, 0, shuJv_zgz_b_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.zgz_b_listview, parent, false);
            viewHolder.gwmc = convertView.findViewById(R.id.gwmc);
            viewHolder.gsmc = convertView.findViewById(R.id.gsmc);
            viewHolder.xz = convertView.findViewById(R.id.xz);
            viewHolder.tdsj = convertView.findViewById(R.id.tdsj);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_ZGZ_B_ListView shuJv_zgz_b_listView = getItem(position);
        viewHolder.gwmc.setText("岗位名称：" + shuJv_zgz_b_listView.getPostName());
        viewHolder.gsmc.setText("公司名称：" + shuJv_zgz_b_listView.getCompanyName());
        viewHolder.xz.setText("薪资：" + shuJv_zgz_b_listView.getMoney());
        viewHolder.tdsj.setText("投递简历时间：" + shuJv_zgz_b_listView.getSatrTime());
        return convertView;
    }

    class ViewHolder {
        private TextView gwmc;
        private TextView gsmc;
        private TextView xz;
        private TextView tdsj;
    }
}

