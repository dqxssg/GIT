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
import com.example.myapplication2.bean.ShuJv_LV_ListView2;
import com.example.myapplication2.util.App;

import java.util.ArrayList;

public class Adapter_LV_ListView2 extends ArrayAdapter<ShuJv_LV_ListView2> {
    public Adapter_LV_ListView2(@NonNull Context context, ArrayList<ShuJv_LV_ListView2> s) {
        super(context, 0, s);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewhOlder viewhOlder = new ViewhOlder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ss_ls_listview, parent, false);
            viewhOlder.image = convertView.findViewById(R.id.image);
            viewhOlder.mc = convertView.findViewById(R.id.mc);
            viewhOlder.cynx = convertView.findViewById(R.id.cynx);
            viewhOlder.flzc = convertView.findViewById(R.id.flzc);
            viewhOlder.zxrs = convertView.findViewById(R.id.zxrs);
            viewhOlder.hpl = convertView.findViewById(R.id.hpl);
            viewhOlder.zx = convertView.findViewById(R.id.zx);
            convertView.setTag(viewhOlder);
        } else {
            viewhOlder = (ViewhOlder) convertView.getTag();
        }
        ShuJv_LV_ListView2 shuJv_lv_listView2 = getItem(position);
        Glide.with(getContext())
                .load(App.url + shuJv_lv_listView2.getAvatarUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewhOlder.image);
        viewhOlder.mc.setText(shuJv_lv_listView2.getName());
        viewhOlder.flzc.setText("法律擅长：" + shuJv_lv_listView2.getLegalExpertiseName());
        viewhOlder.cynx.setText("从业年限：" + shuJv_lv_listView2.getWorkStartAt() + "-");
        viewhOlder.zxrs.setText("咨询人数：" + shuJv_lv_listView2.getServiceTimes());
        viewhOlder.hpl.setText("好评率：" + shuJv_lv_listView2.getFavorableRate());
        viewhOlder.zx.setOnClickListener(v -> {
            onItemListener.onClick(position);
        });
        return convertView;
    }

    class ViewhOlder {
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

    public onItemListener onItemListener;

    public void setOnItemListener(onItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
}
