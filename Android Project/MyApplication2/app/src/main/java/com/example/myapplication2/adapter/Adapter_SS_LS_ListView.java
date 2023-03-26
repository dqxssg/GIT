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
import com.example.myapplication2.bean.ShuJv_SS_LS_ListView;
import com.example.myapplication2.util.App;

import java.util.ArrayList;

public class Adapter_SS_LS_ListView extends ArrayAdapter<ShuJv_SS_LS_ListView> {
    public Adapter_SS_LS_ListView(@NonNull Context context, ArrayList<ShuJv_SS_LS_ListView> shuJv_ss_ls_listViews) {
        super(context, 0, shuJv_ss_ls_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
           convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ss_ls_listview, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.mc = convertView.findViewById(R.id.mc);
            viewHolder.flzc = convertView.findViewById(R.id.flzc);
            viewHolder.cynx = convertView.findViewById(R.id.cynx);
            viewHolder.zxrs = convertView.findViewById(R.id.zxrs);
            viewHolder.hpl = convertView.findViewById(R.id.hpl);
            viewHolder.zx = convertView.findViewById(R.id.zx);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_SS_LS_ListView shuJv_ss_ls_listView = getItem(position);
        Glide.with(getContext())
                .load(App.url + shuJv_ss_ls_listView.getAvatarUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.image);
        viewHolder.mc.setText(shuJv_ss_ls_listView.getName());
        viewHolder.flzc.setText("法律擅长：" + shuJv_ss_ls_listView.getLegalExpertiseName());
        viewHolder.cynx.setText("从业年限：" + shuJv_ss_ls_listView.getWorkStartAt() + "-至今");
        viewHolder.zxrs.setText("咨询人数：" + shuJv_ss_ls_listView.getServiceTimes());
        viewHolder.hpl.setText("好评率：" + shuJv_ss_ls_listView.getFavorableRate());
        viewHolder.zx.setOnClickListener(v -> {
            onItemListener.onClick(position);
        });
        return convertView;
    }

    class ViewHolder {
        ImageView image;
        TextView mc;
        TextView cynx;
        TextView flzc;
        TextView zxrs;
        TextView hpl;
        TextView zx;
    }
    public interface onItemListener {
        void onClick(int i);
    }

    public Adapter_LV_ListView2.onItemListener onItemListener;

    public void setOnItemListener(Adapter_LV_ListView2.onItemListener onItemListener) {
        this.onItemListener = onItemListener;

    }
}
