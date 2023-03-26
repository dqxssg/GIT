package com.example.myapplication.adapter;

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
import com.example.myapplication.R;
import com.example.myapplication.bean.ShuJv_HDGL_ListView;
import com.example.myapplication.util.App;

import java.util.ArrayList;

public class Adapter_HDGL_ListView extends ArrayAdapter<ShuJv_HDGL_ListView> {
    public Adapter_HDGL_ListView(@NonNull Context context, ArrayList<ShuJv_HDGL_ListView> shuJv_hdgl_listViews) {
        super(context, 0, shuJv_hdgl_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_hdgl_listview, parent, false);
            viewHolder.img = convertView.findViewById(R.id.img);
            viewHolder.cr = convertView.findViewById(R.id.cr);
            viewHolder.sj = convertView.findViewById(R.id.sj);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_HDGL_ListView shuJv_hdgl_listView = getItem(position);
        Glide.with(getContext())
                .load(App.url + shuJv_hdgl_listView.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.img);
        viewHolder.cr.setText(shuJv_hdgl_listView.getSummary());
        viewHolder.sj.setText(shuJv_hdgl_listView.getCreateTime());
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView cr;
        TextView sj;
    }
}
