package com.example.myapplication6.adapter;

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
import com.example.myapplication6.R;
import com.example.myapplication6.bean.S_LJFL_LV;
import com.example.myapplication6.util.App;

import java.util.ArrayList;

public class Adapter_LJFL_LV extends ArrayAdapter<S_LJFL_LV> {
    public Adapter_LJFL_LV(@NonNull Context context, ArrayList<S_LJFL_LV> s_ljfl_lvs) {
        super(context, 0, s_ljfl_lvs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ljfl_lv, parent, false);
            viewHolder.bt = convertView.findViewById(R.id.bt);
            viewHolder.sj = convertView.findViewById(R.id.sj);
            viewHolder.image = convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_LJFL_LV s_ljfl_lv = getItem(position);
        viewHolder.bt.setText(s_ljfl_lv.getTitle());
        viewHolder.sj.setText(s_ljfl_lv.getCreateTime());
        Glide.with(getContext()).load(App.url + s_ljfl_lv.getImgUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(viewHolder.image);
        return convertView;
    }

    private class ViewHolder {
        TextView bt;
        TextView sj;
        ImageView image;
    }
}
