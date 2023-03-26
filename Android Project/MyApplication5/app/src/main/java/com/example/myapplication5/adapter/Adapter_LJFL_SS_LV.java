package com.example.myapplication5.adapter;

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
import com.example.myapplication5.R;
import com.example.myapplication5.bean.S_LJFL_SS_LV;
import com.example.myapplication5.util.App;

import java.util.ArrayList;

public class Adapter_LJFL_SS_LV extends ArrayAdapter<S_LJFL_SS_LV> {
    public Adapter_LJFL_SS_LV(@NonNull Context context, ArrayList<S_LJFL_SS_LV> s_ljfl_ss_lvs) {
        super(context, 0, s_ljfl_ss_lvs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_a_gv, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_LJFL_SS_LV s_ljfl_ss_lv = getItem(position);
        Glide.with(getContext())
                .load(App.url + s_ljfl_ss_lv.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.image);
        viewHolder.text.setText(s_ljfl_ss_lv.getName());
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView text;
    }
}

