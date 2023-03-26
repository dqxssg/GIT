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
import com.example.myapplication3.bean.ShuJv_A_GridView;
import com.example.myapplication3.util.App;

import java.util.ArrayList;

public class A_GridView extends ArrayAdapter<ShuJv_A_GridView> {
    public A_GridView(@NonNull Context context, ArrayList<ShuJv_A_GridView> shuJv_a_gridViews) {
        super(context, 0, shuJv_a_gridViews);
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
        ShuJv_A_GridView shuJv_a_gridView = getItem(position);
        if (position < 9) {
            viewHolder.text.setText(shuJv_a_gridView.getServiceName());
            Glide.with(getContext())
                    .load(App.url + shuJv_a_gridView.getImgUrl())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(viewHolder.img);
        } else if (position == 9) {
            viewHolder.text.setText("更多服务");
            viewHolder.img.setImageResource(R.drawable.gdfw);

        }
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView text;
    }
}
