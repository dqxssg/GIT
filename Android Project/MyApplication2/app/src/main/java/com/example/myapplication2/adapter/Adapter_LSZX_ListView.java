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
import com.example.myapplication2.bean.ShuJv_LSZX_ListView;
import com.example.myapplication2.util.App;

import java.util.ArrayList;

public class Adapter_LSZX_ListView extends ArrayAdapter<ShuJv_LSZX_ListView> {
    public Adapter_LSZX_ListView(@NonNull Context context, ArrayList<ShuJv_LSZX_ListView> shuJv_lszx_listViews) {
        super(context, 0, shuJv_lszx_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lszx_listview, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.mc = convertView.findViewById(R.id.mc);
            viewHolder.flzc = convertView.findViewById(R.id.flzc);
            viewHolder.slzt = convertView.findViewById(R.id.slzt);
            viewHolder.sj = convertView.findViewById(R.id.sj);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_LSZX_ListView shuJv_lszx_listView = getItem(position);
        Glide.with(getContext())
                .load(App.url + shuJv_lszx_listView.getImageUrls())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.image);
        viewHolder.mc.setText(shuJv_lszx_listView.getLawyerName());
        viewHolder.flzc.setText("法律专长：" + shuJv_lszx_listView.getLegalExpertiseName());
        if (shuJv_lszx_listView.getState().equals("0")) {
            viewHolder.slzt.setText("未受理");
        } else {
            viewHolder.slzt.setText("已受理");
        }
        viewHolder.sj.setText("提交时间：" + shuJv_lszx_listView.getCreateTime());
        return convertView;
    }

    class ViewHolder {
        ImageView image;
        TextView mc;
        TextView flzc;
        TextView slzt;
        TextView sj;
    }
}
