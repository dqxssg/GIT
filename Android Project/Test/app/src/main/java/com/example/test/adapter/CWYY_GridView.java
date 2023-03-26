package com.example.test.adapter;

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
import com.example.test.R;
import com.example.test.bean.ShuJv_A_GridView;
import com.example.test.bean.ShuJv_CWYY_GridView;
import com.example.test.bean.ShuJv_WLCX_GridView;

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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cwyy_gridview, parent, false);
            viewHolder.text = convertView.findViewById(R.id.text);
            viewHolder.img = convertView.findViewById(R.id.img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_CWYY_GridView shuJv_cwyy_gridView = getItem(position);
        viewHolder.text.setText(shuJv_cwyy_gridView.getName());
        Glide.with(getContext())
                .load("http://124.93.196.45:10001" + shuJv_cwyy_gridView.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.img);
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView text;
    }
}
