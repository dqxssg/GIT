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
import com.example.myapplication2.bean.ShuJv_LSXQ_ListView;
import com.example.myapplication2.util.App;

import java.util.ArrayList;

public class Adapter_LSXQ_ListView extends ArrayAdapter<ShuJv_LSXQ_ListView> {
    public Adapter_LSXQ_ListView(@NonNull Context context, ArrayList<ShuJv_LSXQ_ListView> shuJv_lsxq_listViews) {
        super(context, 0, shuJv_lsxq_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHodler viewHodler = new ViewHodler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lsxq_listview, parent, false);
            viewHodler.image = convertView.findViewById(R.id.image);
            viewHodler.nc = convertView.findViewById(R.id.nc);
            viewHodler.pjsj = convertView.findViewById(R.id.pjsj);
            viewHodler.dzs = convertView.findViewById(R.id.dzs);
            convertView.setTag(viewHodler);
        }else {
            viewHodler= (ViewHodler) convertView.getTag();
        }
        ShuJv_LSXQ_ListView shuJv_lsxq_listView=getItem(position);
        Glide.with(getContext())
                .load(App.url+shuJv_lsxq_listView.getFromUserAvatar())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHodler.image);
        viewHodler.nc .setText(shuJv_lsxq_listView.getFromUserName());
        viewHodler.pjsj.setText(shuJv_lsxq_listView.getCreateTime()+"");
        viewHodler.dzs .setText(shuJv_lsxq_listView.getLikeCount()+"");
        return convertView;
    }

    class ViewHodler {
        ImageView image;
        TextView nc;
        TextView pjsj;
        TextView dzs;

    }
}
