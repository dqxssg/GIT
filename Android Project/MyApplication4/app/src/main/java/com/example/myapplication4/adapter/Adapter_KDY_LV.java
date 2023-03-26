package com.example.myapplication4.adapter;

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
import com.example.myapplication4.R;
import com.example.myapplication4.bean.S_KDY_LV;
import com.example.myapplication4.util.App;

import java.util.ArrayList;

public class Adapter_KDY_LV extends ArrayAdapter<S_KDY_LV> {
    int ii;

    public Adapter_KDY_LV(@NonNull Context context, ArrayList<S_KDY_LV> s_kdy_lvs, int ii) {
        super(context, 0, s_kdy_lvs);
        this.ii = ii;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHodler viewHodler = new ViewHodler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_kdt_lv, parent, false);
            viewHodler.image = convertView.findViewById(R.id.image);
            viewHodler.ypmc = convertView.findViewById(R.id.ypmc);
            viewHodler.sysj = convertView.findViewById(R.id.sysj);
            viewHodler.sc = convertView.findViewById(R.id.sc);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        S_KDY_LV s_kdy_lv = getItem(position);
        if (position < ii) {
            Glide.with(getContext())
                    .load(App.url + s_kdy_lv.getCover())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(viewHodler.image);
            viewHodler.ypmc.setText(s_kdy_lv.getName());
            viewHodler.sysj.setText("上映时间：" + s_kdy_lv.getPlayDate());
            viewHodler.sc.setText("时长：" + s_kdy_lv.getDuration()+"分钟");
        }
        return convertView;
    }

    private class ViewHodler {
        ImageView image;
        TextView ypmc;
        TextView sysj;
        TextView sc;
    }
}
