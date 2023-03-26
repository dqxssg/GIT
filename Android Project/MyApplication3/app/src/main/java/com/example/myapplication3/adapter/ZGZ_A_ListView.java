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
import com.example.myapplication3.bean.ShuJv_ZGZ_A_ListView;

import java.util.ArrayList;

public class ZGZ_A_ListView extends ArrayAdapter<ShuJv_ZGZ_A_ListView> {
    public ZGZ_A_ListView(@NonNull Context context, ArrayList<ShuJv_ZGZ_A_ListView> shuJv_zgz_a_listViews) {
        super(context, 0, shuJv_zgz_a_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.zgz_listview, parent, false);
            viewHolder.zwmc = convertView.findViewById(R.id.zwmc);
            viewHolder.gwzz = convertView.findViewById(R.id.gwzz);
            viewHolder.gsdd = convertView.findViewById(R.id.gsdd);
            viewHolder.xz = convertView.findViewById(R.id.xz);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_ZGZ_A_ListView shuJv_zgz_a_listView = getItem(position);
        viewHolder.zwmc.setText("职位名称：" + shuJv_zgz_a_listView.getName());
        viewHolder.gwzz.setText("岗位职责:" + shuJv_zgz_a_listView.getObligation());
        viewHolder.gsdd.setText("公司地点：" + shuJv_zgz_a_listView.getAddress());
        viewHolder.xz.setText("薪资待遇：" + shuJv_zgz_a_listView.getSalary());
        return convertView;
    }

    class ViewHolder {
        TextView zwmc;
        TextView gwzz;
        TextView gsdd;
        TextView xz;
    }
}
