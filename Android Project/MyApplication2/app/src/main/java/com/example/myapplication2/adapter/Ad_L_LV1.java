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
import com.example.myapplication2.bean.SJ_L_LV1;
import com.example.myapplication2.util.App;

import java.util.ArrayList;

public class Ad_L_LV1 extends ArrayAdapter<SJ_L_LV1> {
    public Ad_L_LV1(@NonNull Context context, ArrayList<SJ_L_LV1> sj_l_lv1s) {
        super(context, 0, sj_l_lv1s);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_l_lv1, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SJ_L_LV1 sj_l_lv1 = getItem(position);
        Glide.with(getContext())
                .load(App.url + sj_l_lv1.getImageUrls())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.image);
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
    }
}
