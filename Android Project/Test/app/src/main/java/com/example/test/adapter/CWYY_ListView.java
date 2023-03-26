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
import com.example.test.bean.ShuJv_CWYY_ListView;

import java.util.ArrayList;

public class CWYY_ListView extends ArrayAdapter<ShuJv_CWYY_ListView> {
    public CWYY_ListView(@NonNull Context context, ArrayList<ShuJv_CWYY_ListView> shuJv_cwyy_listViews) {
        super(context, 0, shuJv_cwyy_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cwyy_listview, parent, false);
            viewHolder.img = convertView.findViewById(R.id.img);
            viewHolder.xm = convertView.findViewById(R.id.xm);
            viewHolder.ms = convertView.findViewById(R.id.ms);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_CWYY_ListView shuJv_cwyy_listView = getItem(position);
        viewHolder.xm.setText(shuJv_cwyy_listView.getDoctor().getName());
        viewHolder.ms.setText(shuJv_cwyy_listView.getQuestion());
        Glide.with(getContext())
                .load("http://124.93.196.45:10001" + shuJv_cwyy_listView.getDoctor().getAvatar())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.img);
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView xm;
        TextView ms;
    }
}
