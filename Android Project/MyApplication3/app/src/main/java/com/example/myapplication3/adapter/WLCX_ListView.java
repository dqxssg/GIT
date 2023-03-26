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
import com.example.myapplication3.bean.ShuJv_WLCX_ListView;
import com.example.myapplication3.util.App;

import java.util.ArrayList;

public class WLCX_ListView extends ArrayAdapter<ShuJv_WLCX_ListView> {
    public WLCX_ListView(@NonNull Context context, ArrayList<ShuJv_WLCX_ListView> shuJv_wlcx_listViews) {
        super(context, 0, shuJv_wlcx_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wlcx_listview, parent, false);
            viewHolder.text = convertView.findViewById(R.id.text);
            viewHolder.img = convertView.findViewById(R.id.img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_WLCX_ListView shuJv_wlcx_listView = getItem(position);
        Glide.with(getContext())
                .load(App.url + shuJv_wlcx_listView.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.img);
        viewHolder.text.setText(shuJv_wlcx_listView.getName());
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView text;
    }
}
