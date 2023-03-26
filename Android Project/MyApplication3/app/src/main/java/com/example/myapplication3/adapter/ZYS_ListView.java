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
import com.example.myapplication3.bean.ShuJv_ZYS_ListView;
import com.example.myapplication3.util.App;

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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.zys_listviwe, parent, false);
            viewHolder.img = convertView.findViewById(R.id.img);
            viewHolder.xm = convertView.findViewById(R.id.xm);
            viewHolder.zw = convertView.findViewById(R.id.zw);
            viewHolder.zybh = convertView.findViewById(R.id.zybh);
            viewHolder.zynx = convertView.findViewById(R.id.zynx);
            viewHolder.sc = convertView.findViewById(R.id.sc);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_ZYS_ListView shuJv_zys_listView = getItem(position);
        Glide.with(getContext())
                .load(App.url + shuJv_zys_listView.getAvatar())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.img);
        viewHolder.xm.setText(shuJv_zys_listView.getName());
        viewHolder.zw.setText("职称：" + shuJv_zys_listView.getJobName());
        viewHolder.zybh.setText("职业编号：" + shuJv_zys_listView.getPracticeNo());
        viewHolder.zynx.setText("从业年限：" + shuJv_zys_listView.getWorkingYears());
        viewHolder.sc.setText("擅长：" + shuJv_zys_listView.getGoodAt());
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView xm;
        TextView zw;
        TextView zybh;
        TextView zynx;
        TextView sc;
    }
}
