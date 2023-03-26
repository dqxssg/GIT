package com.example.myapplication5.adapter;

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
import com.example.myapplication5.R;
import com.example.myapplication5.bean.S_D_LV2;
import com.example.myapplication5.bean.S_D_SS;
import com.example.myapplication5.util.App;

import java.util.ArrayList;

public class Adapter_D_LV2 extends ArrayAdapter<S_D_LV2> {
    public Adapter_D_LV2(@NonNull Context context, ArrayList<S_D_LV2> s) {
        super(context, 0, s);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_d_ss, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.mc = convertView.findViewById(R.id.mc);
            viewHolder.cynx = convertView.findViewById(R.id.cynx);
            viewHolder.zxrs = convertView.findViewById(R.id.zxrs);
            viewHolder.hpl = convertView.findViewById(R.id.hpl);
            viewHolder.zx = convertView.findViewById(R.id.zx);
            viewHolder.flzc = convertView.findViewById(R.id.flzc);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_D_LV2 s_d_ss = getItem(position);
        Glide.with(getContext()).load(App.url + s_d_ss.getAvatarUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(viewHolder.image);
        viewHolder.mc.setText(s_d_ss.getName());
        viewHolder.cynx.setText("从业年限：" + s_d_ss.getWorkStartAt());
        viewHolder.zxrs.setText("咨询人数：" + s_d_ss.getServiceTimes());
        viewHolder.hpl.setText("好评率：" + s_d_ss.getFavorableRate());
        viewHolder.flzc.setText("法律专长：" + s_d_ss.getLegalExpertiseName());
        viewHolder.zx.setOnClickListener(v -> {
            onItemListener.onClick(position);
        });
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView mc;
        TextView cynx;
        TextView zxrs;
        TextView hpl;
        TextView zx;
        TextView flzc;
    }

    public interface onItemListener {
        void onClick(int i);
    }

    public com.example.myapplication5.adapter.Adapter_D_SS.onItemListener onItemListener;

    public void setOnItemListener(com.example.myapplication5.adapter.Adapter_D_SS.onItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
}