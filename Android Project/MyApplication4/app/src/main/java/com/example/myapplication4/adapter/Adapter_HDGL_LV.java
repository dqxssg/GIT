package com.example.myapplication4.adapter;

import android.content.Context;
import android.text.Html;
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
import com.example.myapplication4.R;
import com.example.myapplication4.bean.S_HDGL_LV;
import com.example.myapplication4.util.App;

import java.util.ArrayList;

public class Adapter_HDGL_LV extends ArrayAdapter<S_HDGL_LV> {
    public Adapter_HDGL_LV(@NonNull Context context, ArrayList<S_HDGL_LV> s_hdgl_lvs) {
        super(context, 0, s_hdgl_lvs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_hdgl_lv, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.xwpd = convertView.findViewById(R.id.xwpd);
            viewHolder.fbsj = convertView.findViewById(R.id.fbsj);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_HDGL_LV s_hdgl_lv = getItem(position);
        Glide.with(getContext())
                .load(App.url + s_hdgl_lv.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.image);
        viewHolder.xwpd.setText(s_hdgl_lv.getTitle());
        viewHolder.fbsj.setText(s_hdgl_lv.getCreateTime());
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView xwpd;
        TextView fbsj;
    }
}
