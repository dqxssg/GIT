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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication2.R;
import com.example.myapplication2.bean.ShuJv_AXJZ_GridView;
import com.example.myapplication2.util.App;

import java.util.ArrayList;

public class Adapter_AXJZ_GridView extends ArrayAdapter<ShuJv_AXJZ_GridView> {
    public Adapter_AXJZ_GridView(@NonNull Context context, ArrayList<ShuJv_AXJZ_GridView> shuJv_axjz_gridViews) {
        super(context, 0, shuJv_axjz_gridViews);
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
        ShuJv_AXJZ_GridView shuJv_axjz_gridView = getItem(position);
        Glide.with(getContext())
                .load(App.url + shuJv_axjz_gridView.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.image);
        viewHolder.text.setText(shuJv_axjz_gridView.getName());
        return convertView;
    }

    class ViewHolder {
        ImageView image;
        TextView text;
    }
}
