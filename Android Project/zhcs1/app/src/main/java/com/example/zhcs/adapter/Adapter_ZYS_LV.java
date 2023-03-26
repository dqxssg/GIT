package com.example.zhcs.adapter;

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
import com.example.zhcs.R;
import com.example.zhcs.bean.S_ZYS_LV;
import com.example.zhcs.util.App;

import java.util.ArrayList;

public class Adapter_ZYS_LV extends ArrayAdapter<S_ZYS_LV> {
    public Adapter_ZYS_LV(@NonNull Context context, ArrayList<S_ZYS_LV> shuJv_zys_listViews) {
        super(context, 0, shuJv_zys_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_zys_lv, parent, false);
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
        S_ZYS_LV shuJv_zys_listView = getItem(position);
        viewHolder.ysxm.setText(shuJv_zys_listView.getName());
        viewHolder.zc.setText(shuJv_zys_listView.getJobName());
        viewHolder.zybh.setText("职业编号：" + shuJv_zys_listView.getPracticeNo());
        viewHolder.scms.setText("擅长：" + shuJv_zys_listView.getGoodAt());
        viewHolder.cynx.setText("从业年限：" + shuJv_zys_listView.getWorkingYears());
        Glide.with(getContext())
                .load(App.url + shuJv_zys_listView.getAvatar())
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
