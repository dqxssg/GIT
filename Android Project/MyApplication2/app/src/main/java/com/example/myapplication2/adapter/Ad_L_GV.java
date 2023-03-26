package com.example.myapplication2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication2.R;
import com.example.myapplication2.bean.SJ_L_GV;
import com.example.myapplication2.bean.ShuJv_A_GridView;
import com.example.myapplication2.util.App;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ad_L_GV extends ArrayAdapter<SJ_L_GV> {
    public Ad_L_GV(@NonNull Context context, ArrayList<SJ_L_GV> sj_l_gvs) {
        super(context, 0, sj_l_gvs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_a_gridview, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SJ_L_GV sj_l_gvs = getItem(position);
        Glide.with(getContext())
                .load(App.url + sj_l_gvs.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.image);
        viewHolder.text.setText(sj_l_gvs.getName());
        return convertView;
    }

    class ViewHolder {
        ImageView image;
        TextView text;
    }
}