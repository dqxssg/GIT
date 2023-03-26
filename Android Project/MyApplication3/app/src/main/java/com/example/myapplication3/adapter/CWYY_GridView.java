package com.example.myapplication3.adapter;

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
import com.example.myapplication3.R;
import com.example.myapplication3.bean.ShuJv_CWYY_GridView;
import com.example.myapplication3.util.App;

import java.util.ArrayList;

public class CWYY_GridView extends ArrayAdapter<ShuJv_CWYY_GridView> {
    public CWYY_GridView(@NonNull Context context, ArrayList<ShuJv_CWYY_GridView> shuJv_cwyy_gridViews) {
        super(context, 0, shuJv_cwyy_gridViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.a_gridview, parent, false);
            viewHolder.img = convertView.findViewById(R.id.img);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_CWYY_GridView shuJv_cwyy_gridView = getItem(position);
        viewHolder.text.setText(shuJv_cwyy_gridView.getName());
        Glide.with(getContext())
                .load(App.url + shuJv_cwyy_gridView.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.img);
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView text;
    }
}
