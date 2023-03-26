package com.example.myapplication6.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication6.R;
import com.example.myapplication6.bean.S_MZYY_LV;
import com.example.myapplication6.util.App;

import java.util.ArrayList;

public class Adapter_MZYY_LV extends ArrayAdapter<S_MZYY_LV> {
    public Adapter_MZYY_LV(@NonNull Context context, ArrayList<S_MZYY_LV> s_mzyy_lvs) {
        super(context, 0, s_mzyy_lvs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mzyy_lv, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.mc = convertView.findViewById(R.id.mc);
            viewHolder.ratingbar = convertView.findViewById(R.id.ratingbar);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_MZYY_LV s_mzyy_lv=getItem(position);
        Glide.with(getContext())
                .load(App.url+s_mzyy_lv.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.image);
        viewHolder.mc.setText(s_mzyy_lv.getHospitalName());
        viewHolder.ratingbar.setRating(s_mzyy_lv.getLevel());
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView mc;
        RatingBar ratingbar;
    }
}
