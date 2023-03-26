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
import com.example.test.bean.ShuJv_ZYS_ListView;

import java.util.ArrayList;

public class ZYS_ListView extends ArrayAdapter<ShuJv_ZYS_ListView> {
    public ZYS_ListView(@NonNull Context context, ArrayList<ShuJv_ZYS_ListView> shuJv_zys_listViews) {
        super(context, 0, shuJv_zys_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.zys_listview, parent, false);
            viewHolder.img = convertView.findViewById(R.id.img);
            viewHolder.ysxm = convertView.findViewById(R.id.ysxm);
            viewHolder.zc = convertView.findViewById(R.id.zc);
            viewHolder.zybh = convertView.findViewById(R.id.zybh);
            viewHolder.scms = convertView.findViewById(R.id.scms);
            viewHolder.cynx = convertView.findViewById(R.id.cynx);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_ZYS_ListView shuJv_zys_listView = getItem(position);
        viewHolder.ysxm.setText(shuJv_zys_listView.getName());
        viewHolder.zc.setText(shuJv_zys_listView.getJobName());
        viewHolder.zybh.setText("职业编号：" + shuJv_zys_listView.getPracticeNo());
        viewHolder.scms.setText("擅长：" + shuJv_zys_listView.getGoodAt());
        viewHolder.cynx.setText("从业年限：" + shuJv_zys_listView.getWorkingYears());
        Glide.with(getContext())
                .load("http://124.93.196.45:10001" + shuJv_zys_listView.getAvatar())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.img);
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView ysxm;
        TextView zc;
        TextView zybh;
        TextView scms;
        TextView cynx;
    }
}
