package com.example.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.test.R;
import com.example.test.bean.ShuJv_A_GridView;

import java.util.ArrayList;

public class A_GridView extends ArrayAdapter<ShuJv_A_GridView> {
    public A_GridView(@NonNull Context context, ArrayList<ShuJv_A_GridView> shuJv_a_gridViews) {
        super(context, 0, shuJv_a_gridViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.a_gridview, parent, false);
            viewHolder.img = convertView.findViewById(R.id.img);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_A_GridView shuJv_a_gridView = getItem(position);
        if (position < 9) {
            viewHolder.text.setText(shuJv_a_gridView.getServiceName());
            Glide.with(getContext())
                    .load("http://124.93.196.45:10001" + shuJv_a_gridView.getImgUrl())
                    .into(viewHolder.img);
        }
        if (position == 9) {
            viewHolder.text.setText("更多服务");
            viewHolder.img.setImageResource(R.drawable.gdfw);
        }
        return convertView;
    }

    static class ViewHolder {
        ImageView img;
        TextView text;
    }
}

