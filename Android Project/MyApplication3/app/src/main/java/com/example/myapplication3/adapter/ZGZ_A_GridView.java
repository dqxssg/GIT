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
import com.example.myapplication3.bean.ShuJv_ZGZ_A_GridView;

import java.util.ArrayList;

public class ZGZ_A_GridView extends ArrayAdapter<ShuJv_ZGZ_A_GridView> {
    public ZGZ_A_GridView(@NonNull Context context, ArrayList<ShuJv_ZGZ_A_GridView> shuJv_zgz_a_gridViews) {
        super(context, 0, shuJv_zgz_a_gridViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.zgz_gridview, parent, false);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ShuJv_ZGZ_A_GridView shuJv_zgz_a_gridView = getItem(position);
        viewHolder.text.setText(shuJv_zgz_a_gridView.getProfessionName());
        return convertView;
    }

    class ViewHolder {
        TextView text;
    }
}
