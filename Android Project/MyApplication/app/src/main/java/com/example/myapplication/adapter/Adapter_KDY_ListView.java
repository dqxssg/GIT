package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.bean.ShuJv_KDY_ListView;
import com.example.myapplication.util.App;

import java.util.ArrayList;

public class Adapter_KDY_ListView extends ArrayAdapter<ShuJv_KDY_ListView> {
    private int num;

    public Adapter_KDY_ListView(@NonNull Context context, ArrayList<ShuJv_KDY_ListView> shuJv_kdy_listViews, int num) {
        super(context, 0, shuJv_kdy_listViews);
        this.num = num;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Viewholder viewholder = new Viewholder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_kdy_listview, parent, false);
            viewholder.mz = convertView.findViewById(R.id.mz);
            viewholder.sysj = convertView.findViewById(R.id.sysj);
            viewholder.sc = convertView.findViewById(R.id.sc);
            viewholder.image = convertView.findViewById(R.id.image);
            convertView.setTag(viewholder);
        } else {
            viewholder = (Viewholder) convertView.getTag();
        }
        ShuJv_KDY_ListView shuJv_kdy_listView = getItem(position);
        if (position < num) {
            Glide.with(getContext())
                    .load(App.url + shuJv_kdy_listView.getCover())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(viewholder.image);
            viewholder.mz.setText(shuJv_kdy_listView.getName());
            viewholder.sysj.setText("上映时间：" + shuJv_kdy_listView.getPlayDate());
            viewholder.sc.setText("时长：：" + shuJv_kdy_listView.getDuration());
        }
        return convertView;
    }

    class Viewholder {
        ImageView image;
        TextView mz;
        TextView sysj;
        TextView sc;
    }
}
