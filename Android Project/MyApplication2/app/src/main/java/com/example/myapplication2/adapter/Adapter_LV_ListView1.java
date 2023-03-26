package com.example.myapplication2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication2.R;
import com.example.myapplication2.bean.ShuJv_LV_ListView1;
import com.example.myapplication2.util.App;

import java.util.ArrayList;

public class Adapter_LV_ListView1 extends ArrayAdapter<ShuJv_LV_ListView1> {
    public Adapter_LV_ListView1(@NonNull Context context, ArrayList<ShuJv_LV_ListView1> shuJv_lv_listView1s) {
        super(context, 0, shuJv_lv_listView1s);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lv_listview1, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_LV_ListView1 shuJv_lv_listView1=getItem(position);
        Glide.with(getContext())
                .load(App.url+shuJv_lv_listView1.getImageUrls())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.image);
        return convertView;
    }

    class ViewHolder {
        ImageView image;
    }
}
