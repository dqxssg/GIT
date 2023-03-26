package com.example.myapplication7.adapter;

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
import com.example.myapplication7.R;
import com.example.myapplication7.bean.S_F_A_GV;
import com.example.myapplication7.util.App;

import java.util.ArrayList;


public class Adapter_F_B_GV extends ArrayAdapter<S_F_A_GV> {
    public Adapter_F_B_GV(@NonNull Context context, ArrayList<S_F_A_GV> s_f_a_gvArrayList) {
        super(context, 0, s_f_a_gvArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_aa_gv, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_F_A_GV s_f_a_gv = getItem(position);
        Glide.with(getContext())
                .load(App.url + s_f_a_gv.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.image);
        viewHolder.text.setText(s_f_a_gv.getServiceName());
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView text;
    }
}
