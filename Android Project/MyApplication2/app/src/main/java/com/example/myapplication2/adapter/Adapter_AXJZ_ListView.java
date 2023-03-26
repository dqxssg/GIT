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
import com.example.myapplication2.bean.ShuJv_AXJZ_ListView;
import com.example.myapplication2.util.App;

import java.util.ArrayList;

public class Adapter_AXJZ_ListView extends ArrayAdapter<ShuJv_AXJZ_ListView> {
    public Adapter_AXJZ_ListView(@NonNull Context context, ArrayList<ShuJv_AXJZ_ListView> shuJv_axjz_listViews) {
        super(context, 0, shuJv_axjz_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_axjz_listview, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.gyxmmc = convertView.findViewById(R.id.gyxmmc);
            viewHolder.fbzz = convertView.findViewById(R.id.fbzz);
            viewHolder.jzrs = convertView.findViewById(R.id.jzrs);
            viewHolder.ycsk = convertView.findViewById(R.id.ycsk);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_AXJZ_ListView shuJv_axjz_listView = getItem(position);
        Glide.with(getContext()).load(App.url + shuJv_axjz_listView.getImgUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(viewHolder.image);
        viewHolder.gyxmmc.setText(shuJv_axjz_listView.getName());
        viewHolder.fbzz.setText(shuJv_axjz_listView.getAuthor());
        viewHolder.jzrs.setText("捐赠人数：" + shuJv_axjz_listView.getDonateCount());
        viewHolder.ycsk.setText("已筹善款：" + shuJv_axjz_listView.getMoneyNow());
        return convertView;
    }

    class ViewHolder {
        ImageView image;
        TextView gyxmmc;
        TextView fbzz;
        TextView jzrs;
        TextView ycsk;
    }
}
