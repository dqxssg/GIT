package com.example.myapplication9.adapter;

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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication9.R;
import com.example.myapplication9.bean.S_HD_LV;

import java.util.ArrayList;

public class Adapter_HD_LV extends ArrayAdapter<S_HD_LV> {
    public Adapter_HD_LV(@NonNull Context context, ArrayList<S_HD_LV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_hd_lv, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.mc = convertView.findViewById(R.id.mc);
            viewHolder.bmrs = convertView.findViewById(R.id.bmrs);
            viewHolder.dzs = convertView.findViewById(R.id.dzs);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_HD_LV s_hd_lv = getItem(position);
        Glide.with(getContext())
                .load(s_hd_lv.getImage())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.image);
        viewHolder.mc.setText(s_hd_lv.getName());
        viewHolder.bmrs.setText("报名人数：" + s_hd_lv.getCount());
        viewHolder.dzs.setText("点赞数：" + s_hd_lv.getPraiseCount());
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView mc;
        TextView bmrs;
        TextView dzs;
    }
}
