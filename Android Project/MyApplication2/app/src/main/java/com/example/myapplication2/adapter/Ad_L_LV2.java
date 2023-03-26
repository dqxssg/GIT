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
import com.example.myapplication2.bean.SJ_L_LV2;
import com.example.myapplication2.util.App;

import java.util.ArrayList;

public class Ad_L_LV2 extends ArrayAdapter<SJ_L_LV2> {
    public Ad_L_LV2(@NonNull Context context, ArrayList<SJ_L_LV2> sj_l_lv2s) {
        super(context, 0, sj_l_lv2s);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_l_lv2, parent, false);
            viewHolder.img = convertView.findViewById(R.id.img);
            viewHolder.mc = convertView.findViewById(R.id.mc);
            viewHolder.cynx = convertView.findViewById(R.id.cynx);
            viewHolder.flzc = convertView.findViewById(R.id.flzc);
            viewHolder.zxrs = convertView.findViewById(R.id.zxrs);
            viewHolder.hpl = convertView.findViewById(R.id.hpl);
            viewHolder.zx = convertView.findViewById(R.id.zx);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SJ_L_LV2 sj_l_lv2 = getItem(position);
        Glide.with(getContext())
                .load(App.url + sj_l_lv2.getAvatarUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.img);
        viewHolder.mc.setText(sj_l_lv2.getName());
        viewHolder.cynx.setText("从业年限：" + sj_l_lv2.getWorkStartAt() + "--至今");
        viewHolder.flzc.setText("法律专长：" + sj_l_lv2.getLegalExpertiseName());
        viewHolder.zxrs.setText("咨询人数：" + sj_l_lv2.getServiceTimes());
        viewHolder.hpl.setText("好评率：" + sj_l_lv2.getFavorableRate());
        viewHolder.zx.setOnClickListener(v -> {
            onItemListener.onClick(position);
        });
        return convertView;
    }

    private class ViewHolder {
        ImageView img;
        TextView mc;
        TextView cynx;
        TextView flzc;
        TextView zxrs;
        TextView hpl;
        TextView zx;
    }

    public interface onItemListener {
        void onClick(int position);
    }

    public Adapter_LV_ListView2.onItemListener onItemListener;

    public void setOnItemListener(Adapter_LV_ListView2.onItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
}
