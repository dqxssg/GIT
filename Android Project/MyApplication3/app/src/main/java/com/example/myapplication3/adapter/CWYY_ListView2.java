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
import com.example.myapplication3.bean.ShuJv_CWYY_ListView2;
import com.example.myapplication3.util.App;

import java.util.ArrayList;

public class CWYY_ListView2 extends ArrayAdapter<ShuJv_CWYY_ListView2> {
    public CWYY_ListView2(@NonNull Context context, ArrayList<ShuJv_CWYY_ListView2> shuJv_cwyy_listView2s) {
        super(context, 0, shuJv_cwyy_listView2s);
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
        ShuJv_CWYY_ListView2 shuJv_cwyy_listView2 = getItem(position);
        Glide.with(getContext())
                .load(App.url + shuJv_cwyy_listView2.getDoctor().getAvatar())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.img);
        viewHolder.xm.setText(shuJv_cwyy_listView2.getDoctor().getName());
        viewHolder.ms.setText(shuJv_cwyy_listView2.getQuestion());
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView xm;
        TextView ms;
    }
}
